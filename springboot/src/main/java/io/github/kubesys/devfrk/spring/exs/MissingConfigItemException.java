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
@Description(desc = "缺少配置项", id = 501, see = ExceptionConstants.MISSING_CONFIG_ITEM)
public class MissingConfigItemException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;

	
	public MissingConfigItemException() {
		super();
	}



	public MissingConfigItemException(String message) {
		super(message);
	}
	
}
