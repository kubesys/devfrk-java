/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.orm;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import io.github.kubesys.devfrk.spring.cores.HttpHandlerRegistry;


/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 * 
 */

@Component
public class OrmRepoRegistry {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(HttpHandlerRegistry.class.getName());
	
	/**
	 * handlers
	 */
	protected static Map<String, OrmRepository<OrmEntity,Integer>> repoHub = new HashMap<>();
	
	/**
	 * get a HttpHandler
	 * 
	 * @param customPath   servlet
	 * @return              the related method
	 * @throws Exception    exception
	 */
	public OrmRepository<OrmEntity,Integer> getRepository(Class<?> clz) throws BeansException {
		return repoHub.get(clz.getName());
	}

}
