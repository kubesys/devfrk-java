/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.devfrk.spring.constants.ExceptionConstants;
import io.github.kubesys.devfrk.spring.exs.MissingConfigFileException;
import io.github.kubesys.devfrk.spring.exs.MissingConfigItemException;
import jakarta.annotation.PostConstruct;

/**
 * @author wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since 2023/06/29
 * 
 *        目前只支持String, int, long, boolean和Json，其中JSONNode 交给开发者自己解决
 */
@Configuration
public class LocalConfigServer implements AbstractConfigServer {

	protected static final Logger m_logger = Logger.getLogger(LocalConfigServer.class.getName());

	public static final String DEFAULT_DIR = "config";

	public static final String CONFIG_DIR = System.getenv(DEFAULT_DIR) == null ? DEFAULT_DIR
			: System.getenv(DEFAULT_DIR);

	public final Map<String, JsonNode> configValueHub = new HashMap<>();
	
	public final Map<String, String> configFileMapper = new HashMap<>();

	/**
	 * 配置文件如何不存在，或者不是JSON格式，则会在启动时候就报错 后台程序会无法正常启动
	 * 
	 * 配置的命名规则：对于类名中间用中画线，后缀xx可以随意 - 如TextCrawler对应配置位文件是config/text-crawler.xx -
	 * 如TextCrawlerTest对应配置文件是config/text-crawler-text.xx
	 * 
	 * 参见docs/spec.md
	 * 
	 * @throws IOException 程序退出
	 */
	@PostConstruct
	public void init() throws IOException {
		try {
			for (File file : new File(CONFIG_DIR).listFiles()) {
				try {
					String fileContent = Files.readString(file.toPath(), StandardCharsets.UTF_8);
					String key = toKey(file);
					configValueHub.put(key, new ObjectMapper().readTree(fileContent));
					configFileMapper.put(key, file.getName());
				} catch (Exception ex) {
					m_logger.log(Level.INFO,
							() -> ExceptionConstants.MISSING_CONFIG_FORMAT + CONFIG_DIR + "/" + file.getName());
					System.exit(1);
				}
			}
		} catch (NullPointerException ex) {
			m_logger.log(Level.INFO, () -> "no config");
		}
	}

	/**
	 * 不判断输入值，如果不存在，则在init中捕获，并异常退出
	 * 
	 * @param file 文件
	 * @return 对应的真实类名，如text-crawler.xx为TextCrawler，
	 *         text-crawler-text.xx为TextCrawlerTest
	 */
	private static String toKey(File file) {
		StringBuilder sb = new StringBuilder();
		String[] parts = file.getName().split("\\.")[0].split("-");
		for (String part : parts) {
			String name = part.substring(0, 1).toUpperCase() + part.substring(1);
			sb.append(name);

		}
		return sb.toString();
	}

	@Override
	public String getString(String kind, String key) {
		return getJSON(kind, key).asText();
	}

	@Override
	public int getInt(String kind, String key) {
		return getJSON(kind, key).asInt();
	}

	@Override
	public long getLong(String kind, String key) {
		return getJSON(kind, key).asLong();
	}

	@Override
	public boolean getBoolean(String kind, String key) {
		return getJSON(kind, key).asBoolean();
	}

	@Override
	public JsonNode getJSON(String kind, String key) {
		checkKey(kind);
		JsonNode node = configValueHub.get(kind);
		for (String part : key.split("\\.")) {
			if (!node.has(part)) {
				throw new MissingConfigItemException(ExceptionConstants.MISSING_CONFIG_ITEM 
							+ "no " + key + " in " + configFileMapper.get(kind));
			}
			node = node.get(part);
		}
		return node;
	}

	/**
	 * @param kind 根据docs/spec.md，kind即为类名
	 */
	private void checkKey(String kind) {
		if (!configValueHub.containsKey(kind)) {
			throw new MissingConfigFileException(
					ExceptionConstants.MISSING_CONFIG_FILE + "please check " + configFileMapper.get(kind));
		}
	}

}
