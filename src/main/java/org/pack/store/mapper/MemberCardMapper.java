package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.entity.MemberCardEntity;
import org.pack.store.requestVo.MemberCardListReq;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MemberCardMapper {
    /**
     * 创建会员卡
     * @param memberCardEntity
     */
    public void insertMemberCard(MemberCardEntity memberCardEntity);

    /**
     * 分页条件查询会员卡管理信息列表
     * @param memberCardListReq
     * @return
     */
    public List<MemberCardEntity> queryMemberCardAll(MemberCardListReq memberCardListReq);

    /**
     * 条件查询会员卡管理信息总条数
     * @param memberCardListReq
     * @return
     */
    public int queryMemberCardAllByCount(MemberCardListReq memberCardListReq);

    /**
     * 根据会员卡卡号查询未启用信息
     * @param cardNo
     * @return
     */
    public MemberCardEntity getCardNo(@Param("cardNo") String cardNo);

    /**
     * 修改会员卡信息
     * @param memberCardEntity
     */
    public void updataMemberCardInfo(MemberCardEntity memberCardEntity);
}
