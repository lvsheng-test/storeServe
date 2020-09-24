package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.pack.store.autoconf.JedisOperator;
import org.pack.store.autoconf.RabbitConfig;
import org.pack.store.common.rabbitmq.producer.RabbitMqSender;
import org.pack.store.entity.AddressEntity;
import org.pack.store.enums.OrderEnums;
import org.pack.store.enums.ResultEnums;
import org.pack.store.enums.TransactionDetailEnums;
import org.pack.store.mapper.AddressMapper;
import org.pack.store.mapper.OrderMapper;
import org.pack.store.mapper.UserVipMapper;
import org.pack.store.requestVo.OrderSerchReq;
import org.pack.store.requestVo.UserTokenReq;
import org.pack.store.service.OrderService;
import org.pack.store.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private IDGenerateUtil IDGenerateUtil;
    @Resource
    private JedisOperator jedisOperator;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserVipMapper userVipMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private RabbitMqSender rabbitMqSender;

    /**
     * 查看订单详情
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getByOrderId(JSONObject jsonObject) {
        String orderId = jsonObject.getString("orderId");
        String openId = jsonObject.getString("openId");
        JSONObject orderJson = orderMapper.queryOrderInfo(orderId, openId);
        if(null != orderJson){
            //查询订单下购买的商品信息
            List<JSONObject> detailList = orderMapper.queryOrderDetail(orderId);
            orderJson.put("detailList",detailList);
            //查询该订单的配送员信息
            JSONObject deliveryJson = orderMapper.queryOrderDeliveryInfo(orderId);
            orderJson.put("horsemanInfo",deliveryJson);
        }
        return orderJson;
    }

    /**
     * 下单
     * @param jsonObject
     * @return
     */
    @Override
    public AppletResult placeOrder(JSONObject jsonObject) {
        String storeId = jsonObject.getString("storeId");
        storeId = StringUtil.isNullStr(storeId) ? "1" : storeId;
        Long nextDateSeconds = DateUtil.getNextDateSeconds();
        Long orderSerial = jedisOperator.incr(RedisKey.ORDER_SERAL + storeId, nextDateSeconds.intValue());
        String orderId = jsonObject.getString("orderId");
        jsonObject.put("orderSerial",orderSerial);
        jsonObject.put("esArriveTime",jsonObject.getString("reqTime"));
        List<JSONObject> detailList = jsonObject.getJSONArray("orderDatail").toJavaList(JSONObject.class);
        BigDecimal decimal =jsonObject.getBigDecimal("preferencesPrice");
        if (decimal ==null){
            jsonObject.put("preferencesPrice",0);
        }
        int i = orderMapper.placeOrder(jsonObject);
        if(i > 0){
            for (JSONObject jsonObj:detailList) {
                jsonObj.put("id",IDGenerateUtil.getId());
                jsonObj.put("orderId",orderId);
            }
            orderMapper.insertDetail(detailList);
            JSONObject reusltJson = new JSONObject();
            reusltJson.put("orderId",orderId);
            rabbitMqSender.queueDealy(RabbitConfig.ORDER_CANCEL,reusltJson.toJSONString(),5 * 60);
            return ResultUtil.success(reusltJson);
        }
        return ResultUtil.error(0,"下单失败");
    }

    /**
     * 支付
     * @param jsonObject
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppletResult payAccount(JSONObject jsonObject){
        String openId = jsonObject.getString("openId");
        String orderId = jsonObject.getString("orderId");
        JSONObject userInfo = userVipMapper.queryUserInfo(openId);
        jsonObject.put("id",IDGenerateUtil.getId());
        jsonObject.put("userId",null != userInfo ? userInfo.get("userId") : "");
        jsonObject.put("status", TransactionDetailEnums.ORDER_PAY.getCode());
        jsonObject.put("inOut", 1);
        jsonObject.put("remark",TransactionDetailEnums.ORDER_PAY.getMessage());
        try {//会员卡扣款
            //查询订单是否是待支付状态
            JSONObject order = orderMapper.queryOrderPending(orderId,openId);
            if (order ==null){
                return ResultUtil.error(0,"支付失败，该订单不是待支付状态！");
            }
            //查询当前用户有没有开通会员账户信息
            JSONObject acountObj = userVipMapper.queryMyMemberInfo(jsonObject.getString("userId"),"会员卡");
            if(acountObj ==null){
                return ResultUtil.error(0,"支付失败，未开通会员账户！");
            }
            acountObj.put("money",jsonObject.getBigDecimal("amount"));
            int updateBalance = orderMapper.updateBalance(acountObj);
            if(updateBalance == 0){
                return ResultUtil.error(0,"支付失败，账户余额不足");
            }
            //判断订单中有没有消费券抵扣金额、积分抵扣金额、配送费，如果有则产生相应的交易明细
            String preferencesPrice = order.getString("preferencesPrice");//消费抵扣金额
            String integralPrice =order.getString("integralPrice");//积分抵扣金额
            String deliveryFee =order.getString("deliveryFee");//配送费
            if (StringUtils.isNotEmpty(preferencesPrice) || !preferencesPrice.equals("0.00")){
                insertDetail(jsonObject.getString("userId"),orderId,preferencesPrice,1);
            }
            if (StringUtils.isNotEmpty(integralPrice) || !integralPrice.equals("0.00")){
                insertDetail(jsonObject.getString("userId"),orderId,integralPrice,2);
            }
            if (StringUtils.isNotEmpty(deliveryFee) || !deliveryFee.equals("0.00")){
                insertDetail(jsonObject.getString("userId"),orderId,deliveryFee,3);
            }
            if(orderMapper.inertTrans(jsonObject) > 0) {
                jsonObject.put("orderStatus", OrderEnums.ORDER_AL_PAY.getCode());
                orderMapper.editOrder(jsonObject);
            }
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return ResultUtil.error(0,"订单已付款，请勿重复操作");
            }
            logger.error("==>支付异常",e);
        }
        return ResultUtil.success(orderId);
    }

    /**
     * 生成交易流水明细
     * @param userId  用户ID
     * @param value   值
     * @param type    类型(1、消费卷抵扣，2、积分抵扣，3、配送费抵扣)
     */
    public void insertDetail(String userId,String orderId,String value,int type){
        BigDecimal money =new BigDecimal(value);
        if (type ==1){
            JSONObject acountObj = userVipMapper.queryMyMemberInfo(userId,"消费卡");
            if(acountObj !=null){
                acountObj.put("money",money);
                int updateBalance = orderMapper.updateBalance(acountObj);
                if (updateBalance>0){
                    logger.info("消费卷抵扣金额流水明细暂时不加");
                    //消费卷抵扣金额流水明细暂时不加
                }
            }
        }else if (type ==2){
            JSONObject acountObj =new JSONObject();
            acountObj.put("userId",userId);
            acountObj.put("amount",money.multiply(new BigDecimal(100)));
            int i= userVipMapper.updateAcountIntegral(acountObj);
            if (i>0){
                logger.info("积分扣金额流水明细暂时不加");
            }
        }else if (type ==3){
            logger.info("配送费流水明细暂时不加");
        }else {
            logger.info("类型错误");
        }
    }

    @Override
    public AppletResult goSettlement(UserTokenReq userTokenReq){
        JSONObject json =new JSONObject();
        if (StringUtil.isNullStr(userTokenReq.getUserId())){
            return ResultUtil.error(ResultEnums.USERID_IS_NULL);
        }
        //查询用户有没有设置默认地址
        AddressEntity addressEntity = addressMapper.queryAdressByUserIdAndDefalust(userTokenReq.getUserId());
        if (addressEntity !=null){
            if (addressEntity.getSex()=='0'){
                addressEntity.setSexName("先生");
            }
            if (addressEntity.getSex()=='1'){
                addressEntity.setSexName("女士");
            }
        }
        json.put("addressInfo",addressEntity);
        //查询用户积分
        JSONObject jsonObject = userVipMapper.queryMyAccount(userTokenReq.getUserId());
        if (jsonObject==null){
            json.put("integral",0);
        }else {
            json.put("integral",jsonObject.getIntValue("integral"));
        }
        //查询用户消费券
        JSONObject  obj = userVipMapper.queryMyXiaoFeiJuan(userTokenReq.getUserId());
        if (obj ==null){
            json.put("consumption","");
        }else {
            json.put("consumption",obj.getBigDecimal("amount"));
        }
        json.put("deliveryFee",0);
        //获取送达时间
        json.put("sendTime",DateUtil.getSystmeTimeOldTime());
        json.put("proportion",0.05);
        return ResultUtil.success(json);
    }

    @Override
    public AppletResult queryOrderListAll(OrderSerchReq orderSerchReq,String openId){
        List<JSONObject> orderList =new ArrayList<>();
        try{
            if (StringUtil.isNullStr(orderSerchReq.getUserId())){
                return ResultUtil.error(ResultEnums.USERID_IS_NULL);
            }
            if (StringUtil.isNullStr(orderSerchReq.getType())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            if (orderSerchReq.getType().equals("101")){//查询个人全部
                orderList =orderMapper.queryOrderByOpenIdAll(openId);
            }
            if (orderSerchReq.getType().equals("102")){
                orderList =orderMapper.queryOrderByOpenIdAndPending(openId);
            }
            if (orderSerchReq.getType().equals("103")){
                orderList =orderMapper.queryOrderByOpenIdAndReceipt(openId);
            }
            if (orderList.size()>0){
                for (JSONObject jsonObject:orderList){
                    List<JSONObject> goodsList = orderMapper.queryOrderDetail(jsonObject.get("orderId").toString());
                    jsonObject.put("orderSerial",goodsList);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success(orderList);
    }

    @Override
    public AppletResult doDeleteOrder(String orderId,String openId){
        if (StringUtil.isNullStr(orderId)){
            return ResultUtil.error(ResultEnums.USERID_IS_NULL);
        }
        int i = orderMapper.deleteOrderInfo(orderId,openId);
        if (i>0){
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnums.ORDER_DEL_ERROR);
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @param openId
     * @return
     */
    @Override
    public AppletResult cancelOrder(String orderId, String openId) {
        int i = orderMapper.cancelOrder(orderId);
        if(i > 0){
            return ResultUtil.success("取消成功");
        }
        logger.info("==>订单状态发生变化，无法取消orderId={}",orderId);
        return ResultUtil.success("订单状态发生变化，无法取消");
    }
}
