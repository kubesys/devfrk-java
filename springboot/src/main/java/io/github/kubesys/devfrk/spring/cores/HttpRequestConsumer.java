/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
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
import io.github.kubesys.devfrk.spring.exs.HttpFramworkException;
import io.github.kubesys.devfrk.spring.utils.JSONUtils;
import io.github.kubesys.devfrk.tools.annotations.Description;
import jakarta.servlet.http.HttpServletRequest;

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
	@PostMapping(value = { "/**/login*", "/**/logout*", "/**/add*", "/**/create*", "/**/new*", "/**/generate*",
			"/**/insert*", "/**/clone*", "/**/attach*", "/**/plug*", "/**/set*", "/**/bind*",
			"/**/solve*" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createTypeRequest(HttpServletRequest request, @RequestBody JsonNode body)
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
			"/**/eject*", "/**/detach*", "/**/unplug*", "/**/unset*",
			"/**/unbind*" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteTypeRequest(HttpServletRequest request, @RequestBody JsonNode body)
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
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = { "/**/update*", "/**/diff*",
			"/**/modify*", "/**/replace*", "/**/change*", "/**/resize*", "/**/tune*", "/**/revert*",
			"/**/convert*" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateTypeRequest(HttpServletRequest request, @RequestBody JsonNode body)
			throws Exception {
		return doResponse(mapper.getCustomPath(request), body);
	}

	/**
	 * @param request servlet path should be startwith 'get', 'list', or 'describe'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@PostMapping(value = { "/**/index*", "/**/mock*", "/**/user*", "/**/ask*", "/**/upload*", "/**/from*", "/**/get*",
			"/**/list*", "/**/query*", "/**/describe*", "/**/retrieve*", "/**/echo*",
			"/**/exec*" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String retrieveTypeGetRequest(HttpServletRequest request, @RequestBody JsonNode body)
			throws Exception {
		return doResponse(mapper.getCustomPath(request), body);
	}

	/**
	 * @param request servlet path should be startwith 'get', 'list', or 'describe'
	 * @param body    just body
	 * @return the {@code HttpBodyHandler} result. In fact, it may be an exception.
	 * @throws Exception it can be any exception that {@code HttpBodyHandler} throws
	 */
	@GetMapping(value = { "/**/index*", "/**/mock*", "/**/user*", "/**/ask*", "/**/upload*", "/**/from*", "/**/get*",
			"/**/list*", "/**/query*", "/**/describe*", "/**/retrieve*", "/**/echo*",
			"/**/exec*" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String retrieveTypeGetRequest(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> body) throws Exception {
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

	/**************************************************
	 * 
	 * APIs and Changelog
	 * 
	 **************************************************/

//	@RequestMapping(value = { "/apis" }, produces = MediaType.APPLICATION_JSON_VALUE)
//	public String apis() throws Exception {
//		
//		ObjectNode apis = new ObjectMapper().createObjectNode();
//		
//		ArrayNode supported = new ObjectMapper().createArrayNode();
//		
//		ArrayNode unsupported = new ObjectMapper().createArrayNode();
//		
//		for (String url : HttpHandlerRegistry.httpHandlers.keySet()) {
//			try {
//				
//				Method apiMethod = HttpHandlerRegistry.httpHandlers.get(url);
//				Description apiDesc = apiMethod.getDeclaredAnnotation(Description.class);
//				
//				ObjectNode apiJson = new ObjectMapper().createObjectNode();
//				apiJson.put("请求名称", apiDesc.desc());
//				apiJson.put("请求路径", url);
//				
//				ArrayNode paramArrayJson = new ObjectMapper().createArrayNode();
//				
//				for (Parameter param: apiMethod.getParameters()) {
//					Description paramDesc = param.getDeclaredAnnotation(Description.class);
//					ObjectNode paramJson = new ObjectMapper().createObjectNode();
//					paramJson.put("参数名称", param.getName());
//					paramJson.put("参数类型", param.getType().getName());
//					paramJson.put("参数必填", paramDesc.required());
//					paramJson.put("参数描述", paramDesc.desc());
//					paramJson.put("参数正则", paramDesc.regexp());
//					paramArrayJson.add(paramJson);
//				}
//				apiJson.set("参数", paramArrayJson);
//				supported.add(apiJson);
//			} catch (Exception ex) {
//				unsupported.add(url);
//			}
//		}
//		
//		apis.set("正常APIs列表", supported);
//		apis.set("异常APIs列表", unsupported);
//		return apis.toPrettyString();
//	}

	@RequestMapping(value = { "/changelog" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public String changelog() throws Exception {

		LocalDate startDate = ctx.containsBean("projStart") ? (LocalDate) ctx.getBean("projStart") : LocalDate.now();
		
		MultiValuedMap<Integer, String> urlMarkdown = new HashSetValuedHashMap<>();
		Map<Method, String> urlsMap = _getAllUrls();
		
		List<Method> urls = new ArrayList<>();
		urls.addAll(urlsMap.keySet());
		
		MultiValuedMap<Integer, String> weekMap = new HashSetValuedHashMap<>();
		for (int i = 0; i < urls.size(); i++) {

			Method method = urls.get(i);
			Description desc = method.getAnnotation(Description.class);
			if (desc == null) {
				continue;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("- ").append(desc.desc()).append("(")
				.append(desc.author()).append(",").append(desc.time());
			
			if (method.getAnnotation(Deprecated.class) == null) {
				sb.append("):");
			} else {
				sb.append(", 废弃").append("):");
			}
			
			sb.append(urlsMap.get(method)).append("\n");
			
			String dateString = desc.date();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate thisDate = LocalDate.parse(dateString, formatter);
			Period period = startDate.until(thisDate);
			
			int idx = period.getDays() / 7;
			urlMarkdown.put(idx, sb.toString());
		}
		
		// week plan
		LocalDate currentDate = LocalDate.now();

		List<String> plan = ctx.containsBean("projPlan") ? (List<String>) ctx.getBean("projPlan") : new ArrayList<>();
		int weekCount = (int) ((currentDate.toEpochDay() - startDate.toEpochDay()) / 7) + 1;
		for (int i = 0; i < weekCount; i++) {
			LocalDate weekStartDate = startDate.plusWeeks(i);
			LocalDate weekEndDate = weekStartDate.plusDays(6);

			String formattedStartDate = weekStartDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			String formattedEndDate = weekEndDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

			weekMap.put(i, formattedStartDate + " - " + formattedEndDate);
		}

		
		// markdown
		StringBuilder allMarkdown = new StringBuilder();
		allMarkdown.append("# *开发周报*\n\n");
		for (int week = weekMap.size() - 1; week >= 0; week--) {
			
			allMarkdown.append("\n## ").append(weekMap.get(week));
			if (plan.size() > week) {
				allMarkdown.append(",").append(plan.get(week));
			}
			
			allMarkdown.append("\n\n");
			
			if (!urlMarkdown.containsKey(week)) {
				continue;
			}
			
			for (String urlDesc : urlMarkdown.get(week)) {
				allMarkdown.append(urlDesc);
			}
		}
		
		return allMarkdown.toString();

	}

	private Map<Method, String> _getAllUrls() {
		Map<Method, String> map = new HashMap<>();
		
		Map<String, Method> httpHandlers = HttpHandlerRegistry.httpHandlers;
		for (String url : httpHandlers.keySet()) {
			map.put(httpHandlers.get(url), url);
		}
		return map;
	}

	/**
	 * @param request request
	 * @param e       exception
	 * @return resp
	 * @throws Exception exception
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
	 * @param customPath customPath
	 * @param body       body
	 * @return resp
	 * @throws Exception exception
	 */
	protected String doResponse(String customPath, JsonNode body) throws Exception {

		m_logger.log(Level.INFO, () -> "Begin to deal with " + customPath);

		long start = System.currentTimeMillis();
		try {

			Object result = mapper.execHttpHandler(mapper.getHttpHandler(customPath), customPath, body);

			m_logger.log(Level.INFO, () -> "Successfully deal with " + customPath);
			return ((HttpResponse) getBean("resp")).success(result);
		} catch (Exception ex) {
			if (ex instanceof InvocationTargetException) {
				StringBuilder sb = new StringBuilder();
				sb.append(((InvocationTargetException) ex).getTargetException());
				throw new HttpFramworkException(402, sb.toString());
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
	 * @param name name
	 * @return obj
	 * @throws Exception exception
	 */
	public Object getBean(String name) throws BeansException {
		return ctx.getBean(name);
	}
}
