package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pack.store.autoconf.DataConfig;
import org.pack.store.mapper.GoodsMapper;
import org.pack.store.requestVo.GoodsTypeReq;
import org.pack.store.requestVo.PageInfoReq;
import org.pack.store.service.GoodsService;
import org.pack.store.utils.*;
import org.pack.store.utils.common.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final static Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);


    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private IDGenerateUtil idGenerateUtil;
    @Resource
    private DataConfig dataConfig;
    /**
     * 查询店内的菜品
     *
     * @param jsonObject
     */
    @Override
    public AppletResult queryGoodsList(JSONObject jsonObject) {
        PageInfo<JSONObject> pageInfo = null;
        try {
            int pageNum = jsonObject.getIntValue("page");
            int pageSize = jsonObject.getIntValue("limit");
            PageHelper.startPage(pageNum,pageSize,true);
            List<JSONObject> cateList = goodsMapper.queryGoodsList(jsonObject);
            //将数据封装到pageInfo类中，接下来就可以直接将pageInfo返回给页面进行前端展示了
            pageInfo = new PageInfo<>(cateList);
        } catch (Exception e) {
            logger.error("==>查询菜品分类异常",e);
        }
        return ResultUtil.success(pageInfo);
    }

    /**
     * 添加商品
     *
     * @param jsonObject
     */
    @Override
    @Transactional
    public AppletResult addGoods(JSONObject jsonObject) {
        //String goodsId = idGenerateUtil.getId();
        //jsonObject.put("id", goodsId);
        jsonObject.put("id", UuidUtil.getUuid());
        jsonObject.put("storeId", "1");
        jsonObject.put("goodsStatus",1);//商品状态(0下架;1上架;3售罄)
        jsonObject.put("discountType",0);//优惠类型(0:会员价，1:拼单价)
        JSONArray pictureList = jsonObject.getJSONArray("pictureList");
        String  goodsUrl ="";
        for (Object url:pictureList) {
            goodsUrl+=url+",";
        }
        jsonObject.put("goodsUrl",goodsUrl);
        int i = goodsMapper.addGoods(jsonObject);
        if(i > 0){
            /*List<JSONObject> list = new ArrayList<>();
            JSONObject json = null;
            for (Object url:pictureList) {
                json = new JSONObject();
                String picId = idGenerateUtil.getId("t_goods_picture");
                json.put("id",picId);
                json.put("goodsId",goodsId);
                json.put("goodsUrl",url);
                list.add(json);
            }
            goodsMapper.addPicture(list);*/
            return ResultUtil.success("添加成功");
        }
        return ResultUtil.error(0,"添加失败");
    }

    /**
     * 修改菜品分类(上下架)
     *
     * @param jsonObject
     */
    @Override
    public AppletResult editGoods(JSONObject jsonObject) {
        int addCategory = goodsMapper.editGoods(jsonObject);
        if(addCategory > 0){
            return ResultUtil.success("修改成功");
        }
        return ResultUtil.error(0, "修改失败");
    }

    /**
     * 删除菜品
     *
     * @param jsonObject
     */
    @Override
    public AppletResult delGoods(JSONObject jsonObject) {
        jsonObject.put("storeId",1);
        int i = goodsMapper.delGoods(jsonObject);
        if(i > 0){return ResultUtil.success("删除成功");}
        return ResultUtil.error(0,"删除失败");
    }

    /**
     * @param goodsId
     * @查询商品图片
     */
    @Override
    public AppletResult queryGoodsPic(String goodsId) {
        String goodsUrl = dataConfig.getGoodsUrl();
        List<JSONObject> jsonObjects = goodsMapper.queryGoodsPic(goodsId,goodsUrl);
        return ResultUtil.success(jsonObjects);
    }

    @Override
    public AppletResult queryGoodsLike(PageInfoReq PageInfoReq){
        PageInfo<JSONObject> pageInfo = null;
        List<JSONObject> list =new ArrayList<>();
        try{
            PageHelper.startPage(PageInfoReq.getPage(),PageInfoReq.getLimit(),true);
            List<JSONObject> jsonObjects =goodsMapper.queryGoodsLike();
            if (jsonObjects.size()>0){
                for (JSONObject object:jsonObjects) {
                    String goodsUrl = object.get("goodsUrl").toString();
                    String arr [] = goodsUrl.split(",");
                    object.put("goodsUrl",arr[0]);
                    list.add(object);
                }
            }
            pageInfo = new PageInfo<>(list);
        }catch (Exception e){
            logger.error("==>查询猜你喜欢异常",e);
        }
        return ResultUtil.success(pageInfo);
    }
    @Override
    public AppletResult queryGoodsInfoListByTypeId(GoodsTypeReq goodsTypeReq){
        PageInfo<JSONObject> pageInfo = null;
        List<JSONObject> list =new ArrayList<>();
        try{
            PageHelper.startPage(goodsTypeReq.getPage(),goodsTypeReq.getLimit(),true);
            List<JSONObject> jsonObjects =goodsMapper.queryGoodsInfoListByTypeId(goodsTypeReq.getPid());
            if (jsonObjects.size()>0){
                for (JSONObject object:jsonObjects) {
                    String goodsUrl = object.get("goodsUrl").toString();
                    String arr [] = goodsUrl.split(",");
                    object.put("goodsUrl",arr[0]);
                    list.add(object);
                }
            }
            pageInfo = new PageInfo<>(list);
        }catch (Exception e){
            logger.error("==>查询猜你喜欢异常",e);
        }
        return ResultUtil.success(pageInfo);
    }
}
