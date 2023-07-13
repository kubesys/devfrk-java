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
@ExceptionDesc(desc = "缺少配置格式", id = "503", group= "配置错误", see = ExceptionConstants.MISSING_CONFIG_FILE)
public class MissingConfigFormatException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;

	
	public MissingConfigFormatException() {
		super();
	}



	public MissingConfigFormatException(String message) {
		super(message);
	}
	
}
