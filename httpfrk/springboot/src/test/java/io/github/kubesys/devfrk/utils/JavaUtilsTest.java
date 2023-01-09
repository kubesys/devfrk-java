/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import io.github.kubesys.devfrk.spring.utils.JavaUtils;
import junit.framework.Assert;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
@SuppressWarnings("deprecation")
public class JavaUtilsTest {

	
	@Test
	public void testPrimitiveString() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(String.class));
	}
	
	@Test
	public void testPrimitiveBool() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(Boolean.class) && JavaUtils.isPrimitive("boolean"));
	}
	
	@Test
	public void testPrimitiveChar() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(Character.class) && JavaUtils.isPrimitive("char"));
	}
	
	@Test
	public void testPrimitiveByte() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(Byte.class) && JavaUtils.isPrimitive("byte"));
	}
	
	@Test
	public void testPrimitiveShort() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(Short.class) && JavaUtils.isPrimitive("short"));
	}
	
	@Test
	public void testPrimitiveInt() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(Integer.class) && JavaUtils.isPrimitive("int"));
	}
	
	@Test
	public void testPrimitiveLong() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(Long.class) && JavaUtils.isPrimitive("long"));
	}
	
	@Test
	public void testPrimitiveFloat() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(Float.class) && JavaUtils.isPrimitive("float"));
	}
	
	@Test
	public void testPrimitiveDouble() {
		Assert.assertEquals(true, JavaUtils.isPrimitive(Double.class) && JavaUtils.isPrimitive("double"));
	}
	
	@Test
	public void testMath() {
		Assert.assertEquals(false, JavaUtils.isPrimitive(Math.class));
	}
	
	@Test
	public void testMap() {
		Assert.assertEquals(true, JavaUtils.isMap(Map.class));
	}
	
	@Test
	public void testHashMap() {
		Assert.assertEquals(true, JavaUtils.isMap(HashMap.class));
	}
	
	
	
	@Test
	public void testNoMap() {
		Assert.assertEquals(false, JavaUtils.isMap(Math.class));
	}

	public Type getType(String name) throws Exception {
		return JavaUtilsTest.class.getDeclaredField(name).getGenericType();
	}
	
	public static Map<String, String> strStrMap = null;
	
	@Test
	public void testStringStringMap() throws Exception {
		Assert.assertEquals(true, JavaUtils.isStringStringMap(getType("strStrMap")));
	}
	
	public static Map<String, Math> strObjMap = null;
	
	@Test
	public void testStringObjectMap() throws Exception {
		Assert.assertEquals(true, JavaUtils.isStringObjectMap(getType("strObjMap")));
	}
	
	
	@Test
	public void testList() {
		Assert.assertEquals(true, JavaUtils.isList(List.class));
	}
	
	@Test
	public void testArrayList() {
		Assert.assertEquals(true, JavaUtils.isList(ArrayList.class));
	}
	
	@Test
	public void testNoList() {
		Assert.assertEquals(false, JavaUtils.isList(Math.class));
	}
	
	public static List<String> strList = null;
	
	@Test
	public void testStringList() throws Exception {
		Assert.assertEquals(true, JavaUtils.isStringList(getType("strList")));
	}
	
	public static List<Math> objList = null;
	
	@Test
	public void testObjectList() throws Exception {
		Assert.assertEquals(true, JavaUtils.isObjectList(getType("objList")));
	}
	
	@Test
	public void testSet() {
		Assert.assertEquals(true, JavaUtils.isSet(Set.class));
	}
	
	@Test
	public void testHashSet() {
		Assert.assertEquals(true, JavaUtils.isSet(HashSet.class));
	}
	
	@Test
	public void testNoSet() {
		Assert.assertEquals(false, JavaUtils.isSet(Math.class));
	}
	
	public static Set<String> strSet = null;
	
	@Test
	public void testStrinSet() throws Exception {
		Assert.assertEquals(true, JavaUtils.isStringSet(getType("strSet")));
	}
	
	public static List<Math> objSet = null;
	
	@Test
	public void testObjectSet() throws Exception {
		Assert.assertEquals(true, JavaUtils.isObjectSet(getType("objSet")));
	}
	
	public void testValueInMap() throws Exception {
		Assert.assertEquals(Math.class.getName(), JavaUtils.getValueClassForGenericMap(getType("strObjMap")));
	}
	
	public void testObjectInList() throws Exception {
		Assert.assertEquals(Math.class.getName(), JavaUtils.getClassForGenericList(getType("objList")));
	}
	
	public void testObjectInSet() throws Exception {
		Assert.assertEquals(Math.class.getName(), JavaUtils.getClassForGenericSet(getType("objSet")));
	}
}
