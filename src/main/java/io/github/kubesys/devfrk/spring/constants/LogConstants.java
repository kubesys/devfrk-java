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
public class LogConstants {

	private LogConstants() {
		super();
	}

	@Document("成功注册Http处理器，用户可以通注册的path进行访问")
	public static final String SUCESSFUL_REGISTER_HTTPHANDLER       = "register http handler: ";

}
