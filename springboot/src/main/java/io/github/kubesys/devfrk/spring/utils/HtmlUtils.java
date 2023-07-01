/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.utils;

import java.util.Arrays;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * @author  wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since   2023/07/01
 */
public class HtmlUtils {

	private HtmlUtils() {
		super();
	}

	public static String toHtml(String md) {
		Parser parser = Parser.builder().build();
        Node document = parser.parse(md);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
	}
	
	public static String toTable(String md) {
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
	
}
