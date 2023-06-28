/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.defs;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.devfrk.spring.HttpResponse;
import io.github.kubesys.devfrk.spring.constants.BeanConstants;
import io.github.kubesys.devfrk.spring.exs.HttpFramworkException;
import jakarta.annotation.Resource;

/**
 * @author  wuheng@iscas.ac.cn
 * @since   2.2.3
 * 
 * <p>
 * The {@code HttpResponse} class represents the return
 * value should be bound to the web response body.
 */
public class DefaultHttpResponse implements HttpResponse {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(DefaultHttpResponse.class.getName());
	
	@Resource
	protected ApplicationContext context;
	
	
	@Override
	public String success(Object obj) throws Exception {
		return ((DefaultHttpResponse) context.getBean(BeanConstants.RESPONSE)).unwrap("success", -1, obj);
	}
	
	@Override
	public String fail(Exception ex) throws Exception {
		m_logger.warning("cannot handle request: " + ex);
		if (ex instanceof HttpFramworkException) {
			HttpFramworkException hex = (HttpFramworkException) ex;
			return ((DefaultHttpResponse) context.getBean(BeanConstants.RESPONSE))
					.unwrap("fail", hex.getCode(), String.valueOf(hex));
		}
		return ((DefaultHttpResponse) context.getBean(BeanConstants.RESPONSE))
				.unwrap("fail", 400, String.valueOf(ex));
	}
	
	public String unwrap(String status, int id, Object value) throws Exception {
		HttpResponseData response = "fail".equals(status) ?
				new HttpResponseData(50000, id, value.toString().substring(value.toString().indexOf(":") + 1)) 
				: new HttpResponseData(20000, value);
		return new ObjectMapper().writeValueAsString(response);
	}
	

	public static class HttpResponseData {
		/**
		 * neither Success or Failure
		 */
		protected int code;
		
		/**
		 * it represents the exception information,
		 * otherwise it should be null
		 */
		protected String message;
		
		
		/**
		 * Exception Id
		 */
		protected int exId;
		
		/**
		 * if it is not an exception, the response
		 * is the object.
		 */
		protected Object data;
		
		public HttpResponseData(int code, int exId, String message) {
			this.code = code;
			this.exId = exId;
			this.message = message;
		}

		public HttpResponseData(int code, Object data) {
			this.code = code;
			this.data = data;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public int getExId() {
			return exId;
		}

		public void setExId(int exId) {
			this.exId = exId;
		}
		
	}

}
