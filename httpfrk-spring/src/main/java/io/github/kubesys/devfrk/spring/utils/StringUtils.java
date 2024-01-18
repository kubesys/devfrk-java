/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kubesys.devfrk.spring.utils;

import java.util.Base64;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author wuheng@iscas.ac.cn
 * @since 0.1.0
 * @date 2023/05/16
 * 
 */

public class StringUtils {

	
	private StringUtils() {
	}

	public static String getField(String method) {
		String name = method.startsWith("get") ? method.substring(3) : method.substring(2);
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
	
	public static String getMethod(String field, boolean is) {
		String prefix = is ? "is" : "get";
		return prefix + field.substring(0, 1)
				.toUpperCase() + field.substring(1);
	}
	
	public static String getRandomString(int len) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString().toLowerCase();
	}
	
	public static boolean isBase64(String str) {
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Pattern, str);
    }
	
	public static String base64Encoder(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
    }
}
