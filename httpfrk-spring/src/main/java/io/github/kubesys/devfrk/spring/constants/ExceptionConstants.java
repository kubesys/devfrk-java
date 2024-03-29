/**
 * Copyrigt (2019, ) Institute of Software, Chinese Academy of Sciences
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kubesys.devfrk.spring.constants;

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

	public static final String INVALID_REQUEST_URL                                       = "invalid url, see [IP:port/context-path](resources/application.yml)/spec";
	
	public static final String INVALID_REQUEST_PARAMS                                    = "invalid parameters.";
	
	public static final String INVALID_REQUEST_RESULT                                    = "invalid result.";
	
	public static final String INVALID_REQUEST_TOKEN                                      = "invalid token.";
	
	public static final String DB_WRONG_USERNAME_OR_PASSWORD                             = "wrong username or password.";
	
	public static final String MISSING_CONFIG_FILE                                       = "missing config file, ";
	
	public static final String MISSING_CONFIG_ITEM                                       = "missing config item, ";
	
	public static final String MISSING_CONFIG_FORMAT                                     = "missing config format, ";
	
	
	
	public static final String EXCEPTION_INVALID_SERVICE_ANOTATION                       = "Invalid ServiceDefinition was requested";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_FROM_INNER_CLASS     = "Unable to resgiter service from an inner class ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_WRONG_NAME      = "Unable to register service from a class does not endwith 'Service' ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_POLYMORPHISM    = "Unable to register the service with duplicated names ";
	
	public static final String EXCEPTION_UNABLE_TO_REGISTER_SERVICE_WITH_UNKNOWN_REASON  = "Unable to register the service with unknown reason, please try again ";
	
	public static final String EXCEPTION_HTTPCONTROLLER_UNSUPPORT                        = "Unable to register the service becasue HttpController does not support it, please modify RequestMapping to support ";

}
