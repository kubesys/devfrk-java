/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.mgr;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import io.github.kubesys.devfrk.UrlHandler;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.4
 */
public class HandlerManager {

	/**
	 * m_logger
	 */
	public static Logger m_logger = Logger.getLogger(HandlerManager.class.getName());
	
	/**
	 * m_mgr
	 */
	protected static HandlerManager m_mgr = new HandlerManager();
	
	
	/**
	 * mapper
	 */
	protected Map<String, UrlHandler> mapper = new HashMap<>();
	
	/**
	 * 
	 */
	private HandlerManager() {
		super();
	}
	
	
	/**
	 * @return                 urls
	 */
	public Set<String> getUrls() {
		return mapper.keySet();
	}
	
	/**
	 * @param name               name
	 * @param handler            handler
	 */
	public void add(String name, UrlHandler handler) {
		if (!mapper.containsKey(name)) {
			this.mapper.put(name, handler);
		} else {
			m_logger.warning("find mutiple handler, but only one works");
		}
	}
	
	/**
	 * @param name     name
	 * @return         true if has exception handler
	 */
	public boolean hasObject(String name) {
		return mapper.containsKey(name);
	}
	
	/**
	 * @param name     name
	 * @return         object
	 */
	public UrlHandler getHandler(String name) {
		return mapper.get(name);
	}
	
	
	/**
	 * @return          singleton
	 */
	public static HandlerManager singleton() {
		return m_mgr;
	}
}