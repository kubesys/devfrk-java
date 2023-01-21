/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.webfrk.orm.current.tools;


import java.io.IOException;

import io.github.kubesys.devfrk.spring.orm.tools.OrmGenerator;
import io.github.webfrk.orm.current.SpringORMBootServer;


/**
 * @author wuheng@iscas.ac.cn
 * @since  
 * 
 */
public class SpringORMGeneratorTest  {

	public static void main(String[] args) throws IOException {
		OrmGenerator gen = new OrmGenerator(SpringORMBootServer.class);
		gen.generate();
	}

}
