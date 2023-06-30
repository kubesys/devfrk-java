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
@Description(desc = "缺少配置文件", id = 502, group= "配置错误", see = ExceptionConstants.MISSING_CONFIG_FILE)
public class MissingConfigFileException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;

	
	public MissingConfigFileException() {
		super();
	}



	public MissingConfigFileException(String message) {
		super(message);
	}
	
}
