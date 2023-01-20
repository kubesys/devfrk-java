/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.kubesys.devfrk.spring.assists.HttpResponse;
import io.github.kubesys.devfrk.spring.utils.JSONUtils;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.0.0
 * 
 * The {@code HttpRequestConsumer} class is used to dispatch request to the
 * related handler, if the handler is not found, it would throw an exception.
 */
@RestController
@Component
public class HttpRequestConsumer implements ApplicationContextAware {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(HttpRequestConsumer.class.getName());

	
	@Value("${server.servlet.context-path}")
    private String path;
	
	@Autowired
	protected RequestHandlerMapper mapper;
	
	protected ApplicationContext ctx;
	
	
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
	@PostMapping(value = { "/**/login*", "/**/logout*", "/**/add*", "/**/create*", "/**/new*",
			"/**/insert*", "/**/clone*", "/**/attach*", "/**/plug*", "/**/set*", "/**/bind*", "/**/solve*" })
	public @ResponseBody String createTypeRequest(
			HttpServletRequest request, 
			@RequestBody JsonNode body)
			throws Exception {
		return doResponse(mapper.getCustomPath(request), body);
	}

	/**
	 * @param request servlet path should be startwith 'delete', or 'remove'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.DELETE }, value = { "/**/delete*", "/**/remove*",
			"/**/eject*", "/**/detach*", "/**/unplug*", "/**/unset*", "/**/unbind*" })
	public @ResponseBody String deleteTypeRequest(
			HttpServletRequest request, 
			@RequestBody JsonNode body)
			throws Exception {
		return doResponse(mapper.getCustomPath(request), body);
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
	public @ResponseBody String updateTypeRequest(
			HttpServletRequest request, 
			@RequestBody JsonNode body)
			throws Exception {
		return doResponse(mapper.getCustomPath(request), body);
	}

	/**
	 * @param request servlet path should be startwith 'get', 'list', or 'describe'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@PostMapping(value = { "/**/index*", "/**/mock*", "/**/user*",
			"/**/get*", "/**/list*", "/**/query*", "/**/describe*", "/**/retrieve*", "/**/echo*", "/**/exec*" })
	public @ResponseBody String retrieveTypeGetRequest(
			HttpServletRequest request,
			@RequestBody JsonNode body) 
			throws Exception {
		return doResponse(mapper.getCustomPath(request), body);
	}
	
	/**
	 * @param request servlet path should be startwith 'get', 'list', or 'describe'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@GetMapping(value = { "/**/index*", "/**/mock*", "/**/user*",
			"/**/get*", "/**/list*", "/**/query*", "/**/describe*", "/**/retrieve*", "/**/echo*", "/**/exec*" })
	public @ResponseBody String retrieveTypeGetRequest(
			HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> body) 
			throws Exception {
		return doResponse(mapper.getCustomPath(request), JSONUtils.toJsonNode(body));
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
		return ((HttpResponse) getBean("resp")).fail(e);
	}
	
	@ResponseBody
	public String invalidRequest(HttpServletRequest request, Exception e) throws Exception {
		return ((HttpResponse) getBean("resp")).fail(e);
	}
	

	/**************************************************
	 * 
	 * Response encapsulation
	 * 
	 **************************************************/

	/**
	 * @param customPath                   customPath
	 * @param body                          body
	 * @return                              resp
	 * @throws Exception                    exception
	 */
	protected String doResponse(String customPath, JsonNode body) throws Exception {

		m_logger.log(Level.INFO, () -> "Begin to deal with " + customPath);

		long start = System.currentTimeMillis();
		try {

			Object result = mapper.execHttpHandler(
					mapper.getHttpHandler(customPath),
					customPath, body);

			m_logger.log(Level.INFO, () -> "Successfully deal with " + customPath);
			return ((HttpResponse) getBean("resp")).success(result);
		} catch (Exception ex) {
			if (ex instanceof InvocationTargetException) {
				StringBuilder sb = new StringBuilder();
				sb.append(((InvocationTargetException) ex).getTargetException());
				throw new InvocationTargetException(ex, sb.toString());
			} else {
				throw ex;
			}
			
		} finally {
			long end = System.currentTimeMillis();
			m_logger.log(Level.INFO, () -> customPath + "," + (end - start) + "ms");
		}
		
	}
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (ctx == null) {
			ctx = applicationContext;
		}
	}

	/**
	 * @param name                                name
	 * @return                                    obj
	 * @throws Exception                          exception
	 */
	public Object getBean(String name) throws BeansException {
		return ctx.getBean(name);
	}
}
