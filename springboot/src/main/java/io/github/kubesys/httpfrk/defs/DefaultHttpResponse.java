/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.httpfrk.defs;

import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;

import io.github.kubesys.httpfrk.cores.HttpContext;
import io.github.kubesys.httpfrk.cores.HttpResponse;
import io.github.kubesys.httpfrk.utils.JSONUtil;

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
	
	@Autowired
	protected HttpContext context;
	
	@Override
	public String success(Object obj) throws Exception {
		return ((DefaultHttpResponse) context.getBean("resp")).unwrap("success", obj);
	}
	
	@Override
	public String fail(Exception ex) throws Exception {
		m_logger.warning("cannot handle request: " + ex);
		return ((DefaultHttpResponse) context.getBean("resp"))
				.unwrap("fail", String.valueOf(ex.getMessage()));
	}
	
	public String unwrap(String status, Object value) throws Exception {
		HttpResponseData response = "fail".equals(status) ?
				new HttpResponseData(50000, value.toString().indexOf(":") == -1 ? value.toString() 
								: value.toString().substring(value.toString().indexOf(":"))) 
				: new HttpResponseData(20000, value);
		return JSONUtil.toJSONString(response);
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
		 * if it is not an exception, the response
		 * is the object.
		 */
		protected Object data;
		
		public HttpResponseData(int code, String message) {
			this.code = code;
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
	}

}
