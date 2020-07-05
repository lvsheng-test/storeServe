package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.pack.store.entity.MembershipEntity;
import org.pack.store.requestVo.MembershipListReq;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MembershipMapper {

    /**
     * 分页条件查询用户会员卡信息
     * @param membershipListReq
     * @return
     */
    public List<MembershipEntity> queryMembershipByPageList(MembershipListReq membershipListReq);

    /**
     * 分页条件查询用户会员卡数量
     * @param membershipListReq
     * @return
     */
    public int getMembershipByPageCount(MembershipListReq membershipListReq);
}
