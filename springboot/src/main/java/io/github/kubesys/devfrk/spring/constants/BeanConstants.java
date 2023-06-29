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
public class BeanConstants {

	private BeanConstants() {
		super();
	}

	@Document("用户可自定义io.github.kubesys.devfrk.spring.HttpResponse实现，并通过以下示例模式生效：\r\n"
			+ " @Bean(name = BeanConstants.RESPONSE)\r\n"
			+ "	public HttpResponse getResponse() {\r\n"
			+ "		return new DefaultHttpResponse();\r\n"
			+ "	}")
	public static final String RESPONSE     = "response";

}
