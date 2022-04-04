/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.mgr;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.4
 */
public class BeanManager {

	/**
	 * m_logger
	 */
	public static Logger m_logger = Logger.getLogger(BeanManager.class.getName());
	
	/**
	 * m_mgr
	 */
	protected static BeanManager m_mgr = new BeanManager();
	
	
	/**
	 * mapper
	 */
	protected Map<String, Object> mapper = new HashMap<>();
	
	/**
	 * 
	 */
	private BeanManager() {
		super();
	}
	
	/**
	 * @param clz           clz
	 */
	public void add(Class<?> clz) {
		if (!mapper.containsKey(clz.getName())) {
			try {
				this.mapper.put(clz.getName(), clz.newInstance());
			} catch (Exception e) {
				m_logger.warning("unable to create object: " + e);
			}
		} else {
			m_logger.warning("find mutiple Autowired, but only one works");
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
	public Object getObject(String name) {
		return mapper.get(name);
	}
	
	
	/**
	 * @return          singleton
	 */
	public static BeanManager singleton() {
		return m_mgr;
	}
}