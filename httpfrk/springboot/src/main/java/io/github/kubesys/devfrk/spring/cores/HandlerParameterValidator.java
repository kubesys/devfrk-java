/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.devfrk.spring.utils.JavaUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


/**
 * @author  wuheng@iscas.ac.cn
 * @since   2.0.0
 */
@Component
public class HandlerParameterValidator {

	protected Validator objValidator = Validation.buildDefaultValidatorFactory().getValidator();
	
	
	
	/**
	 * @param body                             body
	 * @param targetMethod                     target
	 * @return                                 objects 
	 * @throws Exception                       exception
	 */
	public Object[] validateParameters(JsonNode body, Method targetMethod) throws Exception {

		int parameterCount = targetMethod.getParameterCount();
		Object[] params = (parameterCount == 0) ? null : new Object[parameterCount];
		
		for (int i = 0; i < parameterCount; i++) {
			String name = targetMethod.getParameters()[i].getName();
			if (!body.has(name)) {
				String typeName = targetMethod.getParameters()[i].getType().getName();
				if (JavaUtils.isPrimitive(typeName) && !typeName.equals(String.class.getName())) {
					params[i] = 0;
				} else {
					params[i] = null;
				}
			} else {
				params[i] = new ObjectMapper().readValue(body.get(name).toPrettyString(),
					targetMethod.getParameterTypes()[i]);
			}
			
			ValidationResult result = null;
			if (JavaUtils.isPrimitive(params[i].getClass())) {
				Annotation[] as = null;
				try {
					as = targetMethod.getAnnotatedParameterTypes()[i].getAnnotations();
				} catch (Exception ex) {
					
				}
				result = validatePrimitiveType(params[i], as);
			} else {
				result = validateObjectType(params[i]);
			}
			if (result.isHasErrors()) {
				throw new Exception(new ObjectMapper().writeValueAsString(result.getErrorMsg()));
			}
		}
		return params;

	}
	
	private <T> ValidationResult validatePrimitiveType(T obj, Annotation[] as) {
		ValidationResult result = new ValidationResult();
		
		Map<String, String> errorMsg = new HashMap<>();
		if (!obj.getClass().isAssignableFrom(String.class)) {
			result.setHasErrors(true);
			errorMsg.put(obj + "is" + obj.getClass().getName(), "Only support String.class");
			result.setErrorMsg(errorMsg);
		} else {
			for (Annotation a : as) {
				int len = obj.toString().length();
				if (a.annotationType().getTypeName().equals(Size.class.getName())) {
					Size size = (Size) a;
					if (size.min() > len || size.max() < len) {
						result.setHasErrors(true);
						errorMsg.put(obj + " is " + obj.getClass().getName(), "The length must be between " + size.min() + " and " + size.max());
						result.setErrorMsg(errorMsg);
					}
				} else if (a.annotationType().getTypeName().equals(Min.class.getName())) {
					Min min = (Min) a;
					if (min.value() > len) {
						result.setHasErrors(true);
						errorMsg.put("len is invalid", "The length must be great than " +  min.value());
						result.setErrorMsg(errorMsg);
					} 
				} else if (a.annotationType().getTypeName().equals(Max.class.getName())) {
					Max max = (Max) a;
					if (max.value() < len) {
						result.setHasErrors(true);
						errorMsg.put("len is invalid", "The length must be less than " +  max.value());
						result.setErrorMsg(errorMsg);
					} 
				} else if (a.annotationType().getTypeName().equals(Valid.class.getName())) {
					continue;
				} else {
					result.setHasErrors(true);
					errorMsg.put("unsupport type", "only support jakarta.validation.Valid, jakarta.validation.constraints.Size, jakarta.validation.constraints.Min and jakarta.validation.constraints.Max");
					result.setErrorMsg(errorMsg);
				}
			}
		}
		return result;
	}
	
	private <T> ValidationResult validateObjectType(T obj) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = objValidator.validate(obj);

		if (set != null && !set.isEmpty()) {

			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<>();

			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}
	
	public static class ValidationResult {
		 
	    private boolean             hasErrors;
	 
	    private Map<String, String> errorMsg;

		public boolean isHasErrors() {
			return hasErrors;
		}

		public void setHasErrors(boolean hasErrors) {
			this.hasErrors = hasErrors;
		}

		public Map<String, String> getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(Map<String, String> errorMsg) {
			this.errorMsg = errorMsg;
		}
	    
	}
}
