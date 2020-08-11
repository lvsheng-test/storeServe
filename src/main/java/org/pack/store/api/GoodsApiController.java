package org.pack.store.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.pack.store.requestVo.GoodsTypeReq;
import org.pack.store.requestVo.PageInfoReq;
import org.pack.store.service.GoodsCategoryService;
import org.pack.store.service.GoodsService;
import org.pack.store.utils.AppletResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 提供小程序API
 */
@Api(value="GoodsApiController",tags={"小程序商品操作API"})
@RestController
@RequestMapping(value = "/goodsApi")
public class GoodsApiController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Resource
    private GoodsService goodsService;

    @CrossOrigin
    @ApiOperation(value = "获取首页商品分类信息列表")
    @GetMapping(value = "queryGoodsTypeList")
    public AppletResult queryGoodsTypeList(){
        return goodsCategoryService.queryGoodsTypeListByShow();
    }

    @CrossOrigin
    @ApiOperation(value = "获取首页商品猜你喜欢信息列表")
    @PostMapping(value = "queryGoodsLike")
    public AppletResult queryGoodsLike(@RequestBody @ApiParam(name="分页对象",value="传入json格式") PageInfoReq pageInfoReq){
        return goodsService.queryGoodsLike(pageInfoReq);
    }

    @CrossOrigin
    @ApiOperation(value = "获取商品分类信息列表")
    @PostMapping(value = "queryGoodsType")
    public AppletResult queryGoodsType(){
        return goodsCategoryService.queryGoodsType();
    }

    @CrossOrigin
    @ApiOperation(value = "根据商品类别查询对应商品列表信息")
    @PostMapping(value = "queryGoodsInfoListByTypeId")
    public AppletResult queryGoodsInfoListByTypeId(@RequestBody @ApiParam(name="商品分类查询对象",value="传入json格式") GoodsTypeReq goodsTypeReq){
        return goodsService.queryGoodsInfoListByTypeId(goodsTypeReq);
    }

    @CrossOrigin
    @ApiOperation(value = "查询商品详情信息")
    @GetMapping(value = "queryGoodsDetails")
    public AppletResult queryGoodsDetails(){
        return null;
    }


    @CrossOrigin
    @ApiOperation(value = "条件搜索商品信息")
    @GetMapping(value = "searchGoodsInfoList")
    public AppletResult searchGoodsInfoList(){
        return null;
    }




}
