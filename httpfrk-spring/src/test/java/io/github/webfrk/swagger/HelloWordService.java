/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.webfrk.swagger;

import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.OpenAPI;

/**
 * @author wuheng@iscas.ac.cn
 * @since
 * 
 *        <p>
 *        The {@code ApplicationServer} class is used for starting web
 *        applications. Please configure
 * 
 *        src/main/resources/application.yml src/main/resources/log4j.properties
 * 
 */
@ServiceDefinition
public class HelloWordService {

	@Operation(summary = "Get greeting", description = "Get a greeting message")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Resource not found") })
	public String getGreeting() {
		return "Hello, Swagger!";
	}

	public static void main(String[] args) {
		
	}
	
}
