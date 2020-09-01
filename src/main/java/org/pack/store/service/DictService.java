package org.pack.store.service;

import org.pack.store.entity.DictEntity;
import org.pack.store.requestVo.*;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.JSONResult;

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

    /**
     * 查询配置比例信息列表
     * @param configReq
     * @return
     */
    public JSONResult queryConfigAll(ConfigReq configReq);

    /**
     * 添加返佣比例
     * @param configAddReq
     */
    void insertConfigProportion(ConfigAddReq configAddReq);

    /**
     * 添加常见问题信息
     * @param questionsReq
     * @return
     */
    AppletResult addQuestionsInfo(QuestionsReq questionsReq);

}
