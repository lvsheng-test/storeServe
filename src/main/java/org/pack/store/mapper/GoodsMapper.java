package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.pack.store.resposeVo.SearchVo;

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

    /**
     *  查询商品详情信息
     * @param goodsId
     * @return
     */
    JSONObject queryGoodsDetails(@Param("goodsId") String goodsId);

    /**
     * 关键字搜索
     * @param keys
     * @return
     */
    List<JSONObject> searchKeyWords(@Param("keys") String keys);

    /**
     * 条件搜索商品
     * @param searchVo
     * @return
     */
    List<JSONObject> searchGoodsInfoList(SearchVo searchVo);

    /**
     * 查询有效的活动
     * @return
     */
    JSONObject queryActivities();

    /**
     * 根据活动ID查询限时抢购商品
     * @param activeId
     * @return
     */
    List<JSONObject> queryTimeLimitList(@Param("activeId") String activeId);

    /**
     * 查询限时抢购商品详情接口
     * @param goodsId
     * @return
     */
    JSONObject queryTimeLimitInfo(@Param("activeId") String activeId,@Param("goodsId") String goodsId);

}
