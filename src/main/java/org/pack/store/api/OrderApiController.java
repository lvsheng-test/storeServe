package org.pack.store.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.pack.store.autoconf.JedisOperator;
import org.pack.store.autoconf.RabbitConfig;
import org.pack.store.common.rabbitmq.producer.RabbitMqSender;
import org.pack.store.requestVo.*;
import org.pack.store.service.OrderService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.IDGenerateUtil;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 提供小程序API
 */
@Api(value="OrderApiController",tags={"小程序订单操作API"})
@RestController
@RequestMapping(value = "/orderApi")
public class OrderApiController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private JedisOperator jedisOperator;
    @Autowired
    private RabbitMqSender rabbitMqSender;

    @Autowired
    private IDGenerateUtil iDGenerateUtil;

    @CrossOrigin
    @ApiOperation(value = "下单")
    @PostMapping(value = "/placeOrder")
    public AppletResult placeOrder(@RequestBody AppVO<JSONObject> appVo){
        JSONObject data = appVo.getData();
        String openId = jedisOperator.get(appVo.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        data.put("openId",openId);
        String orderId = iDGenerateUtil.getId();
        data.put("orderId",orderId);
        rabbitMqSender.senderMq(RabbitConfig.CREATE_RDER,data.toJSONString());
        JSONObject order = appVo.getData();
        order.put("orderId",orderId);
        return ResultUtil.success(order);
    }

    @CrossOrigin
    @ApiOperation(value = "查询订单详情")
    @PostMapping(value = "/orderDetail")
    public AppletResult orderDetail(@RequestBody AppVO<JSONObject> appVo){
        JSONObject data = appVo.getData();
        String openId = jedisOperator.get(appVo.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        data.put("openId",openId);
        JSONObject jsonObject = orderService.getByOrderId(data);
        return ResultUtil.success(jsonObject);
    }

    /**
     * AppVO层与其他接口一致（token必传）
     * 参数：data层：{"orderId":"订单号","amount":"支付金额","payMode":"支付方式(0:会员卡，1:微信)","remark":"备注"}
     */
    @CrossOrigin
    @ApiOperation(value = "支付扣款")
    @PostMapping(value = "/payAccount")
    @ApiImplicitParam(value = "{\"orderId\":\"订单号\",\"amount\":\"支付金额\",\"remark\":\"备注\"}")
    public AppletResult payAccount(@RequestBody AppVO<JSONObject> jsonObject){
        JSONObject data = jsonObject.getData();
        String openId = jedisOperator.get(jsonObject.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        data.put("openId",openId);
        AppletResult appletResult = orderService.payAccount(data);
        return appletResult;
    }

    @CrossOrigin
    @ApiOperation(value = "去结算接口")
    @PostMapping(value = "/goSettlement")
    public AppletResult goSettlement(@RequestBody @ApiParam(name="用户TOKEN对象",value="传入json格式",required = true) UserTokenReq userTokenReq){
        String openId = jedisOperator.get(userTokenReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return orderService.goSettlement(userTokenReq);
    }

    @CrossOrigin
    @ApiOperation(value = "查询订单列表信息接口")
    @PostMapping(value = "/queryOrderListAll")
    public AppletResult queryOrderListAll(@RequestBody @ApiParam(name="ORDER查询对象",value="传入json格式",required = true) OrderSerchReq orderSerchReq){
        String openId = jedisOperator.get(orderSerchReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return orderService.queryOrderListAll(orderSerchReq,openId);
    }

    @CrossOrigin
    @ApiOperation(value = "删除订单接口")
    @PostMapping(value = "/doDeleteOrder")
    public AppletResult doDeleteOrder(@RequestBody @ApiParam(name="DEL查询对象",value="传入json格式",required = true) OrderReq orderReq){
        String openId = jedisOperator.get(orderReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return orderService.doDeleteOrder(orderReq.getOrderId(),openId);
    }

    @CrossOrigin
    @ApiOperation(value = "到店自取接口")
    @PostMapping(value = "/goToStore")
    public AppletResult goToStore(@RequestBody @ApiParam(name="自取信息对象",value="传入json格式",required = true) GoStoreReq goStoreReq){
        String openId = jedisOperator.get(goStoreReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return orderService.goToStore(goStoreReq);
    }

    @CrossOrigin
    @ApiOperation(value = "查询自取时间接口")
    @PostMapping(value = "/querySelfTime")
    public AppletResult querySelfTime(){
        return orderService.querySelfTime();
    }

}
