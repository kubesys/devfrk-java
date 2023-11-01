/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.kubesys.devfrk.spring.auth.AuthingModel;
import io.github.kubesys.devfrk.spring.config.LocalConfigServer;
import io.github.kubesys.devfrk.spring.constants.BeanConstants;
import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.spring.exs.InternalInvalidTokenException;
import io.github.kubesys.devfrk.spring.exs.InternalInvalidUrlException;
import io.github.kubesys.devfrk.spring.resp.HttpResponse;
import io.github.kubesys.devfrk.spring.utils.JSONUtils;
import io.github.kubesys.devfrk.spring.utils.RegexpUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 * 
 *        The {@code HttpRequestConsumer} class is used to dispatch request to
 *        the related handler, if the handler is not found, it would throw an
 *        exception.
 */
@RestController
@Component
public class HttpRequestConsumer implements ApplicationContextAware {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(HttpRequestConsumer.class.getName());

	/**
	 * HttpHandler映射器
	 */
	@Autowired
	protected RequestHandlerMapper mapper;

	@Autowired
	protected LocalConfigServer configServer;

	/**
	 * 应用上下文
	 */
	protected ApplicationContext ctx;

	/**************************************************
	 * 
	 * forward all requests
	 * 
	 **************************************************/

	@GetMapping(value = { "/**/**" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String forward(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Map<String, String> body) throws Exception {
		return forward(request, response, JSONUtils.from(body));
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.DELETE }, value = { "/**/**" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String forward(HttpServletRequest request, HttpServletResponse response,
			@RequestBody JsonNode body) throws Exception {
		
		String type = request.getMethod();
		String regexp = configServer.getString(this.getClass().getSimpleName(), type);
		
		String servletPath = request.getServletPath();
		if (!RegexpUtils.startWith(regexp, servletPath)) {
			throw new InternalInvalidUrlException(ExceptionConstants.INVALID_REQUEST_URL);
		}
		
		boolean authorised = true;
		
		Object beanInstance = getBean(BeanConstants.AUTHING); 
		if (beanInstance != null && !servletPath.endsWith("/login")) {
			String auth = request.getHeader("authorization");
			String[] parts = auth.split("\\s+");
			String user = request.getHeader("user");
			AuthingModel authModel = new AuthingModel(parts[0], parts[1], user);
			String kind = body.has("fullkind") ? 
						body.get("fullkind").asText() : 
							JSONUtils.toKubeFullKind(body);
			authorised = ((HttpAuthingInterceptor) beanInstance).check(authModel, type, kind);
		}
		
		if (!authorised) {
			throw new InternalInvalidTokenException(ExceptionConstants.INVALID_REQUEST_TOKEN);
		}
		return doResponse( mapper.getCustomPath(request), body);
	}

	/**************************************************
	 * 
	 * handle all responses
	 * 
	 **************************************************/

	/**
	 * @param request request
	 * @param e       exception
	 * @return resp
	 * @throws Exception exception
	 */
	@ExceptionHandler
	@ResponseBody
	public String invalidResponse(Exception e) {
		return ((HttpResponse) getBean(BeanConstants.RESPONSE)).fail(e);
	}

	@ResponseBody
	public String invalidRequest(Exception e) {
		return ((HttpResponse) getBean(BeanConstants.RESPONSE)).fail(e);
	}

	/**
	 * @param customPath customPath
	 * @param body       body
	 * @return resp
	 * @throws Exception exception
	 */
	protected String doResponse(String customPath, JsonNode body) throws Exception {

		m_logger.log(Level.INFO, () -> "Begin to deal with " + customPath);

		long start = System.currentTimeMillis();
		try {

			m_logger.log(Level.INFO, () -> body.toPrettyString());
			Object result = mapper.execHttpHandler(mapper.getHttpHandler(customPath), customPath, body);
			m_logger.log(Level.INFO, () -> "Successfully deal with " + customPath);
			
			HttpResponse httpResponse = (HttpResponse) getBean(BeanConstants.RESPONSE) == null ?
					(HttpResponse) getBean(BeanConstants.DEF_RESPONSE) : (HttpResponse) getBean(BeanConstants.RESPONSE);
			return httpResponse.success(result);
		} catch (Exception ex) {
			if (ex instanceof InvocationTargetException ite) {
				return invalidResponse((Exception) ite.getTargetException());
			} else {
				throw ex;
			}

		} finally {
			long end = System.currentTimeMillis();
			m_logger.log(Level.INFO, () -> customPath + "," + (end - start) + "ms");
		}

	}

	/**************************************************
	 * 
	 * Context
	 * 
	 **************************************************/

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (ctx == null) {
			ctx = applicationContext;
		}
	}

	/**
	 * @param name name
	 * @return obj
	 */
	public Object getBean(String name) {
		try {
			return ctx.getBean(name);
		} catch (NoSuchBeanDefinitionException ex) {
			return null;
		}
	}
	
}
