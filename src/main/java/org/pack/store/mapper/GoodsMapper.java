package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    /**
     * 查询店内的菜品分类
     */
    List<JSONObject> queryGoodsList(JSONObject jsonObject);
    
    int addGoods(JSONObject jsonObject);
    
    int editGoods(JSONObject jsonObject);

    int delGoods(JSONObject jsonObject);

    int addPicture(List<JSONObject> list);

    List<JSONObject> queryGoodsPic(@Param("goodsId") String goodsId, @Param("goodsUrl") String goodsUrl);

    /***************************小程序开发接口*************************/
    /**
     * 获取首页商品猜你喜欢信息列表
     * @return
     */
    List<JSONObject> queryGoodsLike();

    /**
     *  根据商品分类查询对应商品信息
     * @param pid
     * @return
     */
    List<JSONObject> queryGoodsInfoListByTypeId(@Param("pid") String pid);
}
