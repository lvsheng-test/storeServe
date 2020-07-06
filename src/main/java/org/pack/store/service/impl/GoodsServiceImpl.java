package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pack.store.mapper.GoodsMapper;
import org.pack.store.service.GoodsService;
import org.pack.store.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final static Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);


    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private IDGenerateUtil idGenerateUtil;
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
    public AppletResult addGoods(JSONObject jsonObject) {
        jsonObject.put("id", idGenerateUtil.getId());
        jsonObject.put("storeId", "1");
        jsonObject.put("goodsStatus",0);
        jsonObject.put("discountType",0);
        int i = goodsMapper.addGoods(jsonObject);
        if(i > 0){
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
}
