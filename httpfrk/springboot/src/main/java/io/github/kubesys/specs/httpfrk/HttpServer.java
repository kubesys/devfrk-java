/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.specs.httpfrk;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.kubesys.specs.httpfrk.cores.HttpCorsInterceptor;
import io.github.kubesys.specs.httpfrk.cores.HttpResponse;
import io.github.kubesys.specs.httpfrk.defs.DefaultHttpResponse;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since 1.1.0
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
@ComponentScan(basePackages = { "io.github.kubesys.specs.httpfrk" })
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
