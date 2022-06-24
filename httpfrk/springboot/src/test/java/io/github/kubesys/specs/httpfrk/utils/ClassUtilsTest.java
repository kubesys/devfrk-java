/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.specs.httpfrk.utils;

import org.junit.Test;

import junit.framework.Assert;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
public class ClassUtilsTest {

	@Test
	public void testNullPackage() {
		Assert.assertEquals(0,ClassUtils.scan(null).size());
	}
	
	@Test
	public void testNullAnnotation() {
		Assert.assertEquals(3,ClassUtils.scan("io.github.webfrk.plus").size());
	}
}
