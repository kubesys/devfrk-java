/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 * The {@code RequestHandlerMapper} class is used to dispatch request to the
 * related handler, if the handler is not found, it would throw an exception.
 */
@Component
public class RequestHandlerMapper {


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

	public Method getHttpHandler(String customPath) throws Exception {
		return registry.getHttpHandler(customPath);
	}
	
	public Object execHttpHandler(Method httpHandler, String customPath, JsonNode body) throws Exception {
		Object[] params = validator.validateParameters(body, httpHandler);
		return (params != null) ? httpHandler.invoke(getInstance(customPath), params)
				: httpHandler.invoke(getInstance(customPath));
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
