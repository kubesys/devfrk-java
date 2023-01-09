/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.github.kubesys.devfrk.spring.utils.JSONUtils;
import junit.framework.Assert;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
@SuppressWarnings("deprecation")
public class JSONUtilsTest {

	@Test
	public void testNull() {
		Assert.assertEquals(0, JSONUtils.toJsonNode(null).size());
	}
	
	@Test
	public void testMapToJSON() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		Assert.assertEquals(2, JSONUtils.toJsonNode(map).size());
		Assert.assertEquals("{\r\n"
				+ "  \"a\" : \"a\",\r\n"
				+ "  \"b\" : \"b\"\r\n"
				+ "}", JSONUtils.toJsonNode(map).toPrettyString());
	}
}
