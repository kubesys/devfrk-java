/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
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
package io.github.kubesys.devfrk.spring.resp;

/**
 * @author  wuheng@iscas.ac.cn
 * @version 2.3.0
 * @since   2023/06/28
 * 
 * <p>
 * The {@code HttpResponse} class represents the return
 * value should be bound to the web response body.
 */
public interface HttpResponse {

	/**
	 * @param obj 具体数据
	 * @return    正确的Http响应
	 */
	public String success(Object obj);
	
	/**
	 * @param ex 具体异常
	 * @return   错误的Http响应
	 */
	public String fail(Exception ex);
}
