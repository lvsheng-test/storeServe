package org.pack.store.common.rabbitmq.custom;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.autoconf.RabbitConfig;
import org.pack.store.service.OrderService;
import org.pack.store.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RabbitListener(queues = RabbitConfig.ORDER_CANCEL_DELAY, containerFactory = "factory")
public class CancelOrderCustom {

    private static Logger logger = LoggerFactory.getLogger(CancelOrderCustom.class);

    @Resource
    private OrderService orderService;

    @RabbitHandler
    public void process(String message) {
            JSONObject jsonObject = JSONObject.parseObject(message);
            if(null != jsonObject) {
                String orderId = jsonObject.getString("orderId");
                String openId = jsonObject.getString("openId");
                orderService.cancelOrder(orderId, openId);
            }else{
                logger.error("==>待支付订单自动取消队列缺少参数={}",message);
            }
    }
}
