package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface InviteCourtesyMapper {

    /**
     * 根据上级邀请码查询所属下级个数
     * @param superiorCode
     * @return
     */
    int getCount(Integer superiorCode);

    /**
     * 查询邀请的有效和无效人数信息
     * @param jsonObject
     * @return
     */
    List<JSONObject> queryInviteCourtesyByState(JSONObject jsonObject);

}
