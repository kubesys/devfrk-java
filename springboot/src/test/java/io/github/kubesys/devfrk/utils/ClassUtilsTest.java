/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;

import io.github.kubesys.devfrk.spring.utils.ClassUtils;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
class ClassUtilsTest {

	@Test
	void testWithNullPackage() {
		assertEquals(1, ClassUtils.scan(null).size());
	}
	
	@Test
	void testWithNullAnnotation() {
		assertEquals(1, ClassUtils.scan(new String[] {"io.github.webfrk.plus"}).size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testWithAnnotation() {
		assertEquals(1, ClassUtils.scan(new String[] {"io.github.webfrk.plus"}, ComponentScan.class).size());
	}
}
