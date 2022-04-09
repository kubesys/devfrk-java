/**
 * Copyright (2022, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.httpfrk.cores;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 * The {@code HttpAPIDoc} class is used 
 */
@Component
public class HttpAPIDoc {
	
	protected ObjectNode api = new ObjectMapper().createObjectNode();
	
	protected ObjectNode paths = new ObjectMapper().createObjectNode();

	public HttpAPIDoc() {
		api.put("openapi", "3.0.1");
		
		ObjectNode info = new ObjectMapper().createObjectNode();
		info.put("title", "OpenAPI 3.0");
		info.put("version", "v2.0.0");
		api.set("info", info);
		
		ArrayNode servers = new ObjectMapper().createArrayNode();
		ObjectNode server = new ObjectMapper().createObjectNode();
		server.put("url", "http://localhost:9080/httpfrk");
		server.put("description", "automatic generation");
		servers.add(server);
		api.set("servers", servers);
		
		api.set("paths", paths);
	}

	public void addPath (String type, String servicePath, Method serviceDetail) {
		ObjectNode detail = new ObjectMapper().createObjectNode();
		ObjectNode params = new ObjectMapper().createObjectNode();
		params.put("operationId", servicePath.replace("/", "-"));
		detail.set(type, params);
		paths.set(servicePath, detail);
	}
	
	public String getAPIDoc() {
		return api.toPrettyString();
	}
}
