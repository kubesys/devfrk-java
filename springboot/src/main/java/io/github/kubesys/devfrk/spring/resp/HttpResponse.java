/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.resp;

/**
 * @author  wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since   2023/06/28
 * 
 * <p>
 * The {@code HttpResponse} class represents the return
 * value should be bound to the web response body.
 */
public interface HttpResponse {

	/**
	 * @param obj 具体数据
	 * @return    正确的Http响应
	 */
	public String success(Object obj);
	
	/**
	 * @param ex 具体异常
	 * @return   错误的Http响应
	 */
	public String fail(Exception ex);
}
