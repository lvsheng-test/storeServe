package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.service.GoodsService;
import org.pack.store.utils.AppletResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class GoodsServiceImpl implements GoodsService {

    /**
     * 查询店内的菜品
     *
     * @param jsonObject
     */
    @Override
    public AppletResult queryGoodsList(JSONObject jsonObject) {
        return null;
    }

    /**
     * 添加菜品分类
     *
     * @param jsonObject
     */
    @Override
    public AppletResult addGoods(JSONObject jsonObject) {
        return null;
    }

    /**
     * 修改菜品分类(上下架)
     *
     * @param jsonObject
     */
    @Override
    public AppletResult editGoods(JSONObject jsonObject) {
        return null;
    }

    /**
     * 删除菜品
     *
     * @param jsonObject
     */
    @Override
    public AppletResult delGoods(JSONObject jsonObject) {
        return null;
    }
}
