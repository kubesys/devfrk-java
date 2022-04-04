/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Logger;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.1
 */
public class ClassUtil {

	/**
	 * m_logger
	 */
	public static Logger m_logger = Logger.getLogger(ClassUtil.class.getName());

	/**
	 * basePackages
	 */
	private final String[] basePackages;

	/**
	 * anotations
	 */
	private final Class<? extends Annotation>[] annotations;

	/**
	 * 'class-annotation' mapping
	 */
	private final AnnotationClassQuerier classes = new AnnotationClassQuerier();

	public ClassUtil(String[] basePackages, Class<? extends Annotation>[] annotations) {
		this.basePackages = basePackages;
		this.annotations = annotations;
	}

	/**
	 * @param  classloader            classloader
	 * @return the 'class-annotation' mapping
	 */
	public AnnotationClassQuerier scan(ClassLoader classloader) {

		for (String basePackage : basePackages) {

			String path = UrlUtil.getClassPath(classloader, basePackage);

			m_logger.info("classpath is " + path);
			if (isJarFile(path)) {//
				readFromJarFile(path, basePackage);
			} else {
				readFromDirectory(basePackage);
			}
		}

		return classes;
	}

	private void readFromJarFile(String jarPath, String basePackage) {
		try {
			m_logger.info("from Jar: " + jarPath);
			String relativePath = UrlUtil.toRelativePath(basePackage);
			JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
			JarEntry entry = jarIn.getNextJarEntry();
			while (null != entry) {
				String name = entry.getName();
				if (name.startsWith(relativePath) && isClassFile(name)) {
					int idx = name.lastIndexOf("/");
					classes.addClassMapping(annotations, toFullyQualifiedName(basePackage, name.substring(idx + 1)));
				}

				entry = jarIn.getNextJarEntry();
			}
			jarIn.close();
		} catch (Exception ex) {

		}
	}

	private void readFromDirectory(String basePackage) {
		String relativePath = UrlUtil.toRelativePath(basePackage);
		URL file = ClassUtil.class.getClassLoader().getResource(relativePath);
		try {
			for (File subFile : new File(file.toURI()).listFiles()) {
				if (subFile.isDirectory()) {
					readFromDirectory(basePackage + "." + subFile.getName());
				} else {
					String name = subFile.getName();
					if (isClassFile(name)) {
						try {
							classes.addClassMapping(annotations, toFullyQualifiedName(
									basePackage, name.substring(0, name.length() - 6)));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		} catch (URISyntaxException e) {
			m_logger.warning("ignore " + file.getFile() + ": " + e);
		}
	}

	private boolean isClassFile(String name) {
		return name.endsWith(".class");
	}

	private boolean isJarFile(String name) {
		return name.endsWith(".jar");
	}
	
	private Class<?> toFullyQualifiedName(String basePackage, String shortName) throws Exception {
		return Class.forName(basePackage + "." + shortName);
	}

	/**
	 * @author wuheng@iscas.ac.cn
	 * @since  2.0.1
	 *
	 */
	public static class AnnotationClassQuerier {

		/**
		 * mapper
		 */
		protected Map<String, Set<Class<?>>> mapper = new HashMap<>();

		/**
		 * create the 'class-annotation' mapping
		 * 
		 * @param annotations  not null annotation
		 * @param scannedClass not null class
		 */
		public void addClassMapping(Class<? extends Annotation>[] annotations, Class<?> scannedClass) {

			if (scannedClass == null) {
				m_logger.warning("ignore the null class.");
				return;
			}

			if (annotations == null || annotations.length == 0) {
				m_logger.warning("ignore the class " + scannedClass + " without any annotations.");
				return;
			}

			for (Class<? extends Annotation> annotation : annotations) {
				addClassMapping(annotation, scannedClass);
			}
		}

		/**
		 * create the 'class-annotation' mapping
		 * 
		 * @param annotation   not null annotation
		 * @param scannedClass not null class
		 */
		private void addClassMapping(Class<? extends Annotation> annotation, Class<?> scannedClass) {

			Set<Class<?>> classes = mapper.get(annotation.getName());

			Annotation declaredAnnotation = scannedClass.getDeclaredAnnotation(annotation);
			
			if (declaredAnnotation == null) {
				return;
			}

			if (classes == null) {
				classes = new HashSet<>();
			}
			
			classes.add(scannedClass);

			mapper.put(annotation.getName(), classes);
		}

		/**
		 * get classes with a specified annotation
		 * 
		 * @param annotation not null annotation
		 * @return classes
		 */
		public Set<Class<?>> search(Class<?> annotation) {

			if (annotation == null) {
				m_logger.warning("skip the nu annotation.");
				return new HashSet<>();
			}

			Set<Class<?>> results = mapper.get(annotation.getName());

			return results == null ? new HashSet<>() : results;
		}

	}

}
