package org.pack.store;

import org.springframework.web.filter.CorsFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
@MapperScan(basePackages = { "org.pack.store.mapper" })
//@EnableSchedulinguling
//@EnableTransactionManagement
public class StoreServeApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoreServeApplication.class, args);
	}


	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*"); //允许任何域名
		corsConfiguration.addAllowedHeader("*"); //允许任何头
		corsConfiguration.addAllowedMethod("*"); //允许任何方法
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig()); //注册
		return new CorsFilter(source);
	}
}
