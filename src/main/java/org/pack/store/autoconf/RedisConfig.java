package org.pack.store.autoconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	

	@Bean(name = "spring.redis.pool")
	public JedisPool jedisPool(@Qualifier("spring.redis.pool.config") JedisPoolConfig config,
			@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") int port) {
		JedisPool pool = new JedisPool(config, host, port);
		logger.info("init redis...");
		return pool;
	}

	@Bean(name = "spring.redis.pool.config")
	public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.pool.max-active}") int maxActive,
			@Value("${spring.redis.pool.max-idle}") int maxIdle,
			@Value("${spring.redis.pool.max-wait}") int maxWaitMillis) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxActive);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		return config;
	}
	
}
