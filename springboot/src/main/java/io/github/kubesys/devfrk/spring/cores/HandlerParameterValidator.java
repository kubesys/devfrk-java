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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.devfrk.spring.exs.InvalidParameterValueException;
import io.github.kubesys.devfrk.spring.utils.JavaUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 */
@Component
public class HandlerParameterValidator {

	protected Validator objValidator = Validation.buildDefaultValidatorFactory().getValidator();
	
	protected StringValidator stringValidator = new StringValidator();

	/**
	 * @param body         body
	 * @param targetMethod target
	 * @return objects
	 * @throws Exception exception
	 */
	public Object[] validateParameters(JsonNode body, Method targetMethod) throws Exception {

		Object[] allParamsValue = getAllParamsFrom(targetMethod);
		for (int i = 0; i < allParamsValue.length; i++) {
			String thisParamName = getParamName(targetMethod, i);
			Class<?> thisParamType = getParamType(targetMethod, i);
			allParamsValue[i] = getParamValue(thisParamName, thisParamType, body);
			Object thisParamValue = allParamsValue[i];
			Annotation[] thisParamAnnos = getParamAnnos(targetMethod, i);

//			if (JavaUtils.isPrimitive(thisParamType)) {
//				validatePrimitiveType(thisParamName, thisParamValue, thisParamAnnos);
//			} else {
//				validateObjectType(thisParamValue);
//			}

		}
		return allParamsValue;
	}

	private Annotation[] getParamAnnos(Method targetMethod, int i) {
		try {
			return targetMethod.getAnnotatedParameterTypes()[i].getAnnotations();
		} catch (Exception ex) {
			return new Annotation[0];
		}
	}

	private Object getParamValue(String thisParamName, Class<?> thisParamType, JsonNode body) throws Exception {
		if (thisParamType.isAssignableFrom(String.class)) {
			return body.has(thisParamName) ? body.get(thisParamName).asText() : null;
		} else if (JavaUtils.isPrimitive(thisParamType)) {
			return body.has(thisParamName) ? body.get(thisParamName).asInt() : 0;
		} else {
			return body.has(thisParamName)
					? new ObjectMapper().readValue(body.get(thisParamName).toPrettyString(), thisParamType)
					: null;
		}
	}

	private Class<?> getParamType(Method targetMethod, int i) {
		return targetMethod.getParameters()[i].getType();
	}

	private String getParamName(Method targetMethod, int i) {
		return targetMethod.getParameters()[i].getName();
	}

	private Object[] getAllParamsFrom(Method targetMethod) {
		int parameterCount = targetMethod.getParameterCount();
		return (parameterCount == 0) ? new Object[0] : new Object[parameterCount];
	}

	private <T> void validatePrimitiveType(String name, T obj, Annotation[] as) throws InvalidParameterValueException {
		
		String errMsg = null;
		if (obj.getClass().isAssignableFrom(String.class)) {
			errMsg = stringValidator.validate(obj, as);
		}
		
		if (errMsg != null) {
			throw new InvalidParameterValueException(name + ":" + errMsg);
		}
	}

	private <T> void validateObjectType(T obj) throws InvalidParameterValueException, JsonProcessingException {
		Set<ConstraintViolation<T>> set = objValidator.validate(obj);

		if (set != null && !set.isEmpty()) {

			Map<String, String> errorMsg = new HashMap<>();

			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			}

			throw new InvalidParameterValueException(new ObjectMapper().writeValueAsString(errorMsg));
		}

	}

	
	/**
	 * @author wuheng@iscas.ac.cn
	 *
	 */
	public static class StringValidator {
		
		public <T> String validate(T obj, Annotation[] as) {
			for (Annotation a : as) {
				int len = obj.toString().length();
				if (a.annotationType().getTypeName().equals(Size.class.getName())) {
					Size size = (Size) a;
					if (size.min() > len || size.max() < len) {
						return "The length must be between " + size.min() + " and " + size.max();
					}
				} else if (a.annotationType().getTypeName().equals(Min.class.getName())) {
					Min min = (Min) a;
					if (min.value() > len) {
						return "The length must be great than " + min.value();
					}
				} else if (a.annotationType().getTypeName().equals(Max.class.getName())) {
					Max max = (Max) a;
					if (max.value() < len) {
						return "The length must be less than " + max.value();
					}
				} else if (a.annotationType().getTypeName().equals(Valid.class.getName())) {
					continue;
				} else {
					return "only support jakarta.validation.Valid, jakarta.validation.constraints.Size, jakarta.validation.constraints.Min and jakarta.validation.constraints.Max";
				}
			}
			
			return null;
		}
	}
	
}
