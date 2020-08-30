package org.pack.store.service;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.AddressReq;
import org.pack.store.requestVo.BindMemberReq;
import org.pack.store.requestVo.ParentCodeReq;
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

}
