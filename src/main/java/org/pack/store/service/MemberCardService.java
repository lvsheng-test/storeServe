package org.pack.store.service;

import org.pack.store.requestVo.AddCardReq;
import org.pack.store.requestVo.MemberCardListReq;
import org.pack.store.requestVo.MembershipListReq;
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

}
