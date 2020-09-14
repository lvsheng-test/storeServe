package org.pack.store.service;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.*;
import org.pack.store.utils.AppletResult;

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
}
