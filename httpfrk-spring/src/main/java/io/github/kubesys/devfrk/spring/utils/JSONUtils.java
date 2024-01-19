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
package io.github.kubesys.devfrk.spring.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
		try {
			String kind = json.has("kind") ? json.get("kind").asText() 
								: json.get("data").get("kind").asText();
			String apiVersion = json.has("apiVersion") ? json.get("apiVersion").asText()
								: json.get("data").get("apiVersion").asText();
			int idx = apiVersion.indexOf("/");
		return idx == - 1 ? kind : apiVersion.substring(0, idx) + "." + kind;
		} catch (Exception ex) {
			return null;
		}
    }
	
	public static JsonNode objToJson(Object obj) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String replaceAll = objectMapper.writeValueAsString(obj);
		return objectMapper.readTree(replaceAll);
    }
	
	public static JsonNode fillObject(Type type) throws Exception {
		ObjectNode json = new ObjectMapper().createObjectNode();
		Class<?> clz = Class.forName(type.getTypeName());
		for (Field f : clz.getDeclaredFields()) {
			if (Modifier.isStatic(f.getModifiers())) {
				continue;
			}

			Type paramType = f.getGenericType();
			
			if (JavaUtils.isBool(paramType)) {
				json.put(f.getName(), true);
			} else if (JavaUtils.isChar(paramType) 
					|| JavaUtils.isString(paramType)) {
				json.put(f.getName(), "string");
			} else if (JavaUtils.isDouble(paramType)
					|| JavaUtils.isFloat(paramType)
					|| JavaUtils.isInt(paramType)
					|| JavaUtils.isShort(paramType)) {
				json.put(f.getName(), 0);
			} else if (JavaUtils.isStringList(paramType)
					|| JavaUtils.isStringSet(paramType)) {
				List<String> list = new ArrayList<>();
				list.add("string");
				list.add("string");
				json.set(f.getName(), JSONUtils.from(list));
			} else if (JavaUtils.isStringStringMap(paramType)) {
				Map<String, String> map = new HashMap<>();
				map.put("string", "string");
				json.set(f.getName(), JSONUtils.from(map));
			} else if (JavaUtils.isStringObjectMap(paramType)) {
				List<Object> list = new ArrayList<>();
				list.add(JSONUtils.fillObject(paramType));
				list.add(JSONUtils.fillObject(paramType));
				json.set(f.getName(), JSONUtils.from(list));
			} else if (JavaUtils.isObjectList(paramType) 
					|| JavaUtils.isObjectSet(paramType)) {
				Map<String, Object> map = new HashMap<>();
				map.put("string", JSONUtils.fillObject(paramType));
				json.set(f.getName(), JSONUtils.from(map));
			} else {
				json.set(f.getName(), JSONUtils.fillObject(paramType));
			}
		}
		return json;
	}

}
