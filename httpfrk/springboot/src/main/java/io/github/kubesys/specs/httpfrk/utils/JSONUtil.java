/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.specs.httpfrk.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author  wuheng@iscas.ac.cn
 * @since   1.1.0
 */
public class JSONUtil {

	/**********************************
	 *  String utils
	 **********************************/
	
	/**
	 * convert Java object to JSON String
	 * 
	 * @param obj   obejct
	 * @return      return JSON String   
	 * @throws Exception exception
	 */
	public static String toJSONString(Object obj) throws Exception {
		return new ObjectMapper().writeValueAsString(obj);
	}

	/**
	 * check whether the object is empty or not
	 * 
	 * @param obj  object
	 * @return     return true if 'obj' is not null, otherwise return false
	 */
	public static boolean isNull (Object obj) {
		return (obj == null) ? true : false;
	}
	
	
	public static JsonNode toJsonNode(Map<String, String> map) {
		ObjectNode on = new ObjectMapper().createObjectNode();
		for (String key : map.keySet()) {
			on.put(key, map.get(key));
		}
		return on;
	}
	
}
