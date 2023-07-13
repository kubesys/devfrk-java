/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.auth;

/**
 * @author wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since 2023/07/07
 * 
 *        目前只支持String, int, long, boolean和Json，其中JSONNode 交给开发者自己解决
 */
public class AuthingModel {

	protected String type;
	
	protected String token;
	
	protected String user;


	public AuthingModel(String type, String token, String user) {
		super();
		this.type = type;
		this.token = token;
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
