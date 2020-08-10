package org.pack.store.mapper;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface GoodsCategoryMapper {

    /**
     * 查询店内的菜品分类
     */
    List<JSONObject> queryCategoryList(JSONObject jsonObject);

    List<JSONObject> queryGoodsTypeList();
    
    int addCategory(JSONObject jsonObject);
    
    int editCategory(JSONObject jsonObject);

    int delCategory(JSONObject jsonObject);

    /**********************************小程序接口开发************************************/
    /**
     *  查询首页商品分类信息
     * @return
     */
    List<JSONObject> queryGoodsTypeListByShow();

}
