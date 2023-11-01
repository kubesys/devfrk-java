/**
 * Copyrigt (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.constants;

import io.github.kubesys.devfrk.tools.annotations.Document;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 */
public class HttpConstants {

	private HttpConstants() {
		super();
	}

	/*****************************************************************
	 * 
	 * HTTP Response
	 * 
	 *****************************************************************/
	@Document("{\"code\": 20000}表示业务系统正常响应，注意：其生效仅针对系统未自定义Bean: " + BeanConstants.RESPONSE)
	public static final int HTTP_RESPONSE_STATUS_OK     = 20000;

	@Document("{\"code\": 50000}表示业务系统非正常响应，细分异常信息默认参见exId和message字段，注意：其生效仅针对系统未自定义Bean: " + BeanConstants.RESPONSE)
	public static final int HTTP_RESPONSE_STATUS_FAILED = 50000;
	
	@Document("用户自定义服务的类名必须是以Service结尾，参见[服务开发规范]{/docs/spec}")
	public static final String SERVICE_CLASS_POSTFIX = "Service";
	
}
