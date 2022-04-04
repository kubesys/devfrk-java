/**
 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.configs;

/**
 * 
 * @author wuheng@iscas.ac.cn
 * @since  2.0.2
 */
public class ServerConfig {

	protected int port = 8080;
	
	protected ServletConfig servlet;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ServletConfig getServlet() {
		return servlet;
	}

	public void setServlet(ServletConfig servlet) {
		this.servlet = servlet;
	}

	public static class ServletConfig {
		
		protected String contextPath = "";

		public String getContextPath() {
			return contextPath;
		}

		public void setContextPath(String contextPath) {
			this.contextPath = contextPath;
		}
		
	}
}
