package org.pack.store.common.rabbitmq.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 生产者
 */
@Component
public class RabbitMqSender {

	@Autowired
	@Qualifier("rabbitTemplate")
	private AmqpTemplate rabbitTemplate;

	/**
	 * @param:queueName 队列名称
	 * @param:content 消息内容
	 */
	public void senderMq(String queueName, String content) {
		rabbitTemplate.convertAndSend(queueName, content);
	}

	/**
	 * 延迟队列
	 * @param queue
	 * @param content
	 * @param time
	 */
	public void queueDealy(String queue, Object content, final int time) {
		MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setExpiration(String.valueOf(time * 1000));
				return message;
			}
		};
		rabbitTemplate.convertAndSend(queue,content,messagePostProcessor);
	}
}
