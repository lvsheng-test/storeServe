package org.pack.store.service;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.GoodsTypeReq;
import org.pack.store.requestVo.PageInfoReq;
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

    /**************************小程序接口开发******************************/
    /**
     * 获取首页商品猜你喜欢信息列表
     * @return
     */
    AppletResult queryGoodsLike(PageInfoReq PageInfoReq);

    /**
     * 根据商品分类查询对应商品信息
     * @param goodsTypeReq
     * @return
     */
    AppletResult queryGoodsInfoListByTypeId(GoodsTypeReq goodsTypeReq);

}
