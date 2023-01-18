/**
 * Copyrigt (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.assists;

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
	public static final int HTTP_RESPONSE_STATUS_OK     = 20000;

	public static final int HTTP_RESPONSE_STATUS_FAILED = 50000;
	
	/*****************************************************************
	 * 
	 * Postfix
	 * 
	 *****************************************************************/
	
	public static final String SERVICE_CLASS_POSTFIX = "Service";
	
	public static final String URL_PATH_SEPARATOR    = "/";
	
	/******************************************************************
	 * 
	 * Sucessful
	 * 
	 ******************************************************************/
	
	public static final String SUCESSFUL_REGISTER_HTTPHANDLER                             = "Register http handler for path: ";
	
	/******************************************************************
	 * 
	 * Exceptions
	 * 
	 ******************************************************************/
	
	public static final String EXCEPTION_INVALID_REQUEST_URL                             = "Invalid servlet path, or invalid parameters was requested";
	
	public static final String EXCEPTION_INVALID_SERVICE_ANOTATION                       = "Invalid ServiceDefinition was requested";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_FROM_INNER_CLASS     = "Unable to resgiter service from an inner class ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_WRONG_NAME      = "Unable to register service from a class does not endwith 'Service' ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_POLYMORPHISM    = "Unable to register the service with polymorphism ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_UNKNOWN_REASON  = "Unable to register the service with unknown reason, please try again ";
	
	public static final String EXCEPTION_HTTPCONTROLLER_UNSUPPORT                        = "Unable to register the service becasue HttpController does not support it, please modify RequestMapping to support ";
}
