/**
 * Copyrigt (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.tools.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author wuheng@iscas.ac.cn
 * @since  1.1.0
 *
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MethodDesc {

	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an auto-detected component.
	 * @return the suggested component name, if any (or empty String otherwise)
	 */
	
	String desc() default "";
	
	String date() default "";
	
	String author() default "";
	
	String time() default "1小时";
	
}
