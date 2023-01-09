/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wuheng@iscas.ac.cn
 * @since  1.1.0
 * 
 * The {@code HttpContext} class is used to get a specified bean.
 */
@Component
public class HttpContext implements ApplicationContextAware {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(HttpContext.class.getName());

	/**
	 * handlers
	 */
	@Autowired
	protected HttpHandlerManager handlers;
	
	/**
	 * ctx
	 */
	protected ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (ctx == null) {
			ctx = applicationContext;
		}
	}
	
	/**
	 * @param servletPath                         path
	 * @return                                    obj
	 * @throws Exception                          exception
	 */
	public Object getInstance(String servletPath) throws Exception {
		String name = handlers.getHandler(servletPath).getDeclaringClass().getSimpleName();
		return ctx.getBean(name.substring(0, 1).toLowerCase() + name.substring(1));
	}
	
	/**
	 * @param name                                name
	 * @return                                    obj
	 * @throws Exception                          exception
	 */
	public Object getBean(String name) throws Exception {
		return ctx.getBean(name);
	}
	
}
