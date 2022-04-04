/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.utils;

import java.io.File;
import java.net.URL;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.1
 */
public class UrlUtil {
	
	private UrlUtil() {

	}

	/**
	 * @param classloader   classloader
	 * @param basePackage   basePackage
	 * @return              classpath
	 */
	public static String getClassPath(ClassLoader classloader, String basePackage) {
		String splashPath = UrlUtil.toRelativePath(basePackage);
		// file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
		URL url = classloader.getResource(splashPath); 
		return UrlUtil.getRootPath(url);
	}
	
	/**
	 * "file:/home/whf/cn/fh" to "file:/home/whf/cn/fh"
	 * "jar:file:/home/whf/foo.jar!cn/fh" to "file:/home/whf/foo.jar"
	 * 
	 * @param url            url
	 * @return               path
	 */
	public static String getRootPath(URL url) {
		String fileUrl = url.getFile();
		
		int pos = fileUrl.indexOf('!');

		// "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
		if (-1 == pos) {
			return fileUrl;
		}

		// "jar:file:/home/whf/foo.jar!cn/fh" -> "file:/home/whf/foo.jar"
		return fileUrl.substring(5, pos);
	}

	/**
	 * "cn.fh.lightning" to "cn/fh/lightning"
	 * 
	 * @param name            name
	 * @return                directory
	 */
	public static String toRelativePath(String name) {
		return name.replaceAll("\\.", "/");
	}
	
	/**
	 * "cn/fh/lightning" to "cn.fh.lightning"
	 * 
	 * @param file            file
	 * @return                package
	 */
	public static String toPackageName(File file) {
		return file.getName().replaceAll("/", "\\.");
	}

	/**
	 * "Apple.class" to "Apple"
	 * 
	 * @param name             name
	 * @return                 classname  
	 */
	public static String trimExtension(String name) {
		int pos = name.indexOf('.');
		if (-1 != pos) {
			return name.substring(0, pos);
		}

		return name;
	}

}
