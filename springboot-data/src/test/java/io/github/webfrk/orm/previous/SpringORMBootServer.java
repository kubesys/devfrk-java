/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.webfrk.orm.previous;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.github.kubesys.devfrk.spring.HttpServer;
import io.github.kubesys.devfrk.spring.assists.HttpResponse;
import io.github.kubesys.devfrk.spring.defs.DefaultHttpResponse;


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
@ComponentScan(value = { "io.github.webfrk.orm.previous.examples" })
public class SpringORMBootServer extends HttpServer  {

	public static void main(String[] args) {
		SpringApplication.run(SpringORMBootServer.class, args);
	}

	@Bean(name = "resp")
	public HttpResponse getResponse() {
		return new DefaultHttpResponse();
	}
	
}
