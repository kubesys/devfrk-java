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
@Description(desc = "未注册的Url，请访问getHandlers查询所有支持的HttpHandlers、参数和返回值类型", id = 301, group= "内部错误" , see = ExceptionConstants.INVALID_REQUEST_URL)
public class InternalInvalidUrlException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;
	
	public InternalInvalidUrlException() {
		super();
	}

	public InternalInvalidUrlException(String message) {
		super(message);
	}

}
