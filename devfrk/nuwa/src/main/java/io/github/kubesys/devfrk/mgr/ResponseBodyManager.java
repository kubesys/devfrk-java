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
 * @since  2.0.4
 */
public class ResponseBodyManager {

	/**
	 * m_logger
	 */
	public static Logger m_logger = Logger.getLogger(ResponseBodyManager.class.getName());
	
	/**
	 * m_mgr
	 */
	protected static ResponseBodyManager m_mgr = new ResponseBodyManager();
	
	/**
	 * set
	 */
	protected Set<String> set = new HashSet<>();
	

	/**
	 * 
	 */
	private ResponseBodyManager() {
		super();
	}
	
	/**
	 * @param name         classname + "." + methodname
	 */
	public void add(String name) {
		if (!set.contains(name)) {
			this.set.add(name);
		} else {
			m_logger.warning("find mutiple ResponseBody, but only one works");
		}
	}
	
	/**
	 * @param name         classname + "." + methodname 
	 * @return             true if has
	 */
	public boolean contain(String name) {
		return this.set.contains(name);
	}
	
	/**
	 * @return          singleton
	 */
	public static ResponseBodyManager singleton() {
		return m_mgr;
	}
}