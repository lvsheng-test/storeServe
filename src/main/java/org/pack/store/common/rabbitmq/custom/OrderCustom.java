package org.pack.store.common.rabbitmq.custom;

import org.pack.store.autoconf.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.CREATE_RDER, containerFactory = "factory")
public class OrderCustom {

    private static Logger logger = LoggerFactory.getLogger(OrderCustom.class);

    @RabbitHandler
    public void process(String message) {

    }
}
