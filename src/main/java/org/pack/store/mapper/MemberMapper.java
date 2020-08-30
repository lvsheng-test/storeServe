package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MemberMapper {
    /**
     * 登录
     * @param--memberCardEntity
     */
    int insertMember(JSONObject jsonObject);

    /**
     * 查询用户是否已注册
     */
    public JSONObject queryMember(String openId);

    /**
     * 验证手机号是否存在
     * @param openId
     * @return
     */
    JSONObject queryMoblie(String openId);

    /**
     * 修改个人信息
     * @param jsonObject
     * @return
     */
    int updateMember(JSONObject jsonObject);

    /**
     * 根据用户主键查询用户信息
     * @param userId
     * @return
     */
    JSONObject getUserInfo(String userId);

}
