/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import io.github.kubesys.devfrk.spring.utils.JavaUtils;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
class JavaUtilsTest {

	
	@Test
	void testPrimitiveString() {
		assertEquals(true, JavaUtils.isPrimitive(String.class));
	}
	
	@Test
	void testPrimitiveBool() {
		assertEquals(true, JavaUtils.isPrimitive(Boolean.class) && JavaUtils.isPrimitive("boolean"));
	}
	
	@Test
	void testPrimitiveChar() {
		assertEquals(true, JavaUtils.isPrimitive(Character.class) && JavaUtils.isPrimitive("char"));
	}
	
	@Test
	void testPrimitiveByte() {
		assertEquals(true, JavaUtils.isPrimitive(Byte.class) && JavaUtils.isPrimitive("byte"));
	}
	
	@Test
	void testPrimitiveShort() {
		assertEquals(true, JavaUtils.isPrimitive(Short.class) && JavaUtils.isPrimitive("short"));
	}
	
	@Test
	void testPrimitiveInt() {
		assertEquals(true, JavaUtils.isPrimitive(Integer.class) && JavaUtils.isPrimitive("int"));
	}
	
	@Test
	void testPrimitiveLong() {
		assertEquals(true, JavaUtils.isPrimitive(Long.class) && JavaUtils.isPrimitive("long"));
	}
	
	@Test
	void testPrimitiveFloat() {
		assertEquals(true, JavaUtils.isPrimitive(Float.class) && JavaUtils.isPrimitive("float"));
	}
	
	@Test
	void testPrimitiveDouble() {
		assertEquals(true, JavaUtils.isPrimitive(Double.class) && JavaUtils.isPrimitive("double"));
	}
	
	@Test
	void testMath() {
		assertEquals(false, JavaUtils.isPrimitive(Math.class));
	}
	
	@Test
	void testMap() {
		assertEquals(true, JavaUtils.isMap(Map.class));
	}
	
	@Test
	void testHashMap() {
		assertEquals(true, JavaUtils.isMap(HashMap.class));
	}
	
	
	
	@Test
	void testNoMap() {
		assertEquals(false, JavaUtils.isMap(Math.class));
	}

	Type getType(String name) throws Exception {
		return JavaUtilsTest.class.getDeclaredField(name).getGenericType();
	}
	
	static Map<String, String> strStrMap = null;
	
	@Test
	void testStringStringMap() throws Exception {
		assertEquals(true, JavaUtils.isStringStringMap(getType("strStrMap")));
	}
	
	static Map<String, Math> strObjMap = null;
	
	@Test
	void testStringObjectMap() throws Exception {
		assertEquals(true, JavaUtils.isStringObjectMap(getType("strObjMap")));
	}
	
	
	@Test
	void testList() {
		assertEquals(true, JavaUtils.isList(List.class));
	}
	
	@Test
	void testArrayList() {
		assertEquals(true, JavaUtils.isList(ArrayList.class));
	}
	
	@Test
	void testNoList() {
		assertEquals(false, JavaUtils.isList(Math.class));
	}
	
	static List<String> strList = null;
	
	@Test
	void testStringList() throws Exception {
		assertEquals(true, JavaUtils.isStringList(getType("strList")));
	}
	
	static List<Math> objList = null;
	
	@Test
	void testObjectList() throws Exception {
		assertEquals(true, JavaUtils.isObjectList(getType("objList")));
	}
	
	@Test
	void testSet() {
		assertEquals(true, JavaUtils.isSet(Set.class));
	}
	
	@Test
	void testHashSet() {
		assertEquals(true, JavaUtils.isSet(HashSet.class));
	}
	
	@Test
	void testNoSet() {
		assertEquals(false, JavaUtils.isSet(Math.class));
	}
	
	static Set<String> strSet = null;
	
	@Test
	void testStrinSet() throws Exception {
		assertEquals(true, JavaUtils.isStringSet(getType("strSet")));
	}
	
	static List<Math> objSet = null;
	
	@Test
	void testObjectSet() throws Exception {
		assertEquals(true, JavaUtils.isObjectSet(getType("objSet")));
	}
	
	void testValueInMap() throws Exception {
		assertEquals(Math.class.getName(), JavaUtils.getValueClassForGenericMap(getType("strObjMap")));
	}
	
	void testObjectInList() throws Exception {
		assertEquals(Math.class.getName(), JavaUtils.getClassForGenericList(getType("objList")));
	}
	
	void testObjectInSet() throws Exception {
		assertEquals(Math.class.getName(), JavaUtils.getClassForGenericSet(getType("objSet")));
	}
}
