package org.pack.store.service;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.*;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.JSONResult;

public interface OrderService {

    /**
     * 查询订单详情
     * @return
     */
    JSONObject getByOrderId(JSONObject jsonObject);

    /**
     * 下单
     * @param jsonObject
     * @return
     */
    AppletResult placeOrder(JSONObject jsonObject);

    /**
     * 购物车去结算接口
     * @param userTokenReq
     * @return
     */
    AppletResult goSettlement(UserTokenReq userTokenReq);
    /**
     * 下单扣款
     * @param jsonObject
     * @return
     */
    public AppletResult payAccount(JSONObject jsonObject);

    /**
     * 查询订单列表接口
     * @param orderSerchReq
     * @return
     */
    AppletResult queryOrderListAll(OrderSerchReq orderSerchReq,String openId);

    /**
     * 逻辑删除订单信息
     * @param orderId
     * @param openId
     * @return
     */
    AppletResult doDeleteOrder(String orderId,String openId);
    /**
     * 取消订单
     * @param orderId
     * @return
     */
    AppletResult cancelOrder(String orderId,String openId);

    /**
     * 订单列表查询
     * @param orderListReq
     * @return
     */
    AppletResult queryOrderList(OrderListReq orderListReq);
}
