/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.devfrk.data;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
@ComponentScan(value = { "io.github.devfrk.data.services" })
@EnableJpaRepositories(basePackages = "io.github.devfrk.data.daos")
@EntityScan("io.github.devfrk.data.models")
public class SpringPlusBootServer extends HttpServer  {

	public static void main(String[] args) {
		SpringApplication.run(SpringPlusBootServer.class, args);
	}

	
}
