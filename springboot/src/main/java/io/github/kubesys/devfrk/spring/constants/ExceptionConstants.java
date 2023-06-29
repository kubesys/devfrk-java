/**
 * Copyrigt (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.constants;

import io.github.kubesys.devfrk.tools.annotations.Document;

/**
 * @author   wuheng@iscas.ac.cn
 * @version  2.3.0
 * @since    2023/06/28
 * 
 */
public class ExceptionConstants {

	private ExceptionConstants() {
		super();
	}

	@Document("未注册的Url，请访问getHandlers查询所有支持的HttpHandlers、参数和返回值类型")
	public static final String INVALID_REQUEST_URL                                       = "invalid url error.";
	
	@Document("错误的参数，请访问getHandlers查询所有支持的HttpHandlers、参数和返回值类型")
	public static final String INVALID_REQUEST_PARAMS                                    = "invalid parameters error.";
	
	@Document("异常的返回值，请访问getHandlers查询所有支持的HttpHandlers、参数和返回值类型")
	public static final String INVALID_REQUEST_RESULT                                    = "invalid result error.";
	
	@Document("错误的用户名活密码")
	public static final String INVALID_USERNAME_OR_PASSWORD                              = "wrong username or password.";
	
	public static final String EXCEPTION_INVALID_SERVICE_ANOTATION                       = "Invalid ServiceDefinition was requested";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_FROM_INNER_CLASS     = "Unable to resgiter service from an inner class ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_WRONG_NAME      = "Unable to register service from a class does not endwith 'Service' ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_POLYMORPHISM    = "Unable to register the service with duplicated names ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_UNKNOWN_REASON  = "Unable to register the service with unknown reason, please try again ";
	
	public static final String EXCEPTION_HTTPCONTROLLER_UNSUPPORT                        = "Unable to register the service becasue HttpController does not support it, please modify RequestMapping to support ";

}
