/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.exs;

import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.tools.annotations.ExceptionDesc;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 */
@ExceptionDesc(desc = "请求中缺少Token，或者Token被篡改，或者Token已经失效", id = "304", group= "内部错误" , see = ExceptionConstants.INVALID_REQUEST_TOKEN)
public class InternalInvalidTokenException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;
	
	public InternalInvalidTokenException() {
		super();
	}

	public InternalInvalidTokenException(String message) {
		super(message);
	}

}
