package org.pack.store.service;

import org.pack.store.entity.DictEntity;
import org.pack.store.requestVo.DictByParentCodeReq;
import org.pack.store.requestVo.DictReq;
import org.pack.store.utils.AppletResult;

public interface DictService {

    /**
     * 查询所有字典表信息
     * @param dictReq
     * @return
     */
    public AppletResult queryDictAll(DictReq dictReq);

    /**
     * 根据父级编码查询字典信息
     * @param dictByParentCodeReq
     * @return
     */
    public AppletResult searchParentCode(DictByParentCodeReq dictByParentCodeReq);

    /**
     * 添加字典表信息
     * @param dictInfo
     */
    public void inserDictInfo(DictEntity dictInfo);

    /**
     * 根据主键ID删除字典信息
     * @param id
     */
    public void deleteDictInfo(String id);

}