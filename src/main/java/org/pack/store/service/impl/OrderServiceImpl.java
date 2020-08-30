package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.autoconf.JedisOperator;
import org.pack.store.mapper.OrderMapper;
import org.pack.store.service.OrderService;
import org.pack.store.utils.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private IDGenerateUtil IDGenerateUtil;
    @Resource
    private JedisOperator jedisOperator;
    @Resource
    private OrderMapper orderMapper;

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
            return ResultUtil.success("下单成功");
        }
        return ResultUtil.error(0,"下单失败");
    }

    /**
     * 支付
     * @param jsonObject
     * @return
     */
    public AppletResult payAccount(JSONObject jsonObject){
        return null;
    }
}
