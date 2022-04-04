/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.mgr;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.2
 */
public class UrlTypeManager {

	/**
	 * m_logger
	 */
	public static Logger m_logger = Logger.getLogger(UrlTypeManager.class.getName());
	
	/**
	 * get 
	 */
	public static Set<String> getUrls = new HashSet<>();
	
	/**
	 * post
	 */
	public static Set<String> postUrls = new HashSet<>();

}
