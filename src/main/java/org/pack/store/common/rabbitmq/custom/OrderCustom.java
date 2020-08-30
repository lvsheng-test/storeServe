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
@RabbitListener(queues = RabbitConfig.CREATE_RDER, containerFactory = "factory")
public class OrderCustom {

    private static Logger logger = LoggerFactory.getLogger(OrderCustom.class);

    @Resource
    private OrderService orderService;

    @RabbitHandler
    public void process(String message) {
        if(StringUtil.isNotNullStr(message)) {
            orderService.placeOrder(JSONObject.parseObject(message));
        }
    }
}
