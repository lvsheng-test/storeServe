package org.pack.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableRetry
@SpringBootApplication
@MapperScan(basePackages = { "org.pack.store.mapper" })
@EnableScheduling
@EnableTransactionManagement
public class StoreServeApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoreServeApplication.class, args);
	}
}
