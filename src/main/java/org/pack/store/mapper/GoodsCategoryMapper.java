package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.entity.DictEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface GoodsCategoryMapper {

    /**
     * 查询店内的菜品分类
     */
    List<JSONObject> queryCategoryList(String storeId);
}
