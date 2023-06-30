/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.exs;

import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.tools.annotations.Description;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 */
@Description(desc = "参数不正确，请访问getHandlers查询所有支持的HttpHandlers、参数和返回值类型", id = 302, see = ExceptionConstants.INVALID_REQUEST_PARAMS)
public class InternalInvalidParamsException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;

	
	public InternalInvalidParamsException() {
		super();
	}



	public InternalInvalidParamsException(String message) {
		super(message);
	}
	
}
