/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.github.kubesys.devfrk.spring.config.LocalConfigServer;
import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.spring.exs.MissingConfigFileException;
import io.github.kubesys.devfrk.spring.exs.MissingConfigItemException;
import io.github.kubesys.devfrk.spring.utils.ClassUtils;
import io.github.kubesys.devfrk.spring.utils.HtmlUtils;
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
public class DocumentGenerator implements ApplicationContextAware {

	/**
	 * logger
	 */
	public static final Logger m_logger = Logger.getLogger(DocumentGenerator.class.getName());

	/**
	 * 配置中心
	 */
	@Autowired
	protected LocalConfigServer configServer;
	
	/**
	 * 应用上下文
	 */
	protected ApplicationContext ctx;



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
	
	/**************************************************
	 * 
	 * APIs and Changelog
	 * 
	 **************************************************/

	@GetMapping(value = { "/" }, produces = MediaType.TEXT_HTML_VALUE)
	public String index(HttpServletRequest request) {

		StringBuilder markdown = new StringBuilder();

		try {
			JsonNode json = configServer.getJSON(this.getClass().getSimpleName(), "DOC");

			markdown.append("## " + json.get("name").asText() + "\n\n");

			ObjectNode objectNode = (ObjectNode) json.get("values");

			objectNode.fields().forEachRemaining(entry -> {
				String key = entry.getKey();
				JsonNode value = entry.getValue();

				if (value.has("name")) {
					markdown.append("- [").append(value.get("name").asText()).append("](")
							.append(request.getContextPath() + key).append(")\n");
				}
			});

			return HtmlUtils.toHtml(markdown.toString());
		} catch (Exception ex) {
			throw new MissingConfigFileException(ExceptionConstants.MISSING_CONFIG_FILE + ex);
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/*" }, produces = MediaType.TEXT_HTML_VALUE)
	public String docs(HttpServletRequest request) throws Exception {

		JsonNode config = getConfig(request);

		// 找到所有的类，扫描速度与引入第三方包数量成正比
		// 暂不解决性能问题
		Class<Annotation> label = (Class<Annotation>) 
					Class.forName(config.get("scan").asText());
					
		Map<String, List<Class<?>>> groups = createGroups(
				config, label, ClassUtils.scan(new String[] { "", }, label));
		
		StringBuilder markdown = new StringBuilder();

		for (JsonNode currentConfig : config.get("values")) {
			if ("table".equals(currentConfig.get("type").asText())) {
			    for (Entry<String, List<Class<?>>> entry : groups.entrySet()) {
			    	
			    	String group = createGroup(markdown, entry);
					
			    	StringBuilder table = new StringBuilder();
					
			    	List<String> params = createTableHeader(currentConfig, table);
					
					String kind = currentConfig.get("kind").asText();
					for (Class<?> cls : groups.get(group)) {
						table.append("\n | ");
						for (String str : params) {
							if ("Annotation".equals(kind)) {
								Annotation a = cls.getAnnotation(label);
								String v = (String) a.annotationType().getMethod(str).invoke(a);
								table.append(v).append(" | ");
							}
						}
					}
					
					markdown.append(HtmlUtils.toTable(table.toString()));
					
				}
			}
		}

		return HtmlUtils.toHtml(markdown.toString());
	}

	private String createGroup(StringBuilder markdown, Entry<String, List<Class<?>>> entry) {
		String group = entry.getKey();
		markdown.append("\n\n## ").append(group).append("\n\n");
		return group;
	}

	private List<String> createTableHeader(JsonNode currentConfig, StringBuilder table) {
		JsonNode params = currentConfig.get("mapper");
		
		table.append("\n | ");
		for (JsonNode item : params) {
			table.append(item.get("name").asText()).append(" | ");
		}
		
		List<String> paramList = new ArrayList<>(); 
		table.append("\n | ");
		for (JsonNode item : params) {
			table.append(" ---- | ");
			paramList.add(item.get("value").asText());
		}
		return paramList;
	}

	private Map<String, List<Class<?>>> createGroups(JsonNode currentConfig, Class<Annotation> targetAnnotaion,
			Set<Class<?>> targetClasses)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String, List<Class<?>>> groupMapper = new LinkedHashMap<>();
		String groupBy = currentConfig.get("groupBy").asText();
		for (Class<?> c : targetClasses) {
			Annotation a = c.getAnnotation(targetAnnotaion);
			// 
			String groupName = (String) a.annotationType().getMethod(groupBy).invoke(a);
			
			List<Class<?>> list = groupMapper.get(groupName) == null ? 
						new ArrayList<>() : groupMapper.get(groupName);
			
			list.add(c);
			groupMapper.put(groupName, list);
		}
		return groupMapper;
	}

	private JsonNode getConfig(HttpServletRequest request) {
		// 查看这个请求是否有对应的文档
		JsonNode allConfig = configServer.getJSON(this.getClass()
							.getSimpleName(), "values");

		String servletPath = request.getServletPath();
		if (!allConfig.has(servletPath)) {
			throw new MissingConfigItemException(ExceptionConstants.MISSING_CONFIG_ITEM + servletPath);
		}

		// 根据servletPath获取当前的配置
		return allConfig.get(servletPath);
	}
}
