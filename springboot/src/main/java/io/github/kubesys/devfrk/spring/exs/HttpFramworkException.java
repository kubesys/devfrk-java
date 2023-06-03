/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.exs;


/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 */
public class HttpFramworkException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;
	
	protected final int code;

	public HttpFramworkException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
