package org.pack.store.service;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.entity.AliyunOssEntity;
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
    AppletResult login(JSONObject jsonObject);

    /**
     * 手机号解密操作
     * @param jsonObject
     * @return
     */
    AppletResult doDecryptionMoblie(JSONObject jsonObject);

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

    /**
     * 查询首页轮播图
     * @return
     */
    AppletResult queryBannerList();

    /**
     * 查询我的账户信息
     * @param userId
     * @return
     */
    AppletResult queryMyAccount(String userId);

    /**
     * 删除收货地址信息
     * @param delAddressReq
     * @return
     */
    AppletResult delAddressInfo(DelAddressReq delAddressReq);

    /**
     * 我的邀请有礼
     * @param userReq
     * @return
     */
    AppletResult myInviteCourtesy(UserReq userReq);

    /**
     * 获取OOS信息
     * @return
     */
    AliyunOssEntity getAliyunOssInfo();

    /**
     * 添加意见反馈内容
     * @param feedbackReq
     * @return
     */
    AppletResult addFeedback(FeedbackReq feedbackReq);

    /**
     * 查询常见问题接口
     * @return
     */
    AppletResult queryQuestions();

    /**
     * 关于我们
     * @return
     */
    AppletResult getAbout();

    /**
     * 判断用户是否注册过
     * @param userLoginReq
     * @return
     */
    AppletResult queryUserLoginState(UserLoginReq userLoginReq);

}
