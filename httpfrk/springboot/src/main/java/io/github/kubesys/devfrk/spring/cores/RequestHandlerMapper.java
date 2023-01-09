/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.devfrk.spring.cores.HandlerParameterValidator.ValidationResult;
import io.github.kubesys.devfrk.spring.utils.JavaUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 * The {@code HttpHandlerForwarder} class is used to dispatch request to the
 * related handler, if the handler is not found, it would throw an exception.
 */
@Component
public class RequestHandlerMapper {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(RequestHandlerMapper.class.getName());

	/**
	 * handler means how to deal with the request for specified servletPath
	 */
	@Autowired
	protected HttpHandlerRegistry registry;
	
	
	@Autowired
	protected HandlerParameterValidator validator;
	
	@Resource
	protected ApplicationContext ctx;

	public String getCustomPath(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length() + 1);
	}

	public Object getAndExecHttpHandler(String customPath, JsonNode body) throws Exception {
		Method httpHanlder = registry.getHttpHandler(customPath);
		Object[] params = getParameters(body, httpHanlder);
		return (params != null) ? httpHanlder.invoke(getInstance(customPath), params)
				: httpHanlder.invoke(getInstance(customPath));
	}
	
	/**
	 * @param body                             body
	 * @param targetMethod                     target
	 * @return                                 objects 
	 * @throws Exception                       exception
	 */
	protected Object[] getParameters(JsonNode body, Method targetMethod) throws Exception {

		int parameterCount = targetMethod.getParameterCount();
		Object[] params = (parameterCount == 0) ? null : new Object[parameterCount];
		for (int i = 0; i < parameterCount; i++) {
			String name = targetMethod.getParameters()[i].getName();
			if (!body.has(name)) {
				String typeName = targetMethod.getParameters()[i].getType().getName();
				if (JavaUtils.isPrimitive(typeName) && !typeName.equals(String.class.getName())) {
					params[i] = 0;
				} else {
					params[i] = null;
				}
			} else {
				params[i] = new ObjectMapper().readValue(body.get(name).toPrettyString(),
					targetMethod.getParameterTypes()[i]);
			}
			
			checkParameter(params, i);
		}
		return params;

	}
	
	/**
	 * @param params                              params
	 * @param i                                   i
	 * @throws Exception                          exception
	 */
	protected void checkParameter(Object[] params, int i) throws Exception {
		ValidationResult result = validator.validateEntity(params[i]);
		if (result.isHasErrors()) {
			throw new Exception(new ObjectMapper().writeValueAsString(result.getErrorMsg()));
		}
	}
	
	/**
	 * @param customPath                          path
	 * @return                                    obj
	 * @throws Exception                          exception
	 */
	public Object getInstance(String customPath) throws BeansException {
		String name = registry.getHttpHandler(customPath).getDeclaringClass().getSimpleName();
		return ctx.getBean(name.substring(0, 1).toLowerCase() + name.substring(1));
	}
}
