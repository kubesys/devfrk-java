/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import io.github.kubesys.devfrk.spring.assists.HttpConstants;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 * The {@code HttpHandlerRegistry} class is used to save and get servlet handlers.
 */
@Component
public final class HttpHandlerRegistry  {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(HttpHandlerRegistry.class.getName());
	
	/**
	 * handlers
	 */
	protected static Map<String, Method> httpHandlers = new HashMap<>();
	

	/**
	 * register a HttpHandler, and if it register a duplicated HttpHandler,
	 * we may automatically delete the existing one.
	 * 
	 * @param serviceModule   service module
	 * @param serviceName     service name
	 */
	public void addHttpHandler(String serviceModule, Method serviceName) {
		String customPath = serviceModule + HttpConstants.URL_PATH_SEPARATOR + serviceName.getName();
		httpHandlers.put(customPath, serviceName);
		m_logger.log(Level.INFO, () -> HttpConstants.SUCESSFUL_REGISTER_HTTPHANDLER + customPath);
	}

	/**
	 * get a HttpHandler
	 * 
	 * @param customPath   servlet
	 * @return              the related method
	 * @throws Exception    exception
	 */
	public Method getHttpHandler(String customPath) throws BeansException {
		if (!httpHandlers.containsKey(customPath)) {
			m_logger.log(Level.SEVERE, () -> HttpConstants.EXCEPTION_INVALID_REQUEST_URL);
			throw new NoSuchBeanDefinitionException(HttpConstants.EXCEPTION_INVALID_REQUEST_URL);
		}
		return httpHandlers.get(customPath);
	}

	/**
	 * @param servletPath   servlet
	 * @return              true or false
	 */
	public boolean contains(String servletPath) {
		return httpHandlers.containsKey(servletPath);
	}
		
}
