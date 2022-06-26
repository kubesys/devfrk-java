/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.specs.httpfrk.utils;

import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;

import junit.framework.Assert;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
@SuppressWarnings("deprecation")
public class ClassUtilsTest {

	@Test
	public void testWithNullPackage() {
		Assert.assertEquals(0, ClassUtils.scan(null).size());
	}
	
	@Test
	public void testWithNullAnnotation() {
		Assert.assertEquals(4, ClassUtils.scan(new String[] {"io.github.webfrk.plus"}).size());
	}
	
	@Test
	public void testWithAnnotation() {
		Assert.assertEquals(2, ClassUtils.scan(new String[] {"io.github.webfrk.plus"}, ComponentScan.class).size());
	}
}
