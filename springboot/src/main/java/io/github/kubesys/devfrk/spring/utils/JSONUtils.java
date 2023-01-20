/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
		ObjectNode json = new ObjectMapper().createObjectNode();
		for (String key : map != null ? map.keySet() : new HashMap<String, String>().keySet()) {
			json.put(key, map.get(key));
		}
		return json;
	}
	
}
