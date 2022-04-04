/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.mgr;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.logging.Logger;

import io.github.kubesys.devfrk.HttpContext;
import io.github.kubesys.devfrk.utils.JsonUtil;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.4
 */
public class ExceptionManager {

	/**
	 * m_logger
	 */
	public static Logger m_logger = Logger.getLogger(ExceptionManager.class.getName());
	
	/**
	 * m_mgr
	 */
	protected static ExceptionManager m_mgr = new ExceptionManager();
	
	/**
	 * handler
	 */
	protected Method handler = null;
	
	/**
	 * object
	 */
	protected Object object = null;
	
	/**
	 * 
	 */
	protected boolean jsonStyle = false;

	/**
	 * 
	 */
	private ExceptionManager() {
		super();
	}
	
	/**
	 * @param handler         handler
	 * @param object          object
	 * @param jsonStyle       true or false
	 */
	public void setValue(Method handler, Object object, boolean jsonStyle) {
		if (this.handler == null && this.object == null) {
			this.handler = handler;
			this.object = object;
			this.jsonStyle = jsonStyle;
		} else {
			m_logger.warning("find mutiple ExceptionHandler, but only one works");
		}
	}
	
	/**
	 * @return         true if has exception handler
	 */
	public boolean hasExceptionHandler() {
		return this.handler != null;
	}
	
	/**
	 * @param context          context
	 * @param throwable        throwable
	 * @return                 string
	 */
	public String getResult (HttpContext context, Throwable throwable) {
		if (handler.getParameterCount() == 0) {
			try {
				return (String) handler.invoke(object);
			} catch (Exception e) {
				return e.getLocalizedMessage();
			}
		}
		Object[] args = new Object[handler.getParameterCount()];
		for (int i = 0; i < handler.getParameters().length; i++) {
			Parameter p = handler.getParameters()[i];
			if (p.getParameterizedType().getTypeName()
					.equals(HttpContext.class.getName())) {
				args[i] = context;
			} else if (p.getParameterizedType().getTypeName()
					.equals(Throwable.class.getName())) {
				args[i] = throwable;
			} else if (p.getParameterizedType().getTypeName()
					.equals(Exception.class.getName())) {
				args[i] = new Exception(throwable);
			} else {
				args[i] = null;
			}
		}
		try {
			Object result = handler.invoke(object, args);
			return jsonStyle ? JsonUtil.getResult(false, result).toPrettyString() : result.toString();
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
	}
	
	/**
	 * @return          singleton
	 */
	public static ExceptionManager singleton() {
		return m_mgr;
	}
}