package org.pack.store.controller;

import io.swagger.annotations.ApiOperation;
import org.pack.store.requestVo.OrderListReq;
import org.pack.store.requestVo.OrderReq;
import org.pack.store.service.OrderService;
import org.pack.store.utils.AppletResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.Lv
 * 后台管理系统订单模块
 * @Date 2020-10-10
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin
    @ApiOperation(value = "订单列表查询")
    @PostMapping(value = "queryOrderList")
    public AppletResult queryOrderList(@RequestBody OrderListReq orderListReq){
        return orderService.queryOrderList(orderListReq);
    }

    @CrossOrigin
    @ApiOperation(value = "分配骑手派送订单")
    @PostMapping(value = "doAllocationHorseman")
    public AppletResult doAllocationHorseman(@RequestBody OrderReq orderReq){
        return orderService.doAllocationHorseman(orderReq);
    }

    @CrossOrigin
    @ApiOperation(value = "后台订单确认收货操作")
    @PostMapping(value = "doConfirmationCompletion")
    public AppletResult doConfirmationCompletion(@RequestBody OrderReq orderReq){
        return orderService.doConfirmationCompletion(orderReq);
    }


}
