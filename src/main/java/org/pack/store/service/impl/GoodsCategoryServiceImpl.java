package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pack.store.mapper.GoodsCategoryMapper;
import org.pack.store.service.GoodsCategoryService;
import org.pack.store.utils.*;
import org.pack.store.utils.common.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private final static Logger logger = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);

    @Resource
    private UploadUtil uploadUtil;
    @Resource
	private IDGenerateUtil idGenerateUtil;
    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
    /**
     * 查询店内的菜品分类
     *
     */
    @Override
    public AppletResult queryCategoryList(JSONObject jsonObject) {
        PageInfo<JSONObject> pageInfo = null;
        try {
            int pageNum = jsonObject.getIntValue("page");
            int pageSize = jsonObject.getIntValue("limit");
			PageHelper.startPage(pageNum,pageSize,true);
			List<JSONObject> cateList = goodsCategoryMapper.queryCategoryList(jsonObject);
            //将数据封装到pageInfo类中，接下来就可以直接将pageInfo返回给页面进行前端展示了
            pageInfo = new PageInfo<>(cateList);
        } catch (Exception e) {
            logger.error("==>查询菜品分类异常",e);
        }
        return ResultUtil.success(pageInfo);
    }

	@Override
    public AppletResult queryGoodsTypeList(){
		List<JSONObject> cateList = goodsCategoryMapper.queryGoodsTypeList();
		return ResultUtil.success(cateList);
	}

	@Override
	public AppletResult addCategory(JSONObject jsonObject) {
		//jsonObject.put("id", idGenerateUtil.getId());
		jsonObject.put("id", UuidUtil.getUuid());
		jsonObject.put("storeId", "1");
		int addCategory = goodsCategoryMapper.addCategory(jsonObject);
		if(addCategory > 0){
			return ResultUtil.success("添加成功");
		}
		return ResultUtil.error(0, "添加失败");
	}
	@Override
	public AppletResult editCategory(JSONObject jsonObject) {
		/*String categoryUrl = jsonObject.getString("categoryUrl");
		if(StringUtil.isNotNullStr(categoryUrl)){
			String savePic = uploadUtil.savePic(categoryUrl);
			jsonObject.put("categoryUrl", savePic);
		}*/
		int addCategory = goodsCategoryMapper.editCategory(jsonObject);
		if(addCategory > 0){
			return ResultUtil.success("修改成功");
		}
		return ResultUtil.error(0, "修改失败");
	}
	@Override
	public AppletResult delCategory(JSONObject jsonObject) {
		jsonObject.put("storeId",1);
		int i = goodsCategoryMapper.delCategory(jsonObject);
		if(i > 0){return ResultUtil.success("删除成功");}
		return ResultUtil.error(0,"删除失败");
	}

	@Override
	public AppletResult queryGoodsTypeListByShow(){
		List<JSONObject> cateList =goodsCategoryMapper.queryGoodsTypeListByShow();
		return ResultUtil.success(cateList);
	}

	@Override
	public AppletResult queryGoodsType(){
		List<JSONObject> cateList =goodsCategoryMapper.queryGoodsType();
		return ResultUtil.success(cateList);
	}
}
