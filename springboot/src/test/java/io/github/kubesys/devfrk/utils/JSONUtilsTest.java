/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.github.kubesys.devfrk.spring.utils.JSONUtils;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
class JSONUtilsTest {

	@Test
	void testNull() {
		assertEquals(0, JSONUtils.toJsonNode(null).size());
	}
	
	@Test
	void testMapToJSON() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		assertEquals(2, JSONUtils.toJsonNode(map).size());
		assertEquals("{\r\n"
				+ "  \"a\" : \"a\",\r\n"
				+ "  \"b\" : \"b\"\r\n"
				+ "}", JSONUtils.toJsonNode(map).toPrettyString());
	}
}
