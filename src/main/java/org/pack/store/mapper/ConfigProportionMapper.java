package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.entity.ConfigProportionEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ConfigProportionMapper {

    /**
     * 添加配置信息
     * @param configProportion
     */
    void insertConfigProportion(ConfigProportionEntity configProportion);

    void updateConfigProportion(ConfigProportionEntity configProportion);

    List<ConfigProportionEntity> selectAll();

    /**
     * 根据配置类型查询配置信息
     * @param type
     * @return
     */
    ConfigProportionEntity getConfigType(@Param("type") String type);

}
