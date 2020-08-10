package org.pack.store.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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


}
