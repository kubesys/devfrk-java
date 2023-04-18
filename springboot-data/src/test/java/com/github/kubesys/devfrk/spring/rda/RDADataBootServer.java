/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.devfrk.spring.rda;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
@ComponentScan(value = { "com.github.kubesys.devfrk.spring.rda.services" })
@EntityScan("com.github.kubesys.devfrk.spring.data.models")
public class RDADataBootServer extends HttpServer  {

	public static void main(String[] args) {
		SpringApplication.run(RDADataBootServer.class, args);
	}
}
