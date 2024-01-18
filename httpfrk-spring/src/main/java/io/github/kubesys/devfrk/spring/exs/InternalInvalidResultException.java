/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.exs;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 */
public class InternalInvalidResultException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1057346529882181459L;
	
	public InternalInvalidResultException() {
		super();
	}

	public InternalInvalidResultException(String message) {
		super(message);
	}

}
