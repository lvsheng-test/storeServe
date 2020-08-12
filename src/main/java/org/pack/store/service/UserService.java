package org.pack.store.service;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.*;
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

    /**
     * 解除绑定会员卡
     * @param memberId
     * @return
     */
    AppletResult releaseMembership(String memberId);

    /**
     * 查询我的佣金明细
     * @param searchDateTimeReq
     * @return
     */
    AppletResult queryCommissionDetails(SearchDateTimeReq searchDateTimeReq);

    /**
     * 提现申请
     * @param applyRecordsReq
     * @return
     */
    AppletResult doCashRecords(ApplyRecordsReq applyRecordsReq);

    /**
     * 查询我的提现记录
     * @param searchDateTimeReq
     * @return
     */
    AppletResult queryCashRecordsDetails(SearchDateTimeReq searchDateTimeReq);

}
