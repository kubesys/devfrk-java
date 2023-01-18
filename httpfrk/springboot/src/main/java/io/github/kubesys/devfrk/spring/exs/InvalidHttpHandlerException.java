/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.exs;


/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 */
public class InvalidHttpHandlerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -572936047596644795L;

	public InvalidHttpHandlerException() {
		super();
	}

	public InvalidHttpHandlerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidHttpHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidHttpHandlerException(String message) {
		super(message);
	}

	public InvalidHttpHandlerException(Throwable cause) {
		super(cause);
	}

}
