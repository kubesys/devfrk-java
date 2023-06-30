/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.BeansException;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.github.kubesys.devfrk.spring.config.LocalConfigServer;
import io.github.kubesys.devfrk.spring.constants.BeanConstants;
import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.spring.exs.InternalInvalidUrlException;
import io.github.kubesys.devfrk.spring.resp.HttpResponse;
import io.github.kubesys.devfrk.spring.utils.ClassUtils;
import io.github.kubesys.devfrk.spring.utils.JSONUtils;
import io.github.kubesys.devfrk.spring.utils.RegexpUtils;
import io.github.kubesys.devfrk.tools.annotations.Description;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

	
	@GetMapping(value = {"/**/**"}, produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody String forward(HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> body) throws Exception {
		return forward(request, JSONUtils.from(body));
	}
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, value = {"/**/**"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String forward(HttpServletRequest request,
			@RequestParam(required = false) @RequestBody JsonNode body) throws Exception {
		String regexp = configServer.getString(this.getClass().getSimpleName(), request.getMethod());
		if (!RegexpUtils.startWith(regexp, request.getServletPath())) {
			throw new InternalInvalidUrlException(ExceptionConstants.INVALID_REQUEST_URL);
		}
		return doResponse(mapper.getCustomPath(request), body);
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
	public String invalidRequest(Exception e)  {
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
			return ((HttpResponse) getBean(BeanConstants.RESPONSE)).success(result);
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
	 * APIs and Changelog
	 * 
	 **************************************************/

	private String toHtml(String md) {
		Parser parser = Parser.builder().build();
        Node document = parser.parse(md);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
	}
	
	private String toTable(String md) {
		List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(md);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        return "<style>"
                + "table {"
                + "    border-collapse: collapse;"
                + "    width: 100%;"
                + "}"
                + "th, td {"
                + "    border: 1px solid black;"
                + "    padding: 8px;"
                + "    text-align: left;"
                + "}"
                + "</style>" 
                + renderer.render(document);
	}
	
	private String toUrl(String path) {
		return System.getenv("host") + path;
	}
	
	@RequestMapping(value = { "/" }, produces = MediaType.TEXT_HTML_VALUE)
	public String index() throws Exception {
		
		StringBuilder markdown = new StringBuilder();
		
		markdown.append("## 文档目录\n\n");
		
		try {
			JsonNode objectNode  =  configServer.getJSON(
					this.getClass().getSimpleName(), "DOC");
			
			objectNode.fields().forEachRemaining(entry -> {
	            String key = entry.getKey();
	            JsonNode value = entry.getValue();

	            markdown.append("- [" + value.toString() + "](")
	            	.append(toUrl("/" + key + "")).append(")\n");
	        });
			
			return toHtml(markdown.toString());
		} catch (Exception ex) {
			return "缺少配置文件config/http-request-consumer.json，"
					+ "请查看[开发规范](/specs#HttpRequestConsumer)";
		}
	}
	
	@RequestMapping(value = { "/changelogs" }, produces = MediaType.TEXT_HTML_VALUE)
	public String changelogs() throws Exception {

		LocalDate startDate = ctx.containsBean("projStart") ? (LocalDate) ctx.getBean("projStart") : LocalDate.now();
		
		MultiValuedMap<Integer, String> urlMarkdown = new HashSetValuedHashMap<>();
		Map<Method, String> urlsMap = _getAllUrls();
		
		List<Method> urls = new ArrayList<>();
		urls.addAll(urlsMap.keySet());
		
		MultiValuedMap<Integer, String> weekMap = new HashSetValuedHashMap<>();
		for (int i = 0; i < urls.size(); i++) {

			Method method = urls.get(i);
			
			Description desc1 = method.getDeclaringClass().getAnnotation(Description.class);
			Description desc2 = method.getAnnotation(Description.class);
			if (desc2 == null) {
				
				try {
					Class<?> superclass = method.getDeclaringClass().getSuperclass();
					
					desc2 = superclass.getMethod(method.getName(), 
							method.getParameterTypes()).getAnnotation(Description.class);
				} catch (Exception ex) {
					
				}
				
				if (desc2 == null) {
					continue;
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("- ").append(desc1 == null ? "" : desc1.desc() + ": ").append(desc2.desc()).append("(")
				.append(desc2.author()).append(",").append(desc2.time());
			
			if (method.getAnnotation(Deprecated.class) == null) {
				sb.append("):");
			} else {
				sb.append(", 废弃").append("):");
			}
			
			sb.append(urlsMap.get(method)).append("\n");
			
			String dateString = desc2.date();
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
		StringBuilder contentMd = new StringBuilder();
		StringBuilder menuMd = new StringBuilder();
		for (int week = weekMap.size() - 1; week >= 0; week--) {
			
			menuMd.append("-" + weekMap.get(week)).append("(#"+ week + ")").append("\n\n");
			contentMd.append("\n### <a id=\"" + week + "\"></a>").append(weekMap.get(week));
			if (plan.size() > week) {
				contentMd.append("\n\n **Tasks:**,").append(plan.get(week));
			}
			
			contentMd.append("\n\n");
			
			if (!urlMarkdown.containsKey(week)) {
				continue;
			}
			
			for (String urlDesc : urlMarkdown.get(week)) {
				contentMd.append(urlDesc);
			}
		}
		
		StringBuilder md = new StringBuilder();
		
		md.append("## 开发周报\n\n");
		md.append("*注意：*以下是代码自动生成，随着开发任务的进行，看到的数据持续更新\n\n");
		md.append(menuMd.toString());
		md.append(contentMd.toString());
		m_logger.info(md.toString());
		return toHtml(md.toString());

	}
	@RequestMapping(value = { "/apis-json" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public String apisJson() throws Exception {
		
		ObjectNode apis = new ObjectMapper().createObjectNode();
		
		ArrayNode supported = new ObjectMapper().createArrayNode();
		
		ArrayNode unsupported = new ObjectMapper().createArrayNode();
		
		for (String url : HttpHandlerRegistry.httpHandlers.keySet()) {
			try {
				
				Method apiMethod = HttpHandlerRegistry.httpHandlers.get(url);
				Description apiDesc = apiMethod.getDeclaredAnnotation(Description.class);
				
				ObjectNode apiJson = new ObjectMapper().createObjectNode();
				apiJson.put("请求名称", apiDesc.desc());
				apiJson.put("请求路径", url);
				
				ArrayNode paramArrayJson = new ObjectMapper().createArrayNode();
					
				for (Parameter param: apiMethod.getParameters()) {
					Description paramDesc = param.getDeclaredAnnotation(Description.class);
					ObjectNode paramJson = new ObjectMapper().createObjectNode();
					paramJson.put("参数名称", param.getName());
					paramJson.put("参数类型", param.getType().getName());
					paramJson.put("参数必填", paramDesc.required());
					paramJson.put("参数描述", paramDesc.desc());
					paramJson.put("参数正则", paramDesc.regexp());
					paramArrayJson.add(paramJson);
				}
				apiJson.set("参数", paramArrayJson);
				supported.add(apiJson);
			} catch (Exception ex) {
				unsupported.add(url);
			}
		}
		
		apis.set("正常APIs列表", supported);
		apis.set("异常APIs列表", unsupported);
		return apis.toPrettyString();
	}

	@RequestMapping(value = { "/models" }, produces = MediaType.TEXT_HTML_VALUE)
	public String model() throws Exception {
		
		StringBuilder menuMd = new StringBuilder();
		StringBuilder contentMd = new StringBuilder();
		int i = 0;
		if (ctx.containsBean("model")) {
			String pkg = (String) ctx.getBean("model");
			for (Class<?> cls : ClassUtils.scan(new String[] {pkg})) {
				Entity entity = cls.getAnnotation(Entity.class);
				if (entity == null) {
					continue;
				}
				
				Table table = cls.getAnnotation(Table.class);
				menuMd.append("- [").append(table == null ? cls.getSimpleName()
						.toLowerCase() : table.name()).append("](#"+ i + ")").append("\n\n");
				contentMd.append("\n\n### <a id=\"" + i + "\"></a>数据表").append(table == null ? 
						cls.getSimpleName().toLowerCase() : table.name()).append("\n");
				
				Description dd = cls.getAnnotation(Description.class);
				
				contentMd.append("\n 实现类:").append(cls.getName()).append("\n");
				contentMd.append("\n 作  用:").append(dd.desc()).append("\n");
				contentMd.append("\n 表描述:").append(cls.getName()).append("\n\n");
				
				StringBuilder tb = createModelTable(cls, dd);
				
				contentMd.append(toTable(tb.toString()));
				i++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("## 数据库模型").append("\n\n");
		sb.append(menuMd).append(contentMd);
		m_logger.info(sb.toString());
		return toHtml(sb.toString());
	}

	private StringBuilder createModelTable(Class<?> cls, Description dd) {
		StringBuilder tb= new StringBuilder();
		
		tb.append("| 列名  | 类型 | 描述 | 约束 | 主键 | 必填 |\n");
		tb.append("| ----  | ---- | ---- | ---- | ---- | --- |\n");
		for (Field field : cls.getDeclaredFields()) {
			tb.append("| ");
			Column column = field.getAnnotation(Column.class);
			if (column == null) {
				tb.append(field.getName()).append(" |" );
			} else {
				tb.append(column.name()).append(" | ");
			}
			tb.append(field.getType().getSimpleName()).append(" | ");
			Description desc = field.getAnnotation(Description.class);
			
			tb.append(desc.desc()).append(" | ");
			jakarta.validation.constraints.Pattern pattern = 
					field.getAnnotation(jakarta.validation.constraints.Pattern.class);
			if (pattern == null) {
				tb.append("满足" + field.getType().getSimpleName() + "约束").append(" | ");
			} else {
				tb.append(pattern.message()).append(" | ");
			}
			Id id = field.getAnnotation(Id.class);
			tb.append(id == null ? false : true).append(" | ");
			Nullable na = field.getAnnotation(Nullable.class);
			tb.append(na == null ? true : false).append(" |\n");
		}
		tb.deleteCharAt(tb.length() - 1);
		return tb;
	}
	
	@RequestMapping(value = { "/apis" }, produces = MediaType.TEXT_MARKDOWN_VALUE)
	public String apis() throws Exception {
		
		StringBuilder contentMd = new StringBuilder();
		
		for (String url : HttpHandlerRegistry.httpHandlers.keySet()) {
			try {
				
				Method apiMethod = HttpHandlerRegistry.httpHandlers.get(url);
				Description apiDesc = apiMethod.getDeclaredAnnotation(Description.class);
				
				contentMd.append("*请求名称*").append(apiDesc.desc());
				contentMd.append("\n请求路径:").append(url);
				contentMd.append("\n请求方法:").append("POST");
				
				contentMd.append("\n\n")
						.append(createParamTable(
								apiMethod.getParameters(), apiDesc));
				
			} catch (Exception ex) {
//				unsupported.add(url);
			}
		}
		
//		apis.set("正常APIs列表", supported);
//		apis.set("异常APIs列表", unsupported);
		return null;
	}
	
	private StringBuilder createParamTable(Parameter[] params, Description dd) {
		StringBuilder tb= new StringBuilder();
		
		tb.append("| 参数名称  | 参数类型 | 参数必填 | 参数描述 | 参数正则 |\n");
		tb.append("| ----  | ---- | ---- | ---- | ---- |\n");
		for (Parameter param :params) {
			tb.append("| ").append(param.getName())
				.append(" | ").append(param.getName())
				.append(" | ").append(dd.required())
				.append(" | ").append(dd.date())
				.append(" | ").append(dd.regexp())
				.append(" | ");
		}
		tb.deleteCharAt(tb.length() - 1);
		return tb;
	}
	
	@RequestMapping(value = { "/resources" }, produces = MediaType.TEXT_HTML_VALUE)
	public String resource() throws Exception {

		LocalDate startDate = ctx.containsBean("projStart") ? (LocalDate) ctx.getBean("projStart") : LocalDate.now();
		
		StringBuilder contentMd = new StringBuilder();
		StringBuilder menuMd = new StringBuilder();
		
		int weekCount = (int) ((LocalDate.now().toEpochDay() - startDate.toEpochDay()) / 7) + 1;
		for (int i = weekCount - 1; i >=0 ; i--) {
			LocalDate weekStartDate = startDate.plusWeeks(i);
			LocalDate weekEndDate = weekStartDate.plusDays(6);

			String formattedStartDate = weekStartDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			String formattedEndDate = weekEndDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			
			menuMd.append("- [" + formattedStartDate + " - " + formattedEndDate).append("](#"+ i + ")").append("\n\n");
			contentMd.append("\n### <a id=\"" + i + "\"></a>").append(formattedStartDate + " - " + formattedEndDate).append("\n\n");
			
			ArrayNode json = ctx.containsBean("resource") ? 
								(ArrayNode) ctx.getBean("resource") : 
									new ObjectMapper().createArrayNode();
			
			try {
				for (JsonNode text : json.get(i)) {
					contentMd.append("- ").append(text.asText()).append("\n");
				}
			} catch (Exception ex) {
				
			}
		}

		StringBuilder md = new StringBuilder();
		md.append("## *资源采集周报*\n\n");
		md.append("*注意：*以下是代码自动生成，随着抓取资源的增加，看到的数据持续更新\n\n");
		md.append(menuMd.toString());
		md.append(contentMd.toString());
		m_logger.info(md.toString());
		return toHtml(md.toString());

	}
	

	
	private Map<Method, String> _getAllUrls() {
		Map<Method, String> map = new HashMap<>();
		
		Map<String, Method> httpHandlers = HttpHandlerRegistry.httpHandlers;
		for (String url : httpHandlers.keySet()) {
			map.put(httpHandlers.get(url), url);
		}
		return map;
	}

	/**************************************************
	 * 
	 * Response encapsulation
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
	 * @throws Exception exception
	 */
	public Object getBean(String name) throws BeansException {
		return ctx.getBean(name);
	}
}
