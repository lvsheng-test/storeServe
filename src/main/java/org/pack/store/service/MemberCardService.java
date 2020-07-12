package org.pack.store.service;

import org.pack.store.requestVo.*;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.JSONResult;

public interface MemberCardService {

    /**
     * 创建会员卡
     * @param addCardReq
     */
    public AppletResult insertMemberCard(AddCardReq addCardReq);

    /**
     * 查询会员卡管理信息
     * @param memberCardListReq
     * @return
     */
    public JSONResult queryMemberCardAll(MemberCardListReq memberCardListReq);

    /**
     * 条件查询用户会员卡信息
     * @param membershipListReq
     * @return
     */
    public JSONResult queryMembershipByPageList(MembershipListReq membershipListReq);

    /**
     * 会员卡号与手机号查询
     * @param cardNoAndMoblieReq
     * @return
     */
    public AppletResult queryCardNoAndMoblie(CardNoAndMoblieReq cardNoAndMoblieReq);

    /**
     * 会员卡充值
     * @param rechargeMemberReq
     * @return
     */
    AppletResult doRecharge(RechargeMemberReq rechargeMemberReq);

}
