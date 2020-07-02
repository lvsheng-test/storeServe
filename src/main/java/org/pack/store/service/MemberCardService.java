package org.pack.store.service;

import org.pack.store.requestVo.AddCardReq;
import org.pack.store.requestVo.MemberCardListReq;
import org.pack.store.utils.AppletResult;

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
    public AppletResult queryMemberCardAll(MemberCardListReq memberCardListReq);

}
