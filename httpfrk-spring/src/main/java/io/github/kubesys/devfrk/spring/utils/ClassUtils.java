/**
 * Copyright (2018, ) Institute of Software, Chinese Academy of Sciences
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kubesys.devfrk.spring.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.SystemPropertyUtils;

/**
 * this class is used for finding all filtered classes with specified annotations.
 * 
 * @author wuheng@iscas.ac.cn
 * @since 1.1.0
 */
public class ClassUtils implements ResourceLoaderAware {

	/**
	 * logger
	 */
	private static Logger m_logger = Logger.getLogger(ClassUtils.class.getName());

	/**
	 * the filtered classes with specified annotations
	 */
	private static Set<Class<?>> m_filteredClasses = new HashSet<Class<?>>();

	/**
	 * specified annotations
	 */
	private final static List<TypeFilter> m_filters = new LinkedList<TypeFilter>();

	/**
	 * it is used for finding all classes with specified annotations
	 */
	private static ResourcePatternResolver m_classResolver = new PathMatchingResourcePatternResolver();

	/**
	 * it is used for recording all classes
	 */
	private static MetadataReaderFactory m_classFactory = new CachingMetadataReaderFactory(m_classResolver);

	/**
	 * @param basePackages if basePackages are null, return null
	 * @return filtered classes with specified annotations
	 */
	@SuppressWarnings("unchecked")
	public static Set<Class<?>> scan(String[] basePackages) {
		return ClassUtils.scan(basePackages, null);
	}
	
	/**
	 * @param basePackages if basePackages are null, return null
	 * @param annotations  if annotations are null, return all classes under the specified 'basePackages'
	 * @return filtered classes with specified annotations
	 */
	@SuppressWarnings("unchecked")
	public static Set<Class<?>> scan(String[] basePackages, Class<? extends Annotation>... annotations) {
		setFilters(annotations == null ? new Class[] {} : annotations);
		return filteredClasses(basePackages == null ? new String[] {} : basePackages);
	}

	@SuppressWarnings("unchecked")
	private static void setFilters(Class<? extends Annotation>... annotations) {
		for (Class<? extends Annotation> anno : annotations) {
			m_filters.add(new AnnotationTypeFilter(anno));
		}
	}

	private static Set<Class<?>> filteredClasses(String[] basePackages) {
		for (String pkg : basePackages) {
			m_filteredClasses.addAll(doScan(pkg));
		}
		return m_filteredClasses;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		m_classResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
		m_classFactory = new CachingMetadataReaderFactory(resourceLoader);
	}

	private static Set<Class<?>> doScan(String basePackage) {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (Resource resource : findClasses(getClassPattern(basePackage))) {
			try {
				MetadataReader reader = m_classFactory.getMetadataReader(resource);
				// return this class since we have no filters
				if (m_filters.size() == 0 
						// filtered this class if this class has specified annoations
						|| matches(reader)) {
					classes.add(Class.forName(reader.getClassMetadata().getClassName()));
				}
			} catch (Exception e) {
				m_logger.warning("cannot load this class, " + e);
			}
		}
		return classes;
	}

	private static Resource[] findClasses(String classPattern) {
		try {
			return m_classResolver.getResources(classPattern);
		} catch (IOException ex) {
			m_logger.warning("I/O failure during classpath scanning, " + ex);
		}
		return new Resource[] {};
	}

	private static String getClassPattern(String basePackage) {
		return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + org.springframework.util.ClassUtils
				.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage)) + "/**/*.class";
	}

	private static boolean matches(MetadataReader metadataReader) throws IOException {
		for (TypeFilter tf : m_filters) {
			if (tf.match(metadataReader, m_classFactory)) {
				return true;
			}
		}
		return false;
	}

}
