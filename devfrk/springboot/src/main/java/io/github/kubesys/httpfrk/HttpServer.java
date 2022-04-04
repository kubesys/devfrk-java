/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.httpfrk;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.kubesys.httpfrk.cores.HttpCorsInterceptor;
import io.github.kubesys.httpfrk.cores.HttpResponse;
import io.github.kubesys.httpfrk.defs.DefaultHttpResponse;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since 2.2.3
 * 
 *        <p>
 *        The {@code ApplicationServer} class is used for starting web
 *        applications. Please configure
 * 
 *        src/main/resources/application.yml src/main/resources/log4j.properties
 * 
 */

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "io.github.kubesys.httpfrk" })
public abstract class HttpServer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// support cross-origin resource sharing
		registry.addInterceptor(new HttpCorsInterceptor()).addPathPatterns("/**");
	}

	@Bean(name = "resp")
	public HttpResponse getResponse() {
		return new DefaultHttpResponse();
	}

}
