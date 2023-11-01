/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since   2023/06/30
 */
public class RegexpUtils {

	private RegexpUtils() {
		super();
	}

	/**
	 * @param regexp
	 * @param input
	 * @return
	 */
	public static boolean startWith(String regexp, String input) {
		Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
	}
}
