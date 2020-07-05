package org.pack.store.mapper;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface GoodsCategoryMapper {

    /**
     * 查询店内的菜品分类
     */
    List<JSONObject> queryCategoryList(JSONObject jsonObject);
    
    int addCategory(JSONObject jsonObject);
    
    int editCategory(JSONObject jsonObject);

    int delCategory(JSONObject jsonObject);
}
