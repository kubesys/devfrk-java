/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.webfrk.plus;


import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import io.github.kubesys.devfrk.spring.HttpServer;


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
@ComponentScan(value = { "io.github.webfrk.plus.examples" })
public class SpringPlusBootServer extends HttpServer  {

	public static void main(String[] args) {
		SpringApplication.run(SpringPlusBootServer.class, args);
	}

	
}
