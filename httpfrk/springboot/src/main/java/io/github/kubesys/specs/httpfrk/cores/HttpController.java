/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.specs.httpfrk.cores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.specs.httpfrk.cores.HttpValidator.ValidationResult;
import io.github.kubesys.specs.httpfrk.utils.JSONUtils;
import io.github.kubesys.specs.httpfrk.utils.JavaUtils;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.2.3
 * 
 * The {@code HttpController} class is used to dispatch request to the
 * related handler, if the handler is not found, it would throw an exception.
 */
@RestController
public class HttpController {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(HttpController.class.getName());

	/**
	 * handler means how to deal with the request for specified servletPath
	 */
	@Autowired
	protected HttpHandlerManager handlers;
	
	@Autowired
	protected HttpContext context;
	
	@Value("${server.servlet.context-path}")
    private String path;
	
	@Autowired
	protected HttpValidator validator;
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> servletContainer() {
		Set<ErrorPage> errorPages = new HashSet<>();
		errorPages.add(new ErrorPage(path + "/error/error"));
		return factory -> factory.setErrorPages(errorPages );
	}
	
	/**************************************************
	 * 
	 * Request Mapping
	 * 
	 **************************************************/

	/**
	 * @param request servlet path should be startwith 'add', 'create', or 'new'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/**/login*", "/**/logout*", "/**/add*", "/**/create*", "/**/new*",
			"/**/insert*", "/**/clone*", "/**/attach*", "/**/plug*", "/**/set*", "/**/bind*", "/**/solve*" })
	public @ResponseBody String createTypeRequest(HttpServletRequest request, @RequestBody JsonNode body)
			throws Exception {
		return doResponse(getServletPath(request), body);
	}

	/**
	 * @param request servlet path should be startwith 'delete', or 'remove'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.DELETE }, value = { "/**/delete*", "/**/remove*",
			"/**/eject*", "/**/detach*", "/**/unplug*", "/**/unset*", "/**/unbind*" })
	public @ResponseBody String deleteTypeRequest(HttpServletRequest request, @RequestBody JsonNode body)
			throws Exception {
		return doResponse(getServletPath(request), body);
	}

	/**
	 * @param request servlet path should be startwith 'update', 'modify', or
	 *                'replace'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = { "/**/update*", "/**/diff*", "/**/modify*",
			"/**/replace*", "/**/change*", "/**/resize*", "/**/tune*", "/**/revert*", "/**/convert*" })
	public @ResponseBody String updateTypeRequest(HttpServletRequest request, @RequestBody JsonNode body)
			throws Exception {
		return doResponse(getServletPath(request), body);
	}

	/**
	 * @param request servlet path should be startwith 'get', 'list', or 'describe'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/**/index*", "/**/mock*", "/**/user*",
			"/**/get*", "/**/list*", "/**/query*", "/**/describe*", "/**/retrieve*", "/**/echo*", "/**/exec*" })
	public @ResponseBody String retrievePostTypeRequest(HttpServletRequest request, @RequestBody JsonNode body)
			throws Exception {
		return doResponse(getServletPath(request), body);
	}

	/**
	 * @param request servlet path should be startwith 'get', 'list', or 'describe'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@RequestMapping(method = { RequestMethod.GET }, value = { "/**/index*", "/**/mock*", "/**/user*",
			"/**/get*", "/**/list*", "/**/query*", "/**/describe*", "/**/retrieve*", "/**/echo*", "/**/exec*" })
	public @ResponseBody String retrieveTypeGetRequest(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> body) throws Exception {
		return doResponse(getServletPath(request), JSONUtils.toJsonNode(body));
	}
	
	/**
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
//	@RequestMapping(value = { "/v3/api" })
//	public @ResponseBody String openAPI() throws Exception {
//		return apiDoc.getAPIDoc();
//	}

	
	/**
	 * @param request                         request
	 * @param e                               exception
	 * @return                                resp
	 * @throws Exception                      exception
	 */
	@ExceptionHandler
	@ResponseBody
	public String invalidResponse(HttpServletRequest request, Exception e) throws Exception {
		return ((HttpResponse) context.getBean("resp")).fail(e);
	}
	
	@ResponseBody
	public String invalidRequest(HttpServletRequest request, Exception e) throws Exception {
		return ((HttpResponse) context.getBean("resp")).fail(e);
	}
	
	/**
	 * @param request servlet path should be startwith 'get', 'list', or 'describe'
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 */
	protected String getServletPath(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length() + 1);
	}

	/**************************************************
	 * 
	 * Response encapsulation
	 * 
	 **************************************************/

	/**
	 * @param servletPath                   path
	 * @param body                          body
	 * @return                              resp
	 * @throws Exception                    exception
	 */
	protected String doResponse(String servletPath, JsonNode body) throws Exception {

		m_logger.info("Begin to deal with " + servletPath);

		long start = System.currentTimeMillis();
		Method hanlder = handlers.getHandler(servletPath);
		try {

			Object[] params = getParameters(body, hanlder);
			Object result = (params != null) ? hanlder.invoke(context.getInstance(servletPath), params)
					: hanlder.invoke(context.getInstance(servletPath));

			m_logger.info("Successfully deal with " + servletPath);
			return ((HttpResponse) context.getBean("resp"))
							.success(result);
		} catch (Exception ex) {
			StringBuffer sb = new StringBuffer();
			if (ex instanceof InvocationTargetException) {
				sb.append(((InvocationTargetException) ex).getTargetException());
			} else {
				sb.append(ex.getMessage()).append("\n");
				for (StackTraceElement ste : ex.getStackTrace()) {
					sb.append("\t").append(ste.getClassName() + "." + ste.getMethodName() + ":" + ste.getLineNumber()).append("\n");
				}
			}
			throw new Exception(sb.toString());
		} finally {
			long end = System.currentTimeMillis();
			m_logger.info(servletPath + "," + (end - start) + "ms");
		}
		
	}


	/**
	 * @param body                             body
	 * @param targetMethod                     target
	 * @return                                 objects 
	 * @throws Exception                       exception
	 */
	protected Object[] getParameters(JsonNode body, Method targetMethod) throws Exception {

		int parameterCount = targetMethod.getParameterCount();
		Object[] params = (parameterCount == 0) ? null : new Object[parameterCount];
		for (int i = 0; i < parameterCount; i++) {
			String name = targetMethod.getParameters()[i].getName();
			if (!body.has(name)) {
				String typeName = targetMethod.getParameters()[i].getType().getName();
				if (JavaUtils.isPrimitive(typeName) && !typeName.equals(String.class.getName())) {
					params[i] = 0;
				} else {
					params[i] = null;
				}
			} else {
				params[i] = new ObjectMapper().readValue(body.get(name).toPrettyString(),
					targetMethod.getParameterTypes()[i]);
			}
			
			checkParameter(params, i);
		}
		return params;

	}

	/**
	 * @param params                              params
	 * @param i                                   i
	 * @throws Exception                          exception
	 */
	protected void checkParameter(Object[] params, int i) throws Exception {
		ValidationResult result = validator.validateEntity(params[i]);
		if (result.isHasErrors()) {
			throw new Exception(new ObjectMapper().writeValueAsString(result.getErrorMsg()));
		}
	}

}
