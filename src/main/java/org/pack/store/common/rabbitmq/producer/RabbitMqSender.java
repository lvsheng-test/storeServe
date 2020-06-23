/*package org.pack.store.common.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

*//**
 * 生产者
 *//*
@Component
public class RabbitMqSender {

	@Autowired
	@Qualifier("rabbitTemplate")
	private AmqpTemplate rabbitTemplate;

	*//**
	 * @param queueName 队列名称
	 * @param content 消息内容
	 *//*
	public void senderMq(String queueName, String content) {
		rabbitTemplate.convertAndSend(queueName, content);
	}
}
*/