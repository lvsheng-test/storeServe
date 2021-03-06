package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 根据手机号和会员卡验证信息是否存在
     * @param mobile
     * @param memberType
     * @return
     */
    public MembershipEntity getMoblieAndMemberType(@Param("mobile") String mobile,@Param("memberType") String memberType);

    /**
     * 开通会员卡信息
     * @param membershipEntity
     * @return
     */
    public int insertMembershipInfo(MembershipEntity membershipEntity);

    /**
     * 会员卡充值
     * @param membershipEntity
     * @return
     */
    int doRechages(MembershipEntity membershipEntity);
    /*********************************小程序接口开发**********************************/
    /**
     * 根据会员卡类型与卡号查询
     * @param membershipEntity
     * @return
     */
    MembershipEntity getMemberTypeAndCardNo(MembershipEntity membershipEntity);


}
