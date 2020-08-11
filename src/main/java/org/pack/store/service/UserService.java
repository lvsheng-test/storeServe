package org.pack.store.service;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.AddressReq;
import org.pack.store.requestVo.AppVO;
import org.pack.store.requestVo.BindMemberReq;
import org.pack.store.requestVo.ParentCodeReq;
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

    /**
     * 查询开通的城市信息
     * @return
     */
    AppletResult queryCommonDict(ParentCodeReq parentCodeReq);

    /**
     * 绑定会员卡操作
     * @param bindMemberReq
     * @return
     */
    AppletResult bindingMembership(BindMemberReq bindMemberReq);

    /**
     * 登录操作
     * @param jsonObject
     * @return
     */
    AppletResult login(AppVO<JSONObject> jsonObject);

    /**
     * 查询我的会员卡
     * @param userId
     * @return
     */
    AppletResult queryMyMembership(String userId);



}
