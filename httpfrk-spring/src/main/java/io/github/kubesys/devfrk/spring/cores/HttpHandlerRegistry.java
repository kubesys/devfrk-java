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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.spring.constants.LogConstants;

/**
 * @author  wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since   2023/06/28
 * 
 * The {@code HttpHandlerRegistry} class is used to save and get servlet handlers.
 */
@Component
public final class HttpHandlerRegistry  {

	/**
	 * logger
	 */
	static final Logger m_logger = Logger.getLogger(HttpHandlerRegistry.class.getName());
	
	/**
	 * http处理器集合
	 */
	static Map<String, Method> httpHandlers = new LinkedHashMap<>();
	
	
	/**
	 * 文件名分隔符
	 */
	static final String URL_PATH_SEPARATOR    = "/";
	
	
	/**
	 * 根据Class名和方法名注册一个HttpHandler,
	 * 这里Class命名必须以Service结尾，如SystemService，且需要实现<code>AbstractHttpHandler</code>
	 * 而且类名上必须标记@ServiceDefinition
	 * 此时如何方法是public开头，且符合方法名命名规范，见docs/spec.md
	 * 如方法名是public void createOne()，则注册的路径为/system/createOne
	 * 
	 * 注意：暂时不支持或者无法区分方法重载，针对该情况，后注册的会替代先注册的
	 * 
	 * @param serviceModule   service module
	 * @param serviceName     service name
	 */
	public void addHttpHandler(String serviceModule, Method serviceName) {
		String customPath = serviceModule + URL_PATH_SEPARATOR + serviceName.getName();
		httpHandlers.put(customPath, serviceName);
		m_logger.log(Level.INFO, () -> LogConstants.SUCESSFUL_REGISTER_HTTPHANDLER + customPath);
	}

	/**
	 * 输入path（如/system/createOne，返回HttpHandler）
	 * 
	 * @param customPath   servlet
	 * @return              the related method
	 * @throws Exception    exception
	 */
	public Method getHttpHandler(String customPath) throws BeansException {
		if (!httpHandlers.containsKey(customPath)) {
			m_logger.log(Level.SEVERE, () -> ExceptionConstants.INVALID_REQUEST_URL);
			throw new NoSuchBeanDefinitionException(ExceptionConstants.INVALID_REQUEST_URL);
		}
		return httpHandlers.get(customPath);
	}

	/**
	 * 是否已经注册过path
	 * 
	 * @param servletPath   servlet
	 * @return              true or false
	 */
	public boolean contains(String servletPath) {
		return httpHandlers.containsKey(servletPath);
	}
}
