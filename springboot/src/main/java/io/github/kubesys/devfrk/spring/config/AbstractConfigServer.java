/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.config;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author   wuheng@iscas.ac.cn
 * @version  2.3.0
 * @since    2023/06/29
 * 
 * 目前只支持String, int, long, boolean和Json，其中JSONNode
 * 交给开发者自己解决
 */
public interface AbstractConfigServer {

	/**
	 * @param kind      类型，本质就是类名
	 * @param key       规则是以.分隔，比如查询JSON的metadata.name
	 * @return    返回String
	 */
	public abstract String getString(String kind, String key);
	
	/**
	 * @param kind      类型，本质就是类名
	 * @param key       规则是以.分隔，比如查询JSON的metadata.name
	 * @return    返回int
	 */
	public abstract int getInt(String kind, String key);
	
	/**
	 * @param kind      类型，本质就是类名
	 * @param key       规则是以.分隔，比如查询JSON的metadata.name
	 * @return    返回long
	 */
	public abstract long getLong(String kind, String key);
	
	
	/**
	 * @param kind      类型，本质就是类名
	 * @param key       规则是以.分隔，比如查询JSON的metadata.name
	 * @return    返回boolean
	 */
	public abstract boolean getBoolean(String kind, String key);
	
	/**
	 * @param kind      类型，本质就是类名
	 * @param key       规则是以.分隔，比如查询JSON的metadata.name
	 * @return    返回json
	 */
	public abstract JsonNode getJSON(String kind, String key);

}
