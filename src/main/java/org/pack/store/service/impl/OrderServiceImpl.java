package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.autoconf.JedisOperator;
import org.pack.store.enums.TransactionDetailEnums;
import org.pack.store.mapper.OrderMapper;
import org.pack.store.mapper.UserVipMapper;
import org.pack.store.service.OrderService;
import org.pack.store.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
     * 查看订单详情
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getByOrderId(JSONObject jsonObject) {
        String orderId = jsonObject.getString("orderId");
        String openId = jsonObject.getString("openId");
        JSONObject orderJson = orderMapper.getByOrderId(orderId, openId);
        if(null != orderJson){
            List<JSONObject> detailList = orderMapper.goodsDetail(orderId);
            orderJson.put("detailList",detailList);
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
        String orderId = IDGenerateUtil.getId();
        jsonObject.put("orderId",orderId);
        jsonObject.put("orderSerial",orderSerial);
        List<JSONObject> detailList = jsonObject.getJSONArray("orderDatail").toJavaList(JSONObject.class);
        int i = orderMapper.placeOrder(jsonObject);
        if(i > 0){
            for (JSONObject jsonObj:detailList) {
                jsonObj.put("id",IDGenerateUtil.getId());
                jsonObj.put("orderId",orderId);
            }
            orderMapper.insertDetail(detailList);
            JSONObject reusltJson = new JSONObject();
            reusltJson.put("orderId",orderId);
            return ResultUtil.success(reusltJson);
        }
        return ResultUtil.error(0,"下单失败");
    }

    /**
     * 支付
     * @param
     * @return
     */
    @Override
    public AppletResult payAccount(JSONObject jsonObject){
        String openId = jsonObject.getString("openId");
        String orderId = jsonObject.getString("orderId");
        JSONObject userInfo = userVipMapper.queryUserInfo(openId);
        jsonObject.put("id",IDGenerateUtil.getId());
        jsonObject.put("userId",null != userInfo ? userInfo.get("userId") : "");
        jsonObject.put("status", TransactionDetailEnums.ORDER_PAY.getCode());
        jsonObject.put("inOut", 1);
        try {
            int updateBalance = orderMapper.updateBalance(jsonObject);
            if(updateBalance == 0){
                return ResultUtil.error(0,"未开通会员账户或账户余额不足");
            }
            orderMapper.inertTrans(jsonObject);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return ResultUtil.error(0,"订单已付款，请勿重复操作");
            }
            logger.error("==>支付异常",e);
        }
        return ResultUtil.success(orderId);
    }
}
