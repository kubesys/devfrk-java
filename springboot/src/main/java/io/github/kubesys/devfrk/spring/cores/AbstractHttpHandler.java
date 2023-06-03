/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import io.github.kubesys.devfrk.spring.assists.HttpConstants;
import io.github.kubesys.devfrk.spring.exs.HttpFramworkException;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 * The {@code AbstractHttpHandler} class is used to register a servlet handler to {@code HttpHandlerManager}.
 */
public abstract class AbstractHttpHandler implements CommandLineRunner {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(AbstractHttpHandler.class.getName());

	/**
	 * registry
	 */
	@Autowired
	protected HttpHandlerRegistry registry;
	
	
	@Override
	public void run(String... args) throws Exception {

		// A class, which implements AbstractHttpHandler and 
		// mark with ServiceDefinition, goes here. 
		String simplename = getClass().getSimpleName();

		// Our rule is a class must be  ends with 'Service'
		if (!simplename.endsWith(HttpConstants.SERVICE_CLASS_POSTFIX)) {
			m_logger.log(Level.SEVERE, () -> HttpConstants.EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_WRONG_NAME + simplename);
			throw new HttpFramworkException(300, "注册服务的类型名必须以'Service'结尾" + simplename);
		}

		// Class to HttpHandler 
		registerHttpHandler(simplename);
	}

	/**
	 * If a class named TestService, and has a method demo,
	 * then the url is ../test/demo
	 * 
	 * @param classname                classname
	 * @throws Exception               exception
	 */
	void registerHttpHandler(String classname) throws Exception {

		// 
		String servicePath = getServiceModule(classname);
		
		for (Method serviceName : getClass().getDeclaredMethods()) {

			// The rules for a method is a service is
			// 1. it is just a public method
			if (!Modifier.isPublic(serviceName.getModifiers())
					|| Modifier.isStatic(serviceName.getModifiers())) {
				continue;
			}

			// 2. we do not support polymorphism because of duplicated service names 
			if (registry.contains(serviceName.getName())) {
				m_logger.log(Level.SEVERE, () -> HttpConstants.EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_POLYMORPHISM + classname
						+ "." + serviceName.getName());
				throw new HttpFramworkException(301, "服务重复注册" + classname
						+ "." + serviceName.getName());
			}
			
			// 3. register to <code>HttpHandlerRegistry<code>
			// this url is servicePath/serviceName
			registry.addHttpHandler(servicePath, serviceName);
		}
	}

	
	/**
	 * @param name        classname
	 * @return lowercase
	 */
	String getServiceModule(String classname) {
		String name = classname.substring(0, classname.length() 
				- HttpConstants.SERVICE_CLASS_POSTFIX.length());
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

}
