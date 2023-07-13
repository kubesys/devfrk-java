/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.devfrk.data.search;


import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

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
@ComponentScan(value = { "io.github.devfrk.data.search" })
@EnableElasticsearchRepositories(basePackages = "io.github.devfrk.data.search.daos")
public class SearchBootServer extends HttpServer  {

	public static void main(String[] args) {
		SpringApplication.run(SearchBootServer.class, args);
	}

}
