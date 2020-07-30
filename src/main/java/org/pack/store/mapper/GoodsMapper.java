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
}
