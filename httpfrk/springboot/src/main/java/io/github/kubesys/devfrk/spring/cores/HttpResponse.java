/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

/**
 * @author  wuheng@iscas.ac.cn
 * @since   2.2.3
 * 
 * <p>
 * The {@code HttpResponse} class represents the return
 * value should be bound to the web response body.
 */
public interface HttpResponse {

	public String success(Object obj) throws Exception;
	
	public String fail(Exception ex) throws Exception;
	
}
