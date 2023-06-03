/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.cores;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kubesys.devfrk.spring.exs.HttpFramworkException;
import io.github.kubesys.devfrk.spring.utils.JavaUtils;
import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 */
@Component
public class HandlerParameterValidator {

	protected Validator objValidator = Validation.buildDefaultValidatorFactory().getValidator();
	
	protected PrimitiveValidator primitiveValidator = new PrimitiveValidator();

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

			if (JavaUtils.isPrimitive(thisParamType)) {
				validatePrimitiveType(thisParamName, thisParamValue, thisParamAnnos);
			} else {
				validateObjectType(thisParamValue);
			}

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
			return body.has(thisParamName) ? body.get(thisParamName).asText() : "";
		} else if (JavaUtils.isInt(thisParamType)) {
			if (!body.has(thisParamName)) {
				return Integer.valueOf(-1);
			}
			JsonNode value = body.get(thisParamName);
			return value.isTextual() ? Integer.valueOf(value.asText()) : value.asInt();
		} else if (JavaUtils.isLong(thisParamType)) {
			if (!body.has(thisParamName)) {
				return Long.valueOf(-1);
			}
			JsonNode value = body.get(thisParamName);
			return value.isTextual() ? Long.valueOf(value.asText()) : value.asLong();
		} else if (JavaUtils.isBool(thisParamType)) {
			if (!body.has(thisParamName)) {
				return false;
			}
			JsonNode value = body.get(thisParamName);
			return value.isTextual() ? Boolean.valueOf(value.asText()) : value.asBoolean();
		}  else {
			return body.has(thisParamName)
					? new ObjectMapper().readValue(
							body.get(thisParamName).toPrettyString(), 
							thisParamType)
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

	private <T> void validatePrimitiveType(String name, T obj, Annotation[] as) throws Exception {
		
		String errMsg = primitiveValidator.validate(obj, as);
		
		if (errMsg != null) {
			throw new HttpFramworkException(401, "参数" + name + "取值错误:" + errMsg);
		}
	}

	private <T> void validateObjectType(T obj) throws Exception {
		Set<ConstraintViolation<T>> set = objValidator.validate(obj);

		if (set != null && !set.isEmpty()) {

			Map<String, String> errorMsg = new HashMap<>();

			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			}

			throw new HttpFramworkException(401, "对象参数" + obj + "取值错误：" 
						+ new ObjectMapper().writeValueAsString(errorMsg));
		}

	}

	
	/**
	 * @author wuheng@iscas.ac.cn
	 *
	 */
	public static class PrimitiveValidator {
		
		public <T> String validate(T obj, Annotation[] as) {
			
			List<String> errMgs = new ArrayList<>();
			
			for (Annotation a : as) {
				if (!a.annotationType().getTypeName().startsWith("jakarta.")) {
					continue;
				}
				
				int len = obj.toString().length();
				if (a.annotationType().getTypeName().equals(Size.class.getName())) {
					Size size = (Size) a;
					if (size.min() > len || size.max() < len) {
						errMgs.add("E401: The length must be between " + size.min() + " and " + size.max());
					}
				} else if (a.annotationType().getTypeName().equals(Min.class.getName())) {
					Min min = (Min) a;
					if (min.value() > len) {
						errMgs.add("E401: The length must be great than " + min.value());
					}
				} else if (a.annotationType().getTypeName().equals(Max.class.getName())) {
					Max max = (Max) a;
					if (max.value() < len) {
						errMgs.add("E401: The length must be less than " + max.value());
					}
				} else if (a.annotationType().getTypeName().equals(Pattern.class.getName())) {
					String pattern = ((Pattern) a).regexp();
					java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
					java.util.regex.Matcher matcher = regex.matcher(obj.toString());
					boolean isMatched = matcher.find();
			        if (!isMatched) {
			            errMgs.add("E401" + ((Pattern) a).message());
			        }
				} else if (a.annotationType().getTypeName().equals(Nullable.class.getName())) {
					if (String.class.getName().equals(obj.getClass().getName())) {
						if (obj == null || "".equals(obj)) {
							errMgs.clear();
							break;
						}
					} else if (Integer.class.getName().equals(obj.getClass().getName())
							|| Long.class.getName().equals(obj.getClass().getName())) {
						int val = (int) obj;
						if (val == -1) {
							errMgs.clear();
							break;
						}
					} 
				}  else {
					errMgs.add("E401: only support jakarta.validation.constraints.Size, jakarta.validation.constraints.Min "
							+ "and jakarta.validation.constraints.Max, and jakarta.validation.constraints.Pattern");
				}
				
			}
			
			return errMgs.size() != 0 ? errMgs.toString() : null;
		}
	}
	
}
