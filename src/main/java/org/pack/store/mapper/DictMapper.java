package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.entity.DictEntity;
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
}
