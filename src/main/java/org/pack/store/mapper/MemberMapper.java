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
    public int insertMember(JSONObject jsonObject);


    /**
     * 查询用户是否已注册
     */
    public JSONObject queryMember(String openId);

}
