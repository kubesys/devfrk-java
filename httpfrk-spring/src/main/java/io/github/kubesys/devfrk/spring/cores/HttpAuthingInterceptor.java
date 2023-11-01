/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import io.github.kubesys.devfrk.spring.auth.AuthingModel;

/**
 * @author  wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since   2023/06/28
 * 
 * <p>
 * The {@code HttpResponse} class represents the return
 * value should be bound to the web response body.
 */
public interface HttpAuthingInterceptor {

	/**
	 * @param auth 具体auth
	 * @param kind，GET/POST/DELET/PUT
	 * @target  url
	 * @return   有权限或者无权限
	 */
	public boolean check(AuthingModel auth, String type, String kind);
	
}
