package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface GoodsMapper {

    /**
     * 查询店内的菜品分类
     */
    List<JSONObject> queryGoodsList(JSONObject jsonObject);
    
    int addGoods(JSONObject jsonObject);
    
    int editGoods(JSONObject jsonObject);

    int delGoods(JSONObject jsonObject);
}
