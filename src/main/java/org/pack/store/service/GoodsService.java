package org.pack.store.service;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.utils.AppletResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsService {

    /**
     * 查询店内的菜品
     */
    AppletResult queryGoodsList(@RequestParam JSONObject jsonObject);
    
    /**
     * 添加菜品分类
     */
    AppletResult addGoods(@RequestBody JSONObject jsonObject);
    
    /**
     * 修改菜品分类(上下架)
     */
    AppletResult editGoods(@RequestBody JSONObject jsonObject);
    
    /**
     * 删除菜品
     */
    AppletResult delGoods(@RequestBody JSONObject jsonObject);

    /**
     * @查询商品图片
     */
    AppletResult queryGoodsPic(String goodsId);
}
