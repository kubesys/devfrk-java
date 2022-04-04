/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

import io.github.kubesys.devfrk.annotation.Autowired;
import io.github.kubesys.devfrk.annotation.ComponentScan;
import io.github.kubesys.devfrk.annotation.ExceptionHandler;
import io.github.kubesys.devfrk.annotation.RequestMapping;
import io.github.kubesys.devfrk.annotation.ResponseBody;
import io.github.kubesys.devfrk.annotation.RestController;
import io.github.kubesys.devfrk.configs.ServerConfig;
import io.github.kubesys.devfrk.httpserver.RequestMethod;
import io.github.kubesys.devfrk.mgr.BeanManager;
import io.github.kubesys.devfrk.mgr.ExceptionManager;
import io.github.kubesys.devfrk.mgr.HandlerManager;
import io.github.kubesys.devfrk.mgr.ResponseBodyManager;
import io.github.kubesys.devfrk.mgr.UrlTypeManager;
import io.github.kubesys.devfrk.utils.ClassUtil;
import io.github.kubesys.devfrk.utils.ClassUtil.AnnotationClassQuerier;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.1
 */
public class HttpServerRunner {
	
	/**
	 * m_logger
	 */
	static Logger m_logger = Logger.getLogger(HttpServerRunner.class.getName());
	
	/**
	 * m_beanMgr
	 */
	static final BeanManager m_beanMgr = BeanManager.singleton();
	
	/**
	 * m_handlerMgr
	 */
	static final HandlerManager m_handlerMgr = HandlerManager.singleton();
	
	
	/**
	 * serverConfig
	 */
	static ServerConfig serverConfig = (ServerConfig) new HttpServerConfigurator()
								.getConfig("server", ServerConfig.class);
	
	/**
	 * annotations
	 */
	@SuppressWarnings("unchecked")
	static Class<? extends Annotation>[] annotations = new Class[1];
	
	static {
		annotations[0] = RestController.class;
	}

	public static void run(Class<?> clz, String[] args) {
		
		ComponentScan componentScan = clz.getAnnotation(ComponentScan.class);

		AnnotationClassQuerier querier = new ClassUtil(
				getBasePackages(componentScan), annotations).scan(clz.getClassLoader());
		
		for (Class<?> restClass : querier.search(RestController.class)) {
			initAnnotation(restClass);
		}
		
		initAnnotation(clz);
		
		initServer();
			
	}

	/***********************************************************************
	 * 
	 *              init
	 * 
	 ***********************************************************************/
	
	private static void initAnnotation(Class<?> restClass) {
		m_beanMgr.add(restClass);
		
		parseAutowired(restClass);
		
		for (Method method : restClass.getDeclaredMethods()) {
			parseExceptionHandler(restClass, method);
			parseResponseBody(restClass, method); 
			parseRequestMapping(restClass, method);
		}
	}
	
	private static void initServer() {
		HttpServer httpServer = createDefaultHttpServer();
		for (String url : m_handlerMgr.getUrls()) {
			httpServer.createContext(url, m_handlerMgr.getHandler(url));
		}
		httpServer.start();
		m_logger.info("Start httpServer (protocol: http, port: " + serverConfig.getPort() 
					+ ", context: " + serverConfig.getServlet().getContextPath() + ")");
	}

	/***********************************************************************
	 * 
	 *              Lifecycle
	 * 
	 ***********************************************************************/
	
	private static HttpServer createDefaultHttpServer() {
		try {
			return HttpServer.create(new InetSocketAddress(serverConfig.getPort()), 0);
		} catch (IOException e) {
			m_logger.severe("unable to create HttpServer: " + e);
			System.exit(1);
		}
		return null;
	}
	
	private static String[] getBasePackages(ComponentScan componentScan) {
		return componentScan == null ? new String[0] : componentScan.basePackages();
	}
	
	/***********************************************************************
	 * 
	 *             parse Annotations
	 * 
	 ***********************************************************************/
	
	private static void parseAutowired(Class<?> restClass)  {
		for (Field f : restClass.getDeclaredFields()) {
			Autowired autowired = f.getAnnotation(Autowired.class);
			if (autowired != null) {
				if (!m_beanMgr.hasObject(f.getType().getName())) {
					m_beanMgr.add(f.getType());
				}
				f.setAccessible(true);
				try {
					f.set(m_beanMgr.getObject(restClass.getName()), 
							m_beanMgr.getObject(f.getType().getName()));
				} catch (Exception e) {
					m_logger.warning("unable to bind " + restClass.getName() 
									+ "." + f.getName() + ": " + e);
				}
			}
		}
	}

	private static void parseRequestMapping(Class<?> restClasses, Method m) {
		
		String url = serverConfig.getServlet().getContextPath();
		RequestMapping[] rms = restClasses.getAnnotationsByType(RequestMapping.class);
		if (rms == null || rms.length == 0) {
			return;
		}
		
		RequestMapping[] mrms = m.getAnnotationsByType(RequestMapping.class);
		if (mrms.length == 0) {
			return;
		}
		
		String thisUrl = url + rms[0].value()[0] + mrms[0].value()[0];
		
		RequestMethod[] types = mrms[0].method();
		
		if (types == null || types.length == 0 || 
				types[0] == RequestMethod.GET) {
			UrlTypeManager.getUrls.add(thisUrl);
		} else if (types[0] == RequestMethod.POST) {
			UrlTypeManager.postUrls.add(thisUrl);
		} else {
			m_logger.severe("now only support GET and POST");
		}
		
		m_handlerMgr.add(thisUrl, new UrlHandler(
				m_beanMgr.getObject(
						restClasses.getName()), m));
	}

	private static void parseResponseBody(Class<?> restClass, Method m) {
		ResponseBody rb = m.getAnnotation(ResponseBody.class);
		if (rb != null) {
			ResponseBodyManager.singleton().add(
					restClass.getName() + "." + m.getName());
		}
	}

	private static void parseExceptionHandler(Class<?> clz, Method m) {
		ExceptionHandler handler =  m.getAnnotation(ExceptionHandler.class);
		ResponseBody rb = m.getAnnotation(ResponseBody.class);
		if (handler != null) {
			ExceptionManager.singleton().setValue(m, 
					m_beanMgr.getObject(clz.getName()),
					rb == null ? false : true);
		}
	}

}
