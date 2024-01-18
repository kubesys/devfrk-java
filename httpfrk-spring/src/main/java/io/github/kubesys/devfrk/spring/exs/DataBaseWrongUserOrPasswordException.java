/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.exs;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 */
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
