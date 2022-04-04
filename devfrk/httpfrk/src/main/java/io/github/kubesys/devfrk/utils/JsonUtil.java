/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.4
 */
public class JsonUtil {
	
	private JsonUtil() {

	}

	public static JsonNode getResult(boolean status, Object obj) {
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("sucess", status);
		if (obj == null) {
			node.set("data", new ObjectMapper().createObjectNode());
		} else if (JavaUtil.isString(obj.getClass().getName())) {
			node.put("data", (String) obj);
		} else if (JavaUtil.isInteger(obj.getClass().getName())) {
			node.put("data", (Integer) obj);
		} else if (JavaUtil.isBoolean(obj.getClass().getName())) {
			node.put("data", (Boolean) obj);
		} else {
			try {
				node.set("data", new ObjectMapper().readTree(
						new ObjectMapper().writeValueAsString(obj)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return node;
	}
}
