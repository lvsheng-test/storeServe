package org.pack.store.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pack.store.enums.ResultEnums;
import org.pack.store.service.GoodsService;
import org.pack.store.utils.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private UploadUtil uploadUtil;


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

    @ApiOperation(value = "图片上传")
    @PostMapping(value = "uploadFile",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"categoryName\":\"\"}")
    public AppletResult uploadFile(@RequestParam MultipartFile file) {
        String url = uploadUtil.UploadFile(file);
        return ResultUtil.success(url);
    }

    @ApiOperation(value = "商品添加")
    @PostMapping(value = "addGoods",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"categoryName\":\"\"}")
    public AppletResult addGoods(@RequestBody JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"goodsName")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
        AppletResult result = goodsService.addGoods(jsonObject);
        return result;
    }

    @ApiOperation(value = "商品修改")
    @PostMapping(value = "editGoods",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"id\":\"\"}")
    public AppletResult editGoods(@RequestBody JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"id")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
        AppletResult result = goodsService.editGoods(jsonObject);
        return result;
    }

    @ApiOperation(value = "商品图片查询")
    @PostMapping(value = "queryGoodsPic",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"goodsId\":\"123\"}")
    public AppletResult queryGoodsPic(@RequestBody JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"id")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
        AppletResult result = goodsService.queryGoodsPic(jsonObject.getString("id"));
        return result;
    }

    @ApiOperation(value = "商品删除")
    @PostMapping(value = "delGoods",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"id\":\"\"}")
    public AppletResult delCategory(@RequestBody JSONObject jsonObject) {
        if(!VerifyUtils.isNotBlanks(jsonObject,"id")){
            return ResultUtil.error(ResultEnums.PARAM_IS_NULL.getCode(),ResultEnums.PARAM_IS_NULL.getMsg());
        }
        AppletResult result = goodsService.delGoods(jsonObject);
        return result;
    }
}

