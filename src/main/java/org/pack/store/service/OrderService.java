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

    /****************************************后台订单接口**************************************
     * 订单列表查询
     * @param orderListReq
     * @return
     */
    AppletResult queryOrderList(OrderListReq orderListReq);

    /**
     * 分配骑手派送订单
     * @param orderReq
     * @return
     */
    AppletResult doAllocationHorseman(OrderReq orderReq);

    /**
     * 后台订单确认收货操作
     * @param orderReq
     * @return
     */
    AppletResult doConfirmationCompletion(OrderReq orderReq);

    /**
     * 到店自取接口
     * @param goStoreReq
     * @return
     */
    AppletResult goToStore(GoStoreReq goStoreReq);

    /**
     * 查询自取时间
     * @return
     */
    AppletResult querySelfTime();
}
