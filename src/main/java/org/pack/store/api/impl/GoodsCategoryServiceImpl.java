package org.pack.store.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pack.store.api.GoodsCategoryService;
import org.pack.store.enums.ResultEnums;
import org.pack.store.mapper.GoodsCategoryMapper;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private final static Logger logger = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
    /**
     * 查询店内的菜品分类
     *
     * @param storeId
     */
    @Override
    public AppletResult queryCategoryList(JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"storeId")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
        /**分页插件会自动分页紧跟在PageHelper.startPage(pageNum,pageSize)后面的第一条查询语句
         * startPage的两个属性分别是：
         * 1.pageNum当前是第几页
         * 2.pageSize一页分多少条数据
         * */
        PageInfo pageInfo = null;
        try {
            int pageNum = jsonObject.getIntValue("pageNum");
            String storeId = jsonObject.getString("storeId");
            Page page = PageHelper.startPage(pageNum, 4);
            List<JSONObject> cateList = goodsCategoryMapper.queryCategoryList(storeId);
            //将数据封装到pageInfo类中，接下来就可以直接将pageInfo返回给页面进行前端展示了
            pageInfo = new PageInfo<>(cateList);
        } catch (Exception e) {
            logger.error("==>查询菜品分类异常",e);
        }
        return ResultUtil.success(pageInfo);
    }
}
