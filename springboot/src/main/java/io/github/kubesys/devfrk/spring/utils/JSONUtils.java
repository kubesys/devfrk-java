/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
		
		// 忽略取值为空的
		ObjectMapper objectMapper = JsonMapper.builder().
				serializationInclusion(JsonInclude.Include.NON_NULL)
				.build();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.valueToTree(
				objectMapper.writeValueAsString(obj));
	}
	
	public static JsonNode fromList(List<Object> objs) throws Exception {
		ArrayNode json = new ObjectMapper().createArrayNode();
		for (Object obj : objs) {
			json.add(objToJson(obj));
		}
		return json;
    }
	
	public static String toKubeFullKind(JsonNode json) throws Exception {
		String kind = json.has("kind") ? json.get("kind").asText() 
							: json.get("data").get("kind").asText();
		String apiVersion = json.has("apiVersion") ? json.get("apiVersion").asText()
							: json.get("data").get("apiVersion").asText();
		int idx = apiVersion.indexOf("/");
		return idx == - 1 ? kind : apiVersion.substring(0, idx) + "." + kind;
    }
	
	public static JsonNode objToJson(Object obj) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String replaceAll = objectMapper.writeValueAsString(obj);
		return objectMapper.readTree(replaceAll);
    }

}
