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
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.spring.constants.HttpConstants;
import io.github.kubesys.devfrk.spring.exs.HttpFramworkException;
import io.github.kubesys.devfrk.spring.utils.JSONUtils;
import io.github.kubesys.devfrk.spring.utils.JavaUtils;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;

/**
 * @author   wuheng@iscas.ac.cn
 * @version  2.3.0
 * @since    2023/06/28
 * 
 * 注册Http服务
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
	
	@Autowired
	protected HttpOpenapiGenerator openapi;
	
	
	public static final Set<String> ignores = new HashSet<>();
	
	static {
		ignores.add("run");
		ignores.add("wait");
		ignores.add("notify");
		ignores.add("equals");
		ignores.add("toString");
		ignores.add("notifyAll");
		ignores.add("hashCode");
		ignores.add("getClass");
	}
	
	
	@Override
	public void run(String... args) throws Exception {

		// A class, which implements AbstractHttpHandler and 
		// mark with ServiceDefinition, goes here. 
		String simplename = getClass().getSimpleName();

		// Our rule is a class must be  ends with 'Service'
		if (!simplename.endsWith(HttpConstants.SERVICE_CLASS_POSTFIX)) {
			m_logger.log(Level.SEVERE, () -> ExceptionConstants.EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_WRONG_NAME + simplename);
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
		
		for (Method serviceName : getClass().getMethods()) {

			// The rules for a method is a service is
			// 1. it is just a public method
			if (!Modifier.isPublic(serviceName.getModifiers())
					|| Modifier.isStatic(serviceName.getModifiers())
					|| ignores.contains(serviceName.getName())) {
				continue;
			}
			

			// 2. we do not support polymorphism because of duplicated service names 
			if (registry.contains(serviceName.getName())) {
				m_logger.log(Level.SEVERE, () -> ExceptionConstants.EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_POLYMORPHISM + classname
						+ "." + serviceName.getName());
				throw new HttpFramworkException(301, "服务重复注册" + classname
						+ "." + serviceName.getName());
			}
			
			// 3. register to <code>HttpHandlerRegistry<code>
			// this url is servicePath/serviceName
			registry.addHttpHandler(servicePath, serviceName);

			PathItem item = new PathItem();
			String url = "/" + servicePath + "/" + serviceName.getName();
			
			String httpType = openapi.getType(url);
			item.description("");
			
			Operation operation = new Operation();
			if ("GET".equals(httpType)) {
				operation.parameters(params(serviceName));
				item.setGet(operation);
			} else if ("POST".equals(httpType)) {
				operation.requestBody(requestBody(serviceName));
				item.setPost(operation);
			} else if ("PUT".equals(httpType)) {
				operation.requestBody(requestBody(serviceName));
				item.setPut(operation);
			} else if ("DELETE".equals(httpType)) {
				operation.requestBody(requestBody(serviceName));
				item.setDelete(operation);
			} 
			openapi.addPath(url, item );
		}
	}

	List<io.swagger.v3.oas.models.parameters.Parameter> params(Method serviceName) throws Exception {
		List<io.swagger.v3.oas.models.parameters.Parameter> list = new ArrayList<>();
		for (Parameter p : serviceName.getParameters()) {
			io.swagger.v3.oas.models.parameters.Parameter param = new io.swagger.v3.oas.models.parameters.Parameter();
			param.setName(p.getName());
			param.setIn("query");
			Schema<Object> schema = new Schema<Object>();
			schema.setType(p.getType().getTypeName());
			schema.setDefault(JavaUtils.m_values.get(p.getType().getTypeName()));
			param.setSchema(schema );
		}
		return list;
	}
	
	RequestBody requestBody(Method serviceName) throws Exception {
		RequestBody body = new RequestBody();
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode json = objectMapper.createObjectNode();
		for (Parameter p : serviceName.getParameters()) {
			Type paramType = p.getParameterizedType();
			if (JavaUtils.isBool(paramType)) {
				json.put(p.getName(), true);
			} else if (JavaUtils.isChar(paramType) 
					|| JavaUtils.isString(paramType)) {
				json.put(p.getName(), "string");
			} else if (JavaUtils.isDouble(paramType)
					|| JavaUtils.isFloat(paramType)
					|| JavaUtils.isInt(paramType)
					|| JavaUtils.isShort(paramType)) {
				json.put(p.getName(), 0);
			} else if (JavaUtils.isStringList(paramType)
					|| JavaUtils.isStringSet(paramType)) {
				List<String> list = new ArrayList<>();
				list.add("string");
				list.add("string");
				json.set(p.getName(), JSONUtils.from(list));
			} else if (JavaUtils.isStringStringMap(paramType)) {
				Map<String, String> map = new HashMap<>();
				map.put("string", "string");
				json.set(p.getName(), JSONUtils.from(map));
			} else if (JavaUtils.isStringObjectMap(paramType)) {
				List<Object> list = new ArrayList<>();
				list.add(JSONUtils.fillObject(paramType));
				list.add(JSONUtils.fillObject(paramType));
				json.set(p.getName(), JSONUtils.from(list));
			} else if (JavaUtils.isObjectList(paramType) 
					|| JavaUtils.isObjectSet(paramType)) {
				Map<String, Object> map = new HashMap<>();
				map.put("string", JSONUtils.fillObject(paramType));
				json.set(p.getName(), JSONUtils.from(map));
			} else {
				json.set(p.getName(), JSONUtils.fillObject(paramType));
			}
		}
		Content content = new Content();
		MediaType mediaType = new MediaType();
		Example example = new Example();
		example.setValue(json.toPrettyString());
		mediaType.addExamples("application/json", example );
		content.put("application/json", mediaType );
		body.setContent(content );
		return body;
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
