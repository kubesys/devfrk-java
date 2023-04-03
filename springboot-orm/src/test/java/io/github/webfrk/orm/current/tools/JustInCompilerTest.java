/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.webfrk.orm.current.tools;


import java.io.IOException;

import net.openhft.compiler.CompilerUtils;


/**
 * @author wuheng@iscas.ac.cn
 * @since  
 * 
 */
public class JustInCompilerTest  {

	public static void main(String[] args) throws IOException, Exception {
		 // dynamically you can call
		 String className = "mypackage.MyClass";
		 String javaCode = "package mypackage;\n" +
		                  "public class MyClass implements Runnable {\n" +
		                  "    public void run() {\n" +
		                  "        System.out.println(\"Hello World\");\n" +
		                  "    }\n" +
		                  "}\n";
		 Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, javaCode);
		 Runnable runner = (Runnable) aClass.newInstance();
		 runner.run();
	}

}
