package org.pack.store.autoconf;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitMQ配置
 */
@Configuration
public class RabbitConfig {

	/**
	 * 下单
	 */
	public static final String CREATE_RDER = "create_order";

	/**
	 * 订单5分钟自动取消
	 */
	public static final String ORDER_CANCEL = "order_cancel";

	/**
	 * 订单5分钟自动取消
	 */
	public static final String ORDER_CANCEL_DELAY = "order_cancel_delay";
	
	/**
	 * 每个消费者获取最大投递数量 (默认50)
	 */
	public static final int DEFAULT_PREFETCH_COUNT = 100;

	/**
	 * 默认的线程数
	 */
	public static final int DEFAULT_CONCURRENT = 5;

	@Value("${spring.rabbitmq.ip}")
	private String ip;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Bean(name = "connectionFactory")
	@Primary
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(ip);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setPublisherConfirms(true);
		return connectionFactory;
	}

	@Bean(name = "rabbitTemplate")
	@Primary
	public RabbitTemplate rabbitTemplate(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}

	@Bean(name = "factory")
	public SimpleRabbitListenerContainerFactory factory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
			@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setPrefetchCount(DEFAULT_PREFETCH_COUNT);
		factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	@Bean
	public Queue thirdQueue() {
		return new Queue(CREATE_RDER, true);
	}

	@Bean
	public Queue waitQueue() {
		return new Queue(ORDER_CANCEL_DELAY, true, false, false);
	}

	@Bean
	public Queue waitHandleQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", "");
		arguments.put("x-dead-letter-routing-key", ORDER_CANCEL_DELAY);
		return new Queue(ORDER_CANCEL, true, false, false, arguments);
	}
}
