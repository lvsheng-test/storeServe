package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.entity.MemberCardEntity;
import org.pack.store.requestVo.MemberCardListReq;
import org.springframework.stereotype.Component;

import java.util.List;

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
