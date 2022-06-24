/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.specs.httpfrk.cores;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.2.3
 * 
 * The {@code HttpHandler} class is used to register a servlet handler to {@code HttpHandlerManager}.
 */
public abstract class HttpHandler implements CommandLineRunner {

	/**
	 * logger
	 */
	public final static Logger m_logger = Logger.getLogger(HttpController.class.getName());

	
	/**
	 * handers
	 */
	@Autowired
	protected HttpHandlerManager handlers;
	
	/**********************************************************
	 * 
	 * 
	 * 
	 **********************************************************/

	
	@Override
	public void run(String... args) throws Exception {

		String simplename = getClass().getSimpleName();

		// if a simplename ends with 'Service', the class includes some services
		if (!simplename.endsWith(HttpConstants.POSTFIX_SERVICE)) {
			m_logger.severe(HttpConstants.EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_WRONG_NAME + simplename);
			throw new Exception(HttpConstants.EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_WRONG_NAME + simplename);
		}

		// register services
		registerService(simplename);
	}

	/**
	 * @param classname                classname
	 * @throws Exception               exception
	 */
	private void registerService(String classname) throws Exception {

		// In our design, a method name of a class is a service name.
		// Now we unsupport polymorphism to deal with duplicated method names 
		String serviceModule = getServiceModule(classname);
		
		for (Method service : getClass().getDeclaredMethods()) {

			// The rules (a method is a service) include
			
			// 1. it is a public method
			if (!Modifier.isPublic(service.getModifiers())
					|| Modifier.isStatic(service.getModifiers())) {
				continue;
			}

			// 2. filter duplicated services
			if (HttpHandlerManager.services.contains(service.getName())) {
				m_logger.severe(HttpConstants.EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_POLYMORPHISM + classname
						+ "." + service.getName());
				throw new Exception(HttpConstants.EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_POLYMORPHISM + classname
						+ "." + service.getName());
			}
			
								
			// 3. register to <code>HttpHandlerManager.addHandler<code>
			handlers.addHandler(serviceModule, service);
			HttpHandlerManager.services.add(service.getName());
			m_logger.info("servelet path '" + serviceModule 
						+ "/" + service.getName() + "' registered sucessful.");
				
		}
	}

	

	/**
	 * @param name object name
	 * @return lowercase
	 */
	private static String getServiceModule(String classname) {
		String name = classname.substring(0, classname.length() 
				- HttpConstants.POSTFIX_SERVICE.length());
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
	

}
