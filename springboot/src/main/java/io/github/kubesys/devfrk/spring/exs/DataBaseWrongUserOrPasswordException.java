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
@ExceptionDesc(desc = "错误的用户名密码", id = "401", group= "权限错误", see = ExceptionConstants.DB_WRONG_USERNAME_OR_PASSWORD)
public class DataBaseWrongUserOrPasswordException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;

	
	public DataBaseWrongUserOrPasswordException() {
		super();
	}



	public DataBaseWrongUserOrPasswordException(String message) {
		super(message);
	}
	
}
