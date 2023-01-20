/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.webfrk.orm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import io.github.kubesys.devfrk.spring.HttpServer;
import io.github.kubesys.devfrk.spring.assists.HttpCorsInterceptor;
import io.github.kubesys.devfrk.spring.assists.HttpResponse;
import io.github.kubesys.devfrk.spring.defs.DefaultHttpResponse;
import io.github.webfrk.orm.SpringORMBootServer;


/**
 * @author wuheng@iscas.ac.cn
 * @since  
 * 
 * <p>
 *        The {@code ApplicationServer} class is used for starting web
 *        applications. Please configure
 * 
 *        src/main/resources/application.yml src/main/resources/log4j.properties
 * 
 */
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "io.github" })
//@ComponentScan(basePackages = { "io.github.webfrk.orm" })
public class SpringORMBootServer  {

	public static void main(String[] args) {
		SpringApplication.run(SpringORMBootServer.class, args);
	}

	@Bean(name = "resp")
	public HttpResponse getResponse() {
		return new DefaultHttpResponse();
	}
	
}
