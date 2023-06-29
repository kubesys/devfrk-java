/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.spring.exs.InternalInvalidResultException;

/**
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
public class JSONUtils {

	private JSONUtils() {
		super();
	}

	/**
	 * @param map map
	 * @return json
	 */
	public static JsonNode from(Map<String, String> map) {
		// 确保map不为空
		map = (map != null) ? map : new HashMap<>();
		
		// 兼容LocalTime
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		ObjectNode json = objectMapper.createObjectNode();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			json.put(entry.getKey(), entry.getValue());
		}

		return json;
	}

	/**
	 * @param obj 对象
	 * @return JSON格式
	 * @throws JsonProcessingException
	 */
	public static JsonNode from(Object obj) throws JsonProcessingException {
		
		//确保不为空
		if (obj == null || JavaUtils
				.isPrimitive(obj.getClass())) {
			throw new InternalInvalidResultException(
					ExceptionConstants.INVALID_REQUEST_RESULT + ":" + obj);
		}
		
		// 本身就是JSON
		if (obj instanceof JsonNode json) {
			return json;
		}
		
		//
		return new ObjectMapper().readTree(
				new ObjectMapper().writeValueAsString(obj));
	}

}
