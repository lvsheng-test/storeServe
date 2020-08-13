package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.SearchDateTimeReq;

import java.util.List;

public interface CashRecordsMapper {

    /**
     *  申请提现
     * @param jsonObject
     * @return
     */
    int addCashRecordsInfo(JSONObject jsonObject);

    /**
     * 查询我的提现记录
     * @param searchDateTimeReq
     * @return
     */
    List<JSONObject> queryCashRecordsDetails(SearchDateTimeReq searchDateTimeReq);

}
