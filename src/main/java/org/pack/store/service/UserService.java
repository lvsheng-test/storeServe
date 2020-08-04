package org.pack.store.service;

import org.pack.store.requestVo.AddressReq;
import org.pack.store.utils.AppletResult;

public interface UserService {

    /**
     * 查询的收货地址
     * @param userId
     * @return
     */
    AppletResult queryMyAddress(String userId);

    /**
     * 编辑我的收货地址
     * @param addressReq
     */
    AppletResult updateMyAddress(AddressReq addressReq);

    /**
     * 添加我的收货地址
     * @param addressReq
     */
    AppletResult insertMyAddress(AddressReq addressReq);

}
