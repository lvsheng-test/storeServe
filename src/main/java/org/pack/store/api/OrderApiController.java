package org.pack.store.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.pack.store.autoconf.JedisOperator;
import org.pack.store.autoconf.RabbitConfig;
import org.pack.store.common.rabbitmq.producer.RabbitMqSender;
import org.pack.store.requestVo.AddressReq;
import org.pack.store.requestVo.AppVO;
import org.pack.store.requestVo.BindMemberReq;
import org.pack.store.requestVo.ParentCodeReq;
import org.pack.store.service.OrderService;
import org.pack.store.service.UserService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.StringUtil;
import org.pack.store.utils.VerifyUtils;
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
        rabbitMqSender.senderMq(RabbitConfig.CREATE_RDER,data.toJSONString());
        return ResultUtil.success("下单成功");
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
     * 参数：data层：{"orderId":"订单号","amount":"支付金额","remark":"备注"}
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

}
