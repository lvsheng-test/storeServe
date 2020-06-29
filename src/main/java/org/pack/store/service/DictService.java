package org.pack.store.service;

import org.pack.store.requestVo.DictReq;
import org.pack.store.utils.AppletResult;

public interface DictService {

    /**
     * 查询所有字典表信息
     * @param dictReq
     * @return
     */
    public AppletResult queryDictAll(DictReq dictReq);
}
