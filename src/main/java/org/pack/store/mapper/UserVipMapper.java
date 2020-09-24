package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.requestVo.BindMemberReq;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserVipMapper {

    /**
     * 校验一下该用户有没有重复绑定会员卡，然后校验用户只允许绑定每种类型卡一张
     * @param bindMemberReq
     * @return
     */
    JSONObject queryBindingMember(BindMemberReq bindMemberReq);

    /**
     * 绑定会员卡，生成绑定记录
     * @param jsonObject
     * @return
     */
    int addBindingMember(JSONObject jsonObject);

    /**
     * 查询我的会员卡列表
     * @param userId
     * @return
     */
    List<JSONObject> queryMyMembership(@Param("userId") String userId);

    /**
     * 解除绑定会员卡
     * 先做物理删除，后期优化逻辑删除
     * @param memberId
     * @return
     */
    int deleteBindingMember(@Param("memberId") String memberId);

    /**
     * 校验其他人是否绑定过此会员卡
     * @param bindMemberReq
     * @return
     */
    JSONObject queryIsBinding(BindMemberReq bindMemberReq);

    /**
     *  创建用户个人账户信息
     * @param jsonObject
     * @return
     */
    int addAccountInfo(JSONObject jsonObject);

    /**
     * 查询首页轮播图列表
     * @return
     */
    List<JSONObject> queryBannerList();

    /**
     * 查询我的个人账户信息
     * @param userId
     * @return
     */
    JSONObject queryMyAccount(@Param("userId") String userId);

    /**
     * 查询我绑定的会员卡余额
     * @param userId
     * @return
     */
    JSONObject queryMyMemberAcount(@Param("userId") String userId);

 /**
     * 查询我的消费卷
     * @param userId
     * @return
     */
    JSONObject queryMyXiaoFeiJuan(@Param("userId") String userId);

 	JSONObject queryUserInfo(String openId);

    /**
     * 查询我绑定的会员卡信息
     * @param userId
     * @return
     */
    JSONObject queryMyMemberInfo(@Param("userId") String userId,@Param("typeName") String typeName);

    int updateAcountBalance(JSONObject jsonObject);

    /**
     * 生成一条提现流水明细
     * @param jsonObject
     */
    void insertTransactionDetail(JSONObject jsonObject);

    /**
     * 扣除账户积分
     * @param jsonObject
     * @return
     */
    int updateAcountIntegral(JSONObject jsonObject);


}
