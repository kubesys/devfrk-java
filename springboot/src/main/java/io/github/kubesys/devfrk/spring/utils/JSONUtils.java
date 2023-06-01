/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author  wuheng@iscas.ac.cn
 * @since   1.1.0
 */
public class JSONUtils {

	/**
	 * @param map   map
	 * @return json
	 */
	public static JsonNode toJsonNode(Map<String, String> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		ObjectNode json = objectMapper.createObjectNode();
		for (String key : map != null ? map.keySet() 
				: new HashMap<String, String>().keySet()) {
			json.put(key, map.get(key));
		}
		return json;
	}
	
}
