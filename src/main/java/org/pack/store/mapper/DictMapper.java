package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.entity.DictEntity;
import org.pack.store.resposeVo.CityInfoRes;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper
public interface DictMapper {

    /**
     * 条件查询  根据父级编码查询字典表信息
     * @param parentCode
     * @return
     */
    public List<DictEntity> queryDictAll(@Param("parentCode") String parentCode);

    List<CityInfoRes> queryCommonParentCode(@Param("parentCode") String parentCode);

    /**
     * 添加字典表信息
     * @param dictInfo
     */
    public void inserDictInfo(DictEntity dictInfo);

    /**
     * 根据主键ID删除字典信息
     * @param id
     */
    public void deleteDictInfo(@Param("id") String id);

    /**
     * 根据CODE查询单挑字典信息
     * @param code
     * @return
     */
    public DictEntity getCode(@Param("code") String code);
}
