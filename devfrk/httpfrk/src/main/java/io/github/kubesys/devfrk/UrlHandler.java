/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.devfrk.httpserver.HttpExchange;
import io.github.kubesys.devfrk.mgr.ExceptionManager;
import io.github.kubesys.devfrk.mgr.ResponseBodyManager;
import io.github.kubesys.devfrk.mgr.UrlTypeManager;
import io.github.kubesys.devfrk.utils.JavaUtil;
import io.github.kubesys.devfrk.utils.JsonUtil;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since 2.0.2
 */
public class UrlHandler implements HttpHandler {

	/**
	 * m_logger
	 */
	public static Logger m_logger = Logger.getLogger(UrlHandler.class.getName());

	protected final Object obj;

	protected final Method m;

	public UrlHandler(Object obj, Method m) {
		super();
		this.obj = obj;
		this.m = m;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Object[] params = null;
		Object response = null;
		String type = exchange.getRequestMethod();
		String path = exchange.getHttpContext().getPath();
		try {
			if (type.equals("GET") && UrlTypeManager.getUrls.contains(path)) {
				String query = exchange.getRequestURI().getQuery();
				params = toGetParams(m, query);
			} else if (type.equals("POST") && UrlTypeManager.postUrls.contains(path)) {
				JsonNode json = new ObjectMapper().readTree(exchange.getRequestBody());
				params = toPostParams(m, json);
			} else {
				m_logger.severe("Only support GET and POST now.");
				return;
			}
			response = m.invoke(obj, params);
		} catch (Exception ex) {
			if (ex instanceof InvocationTargetException) {
				InvocationTargetException iex = (InvocationTargetException) ex;
				m_logger.severe(iex.getTargetException().getLocalizedMessage());
				throw new IOException(ExceptionManager.singleton()
						.getResult(exchange.getHttpContext(), iex.getTargetException()));
				
			}
		}
		exchange.sendResponseHeaders(200, 0);
		OutputStream os = exchange.getResponseBody();
		if (ResponseBodyManager.singleton().contain(
				obj.getClass().getName() + "." + m.getName())) {
			os.write(JsonUtil.getResult(true, response)
					.toPrettyString().getBytes());
		} else {
			os.write(response.toString().getBytes());
		}
		os.close();
	}

	Object[] toGetParams(Method m, String rawData) {
		try {
			Map<String, String> mapper = new HashMap<>();
			if (rawData != null) {
				for (String kv : rawData.split("&")) {
					String[] splits = kv.split("=");
					mapper.put(splits[0], splits[1]);
				}
			}
			List<Object> params = new ArrayList<>();
			for (Parameter p : m.getParameters()) {
				String typeName = p.getType().getName();
				if (JavaUtil.isString(typeName)) {
					params.add(mapper.get(p.getName()));
				} else if (JavaUtil.isInteger(typeName)) {
					params.add(Integer.parseInt(mapper.get(p.getName())));
				} else if (JavaUtil.isBoolean(typeName)) {
					params.add(Boolean.parseBoolean(mapper.get(p.getName())));
				} else if (!JavaUtil.isPrimitive(typeName)) {
					params.add(mapper.get(p.getName()));
				} else {
					m_logger.severe("unsupport " + typeName);
				}
			}
			return params.size() == 0 ? null : params.toArray(new Object[] {});
		} catch (Exception ex) {
			m_logger.severe(ex.getLocalizedMessage());
			return null;
		}
	}

	Object[] toPostParams(Method m, JsonNode jsonData) {
		if (jsonData == null || jsonData.size() == 0) {
			return null;
		}
		try {
			List<Object> params = new ArrayList<>();
			for (Parameter p : m.getParameters()) {
				String typeName = p.getType().getName();
				if (JavaUtil.isString(typeName)) {
					params.add(jsonData.get(p.getName()).asText());
				} else if (JavaUtil.isInteger(typeName)) {
					params.add(jsonData.get(p.getName()).asInt());
				} else if (JavaUtil.isBoolean(typeName)) {
					params.add(jsonData.get(p.getName()).asBoolean());
				} else if (!JavaUtil.isPrimitive(typeName)) {
					params.add(new ObjectMapper().readValue(jsonData.get(p.getName()).toPrettyString(), p.getType()));
				} else {
					m_logger.severe("unsupport " + typeName);
				}
			}
			return params.size() == 0 ? null : params.toArray(new Object[] {});
		} catch (Exception ex) {
			m_logger.severe(ex.getLocalizedMessage());
			return null;
		}
	}
}
