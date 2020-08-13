package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.SearchDateTimeReq;
import java.util.List;

public interface CommissionDetailsMapper {

    /**
     * 查询我的佣金明细
     * @param searchDateTimeReq
     * @return
     */
    List<JSONObject> queryCommissionDetails(SearchDateTimeReq searchDateTimeReq);
}
