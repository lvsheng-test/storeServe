package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.service.OrderService;
import org.pack.store.utils.AppletResult;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    /**
     * 下单
     * @param jsonObject
     * @return
     */
    @Override
    public AppletResult placeOrder(JSONObject jsonObject) {

        return null;
    }

}
