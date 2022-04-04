/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk;

import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.2
 */
public class HttpServerConfigurator {

	/**
	 * m_logger
	 */
	public static Logger m_logger = Logger.getLogger(HttpServerConfigurator.class.getName());

	/**
	 * name
	 */
	public static String m_app_yml = "application.yml";
	
	/**
	 * json
	 */
	protected JsonNode json;
	
	/**
	 * 
	 */
	public HttpServerConfigurator() {
		try {
			this.json =new ObjectMapper(new YAMLFactory()).readTree(HttpServerConfigurator.class.
							getClassLoader().getResourceAsStream(m_app_yml));
		} catch (Exception ex) {
			m_logger.severe("Unable to read " + m_app_yml);
		}
	}
	
	/**
	 * @param key                  key
	 * @param clz                  clz
	 * @return                     object
	 */
	public Object getConfig(String key, Class<?> clz) {
		try {
			return new ObjectMapper().readValue(
					json.get(key).toPrettyString(), clz);
		} catch (Exception ex) {
			m_logger.warning(ex.getLocalizedMessage());
			return null;
		}
	}
	
}
