/*
package org.pack.store.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pack.store.enums.ResultEnums;
import org.pack.store.service.GoodsService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.StringUtil;
import org.pack.store.utils.VerifyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;


    @ApiOperation(value = "菜品列表查询")
    @PostMapping(value = "list",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"storeId\":\"\",\"storeId\":\"分类ID\",\"pageSize\":\"\",\"pageNum\":\"\"}")
    public AppletResult list(@RequestBody JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"page","limit")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
        String storeId = jsonObject.getString("storeId");
        if(StringUtil.isNullStr(storeId)){
            jsonObject.put("storeId","1");
        }
        AppletResult result = goodsService.queryGoodsList(jsonObject);
        return result;
    }

    @ApiOperation(value = "菜品分类添加")
    @PostMapping(value = "addCategory",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"categoryName\":\"\"}")
    public AppletResult addCategory(@RequestBody JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"categoryName")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
       // AppletResult result = goodsService.addCategory(jsonObject);
        return null;
    }

    @ApiOperation(value = "菜品分类修改")
    @PostMapping(value = "editCategory",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"id\":\"\"}")
    public AppletResult editCategory(@RequestBody JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"id")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
       // AppletResult result = goodsCategoryService.editCategory(jsonObject);
        return null;
    }

    @ApiOperation(value = "菜品分类删除")
    @PostMapping(value = "delCategory",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"id\":\"\"}")
    public AppletResult delCategory(@RequestBody JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"id","storeId")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
     //   AppletResult result = goodsCategoryService.delCategory(jsonObject);
        return null;
    }
}
*/
