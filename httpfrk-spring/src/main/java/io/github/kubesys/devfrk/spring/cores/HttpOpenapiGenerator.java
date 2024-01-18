/**
 * Copyright (2024, ) Institute of Software, Chinese Academy of Sciences
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
package io.github.kubesys.devfrk.spring.cores;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.devfrk.spring.config.LocalConfigServer;
import io.github.kubesys.devfrk.spring.utils.RegexpUtils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;


/**
 * @author wuheng@iscas.ac.cn
 * @since 2.2.8
 * 
 *        用于生成OpenAPI标准接口，请查看https://editor.swagger.io/
 */
@Component
public class HttpOpenapiGenerator {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(HttpOpenapiGenerator.class.getName());

	/**
	 * 
	 */
	protected final LocalConfigServer configServer;

	/**
	 * 
	 */
	protected final OpenAPI openAPI;

	@Autowired
	public HttpOpenapiGenerator(LocalConfigServer configServer) {
		super();
		this.configServer = configServer;
		this.openAPI = new ObjectMapper().convertValue(
				configServer.getJSON(this.getClass().getSimpleName()), 
				OpenAPI.class);
	}

	public OpenAPI getOpenAPI() {
		return openAPI;
	}

	public void addPath(String name, PathItem item) {
		Paths paths = this.openAPI.getPaths();
		if (paths == null) {
			paths = new Paths();
		}
		paths.addPathItem(name, item);
		this.openAPI.setPaths(paths);
	}
	
	public String getType(String path) {
		JsonNode jsonNode = configServer.getJSON(
				HttpRequestConsumer.class.getSimpleName());
		Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            String key = fields.next().getKey();
            String val = jsonNode.get(key).asText();
            
            if (RegexpUtils.startWith(val, path)) {
            	return key;
            }
        }
        m_logger.severe("Unsupport operator, it should be GET, POST, PUT or DELETE");
		return null;
	}
}
