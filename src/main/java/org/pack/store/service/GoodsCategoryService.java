package org.pack.store.service;

import org.pack.store.utils.AppletResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

public interface GoodsCategoryService {

    /**
     * 查询店内的菜品分类
     */
    AppletResult queryCategoryList(JSONObject jsonObject);

    /**
     * 查询所有商品类型列表
     * @return
     */
    AppletResult queryGoodsTypeList();
    
    /**
     * 添加菜品分类
     */
    AppletResult addCategory(JSONObject jsonObject);
    
    /**
     * 修改菜品分类
     */
    AppletResult editCategory(JSONObject jsonObject);
    
    /**
     * 删除菜品分类
     */
    AppletResult delCategory(JSONObject jsonObject);

    /**********************************小程序接口开发************************************/
    /**
     *  查询首页商品分类信息
     * @return
     */
    AppletResult queryGoodsTypeListByShow();

    /**
     * 查询商品分类列表
     * @return
     */
    AppletResult queryGoodsType();
}
