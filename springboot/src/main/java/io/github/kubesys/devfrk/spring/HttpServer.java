/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.kubesys.devfrk.spring.addons.HttpCorsInterceptor;
import io.github.kubesys.devfrk.spring.constants.BeanConstants;
import io.github.kubesys.devfrk.spring.resp.DefaultHttpResponse;
import io.github.kubesys.devfrk.spring.resp.HttpResponse;

/**
 * @author  wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since   2023/06/28
 * 
 *  
 * 服务启动框架，进一步配置可参见
 * 
 * <li><code>src/main/resources/application.yml<code>
 * <li><code>src/main/resources/log4j.properties<code>
 * 
 */

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value = { "io.github.kubesys.devfrk.spring" })
public abstract class HttpServer implements WebMvcConfigurer {

	/**
	 * 支持跨域
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpCorsInterceptor()).addPathPatterns("/**");
	}

	/**
	 * 可以通过Bean(name = "resp")自定义
	 * 
	 * @return 默认请求响应实现
	 */
	@Bean(name = BeanConstants.RESPONSE)
	public HttpResponse getResponse() {
		return new DefaultHttpResponse();
	}

}
