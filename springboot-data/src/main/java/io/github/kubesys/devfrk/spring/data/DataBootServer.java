/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.data;


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
@ComponentScan(value = { "io.github.kubesys.devfrk.spring.data.services" })
@EnableJpaRepositories(basePackages = "io.github.kubesys.devfrk.spring.data.daos")
@EntityScan("io.github.kubesys.devfrk.spring.data.models")
public class DataBootServer extends HttpServer  {

	public static void main(String[] args) {
		SpringApplication.run(DataBootServer.class, args);
	}
}
