/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
	protected static Map<String, Method> servletHandlers = new HashMap<String, Method>();
	
	/**
	 * services 
	 */
	protected final static Set<String> services = new HashSet<String>();

	/**
	 * @param serviceModule   service module
	 * @param serviceName     service name
	 */
	public void addHandler(String serviceModule, Method serviceName) {
		String servicePath = serviceModule + "/" + 
				serviceName.getName();
		servletHandlers.put(servicePath, serviceName);
		services.add(servicePath);
	}

	/**
	 * @param servletPath   servlet
	 * @return              the related method
	 * @throws Exception    exception
	 */
	public Method getHandler(String servletPath) throws BeansException {
		if (!servletHandlers.containsKey(servletPath)) {
			throw new NoSuchBeanDefinitionException(HttpConstants.EXCEPTION_INVALID_REQUEST_URL);
		}
		return servletHandlers.get(servletPath);
	}

	/**
	 * @param servletPath   servlet
	 * @return              true or false
	 */
	public boolean contain(String servletPath) {
		return servletHandlers.containsKey(servletPath);
	}
		
}
