/**
 * Copyrigt (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.assists;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.2.3
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
	public final static int HTTP_RESPONSE_STATUS_OK     = 20000;

	public final static int HTTP_RESPONSE_STATUS_FAILED = 50000;
	
	/*****************************************************************
	 * 
	 * Postfix
	 * 
	 *****************************************************************/
	
	public final static String POSTFIX_SERVICE = "Service";
	
	/******************************************************************
	 * 
	 * Exceptions
	 * 
	 ******************************************************************/
	
	public final static String EXCEPTION_INVALID_REQUEST_URL = "Invalid servlet path, or invalid parameters was requested";
	
	public final static String EXCEPTION_INVALID_SERVICE_ANOTATION = "Invalid ServiceDefinition was requested";
	
	public final static String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_FROM_INNER_CLASS = "Unable to resgiter service from an inner class ";
	
	public final static String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_WRONG_NAME = "Unable to register service from a class does not endwith 'Service' ";
	
	public final static String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_POLYMORPHISM = "Unable to register the service with polymorphism ";
	
	public final static String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_UNKNOWN_REASON = "Unable to register the service with unknown reason, please try again ";
	
	public final static String EXCEPTION_HTTPCONTROLLER_UNSUPPORT = "Unable to register the service becasue HttpController does not support it, please modify RequestMapping to support ";
}
