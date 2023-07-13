/**
 * Copyrigt (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.utils;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


/**
 * this class is used for checking whether a class is primitive. 
 * 
 * @author wuheng@iscas.ac.cn
 * @since  1.1.0
 * 
 */

public class JavaUtils {

	/**
	 * logger
	 */
	private static Logger m_logger = Logger.getLogger(JavaUtils.class.getName());
	
	/*********************************************************************
	 *
	 * 
	 * Java primitive
	 * 
	 * 
	 *********************************************************************/

	/**
	 * primitive type in Java
	 */
	private final static Set<String> m_primitive = new HashSet<String>();

	static {
		m_primitive.add(String.class.getName());
		m_primitive.add(Boolean.class.getName());
		m_primitive.add(Character.class.getName());
		m_primitive.add(Byte.class.getName());
		m_primitive.add(Short.class.getName());
		m_primitive.add(Integer.class.getName());
		m_primitive.add(Long.class.getName());
		m_primitive.add(Double.class.getName());
		m_primitive.add(Float.class.getName());
		m_primitive.add("boolean");
		m_primitive.add("char");
		m_primitive.add("byte");
		m_primitive.add("short");
		m_primitive.add("int");
		m_primitive.add("long");
		m_primitive.add("double");
		m_primitive.add("float");
	}

	public static boolean isString(Class<?> clazz) {
		String typename = clazz.getTypeName();
		return typename.equals(String.class.getName());
	}
	
	public static boolean isInt(Class<?> clazz) {
		String typename = clazz.getTypeName();
		return typename.equals("int") || typename.equals(Integer.class.getName());
	}
	
	public static boolean isLong(Class<?> clazz) {
		String typename = clazz.getTypeName();
		return typename.equals("long") || typename.equals(Long.class.getName());
	}
	
	public static boolean isFloat(Class<?> clazz) {
		String typename = clazz.getTypeName();
		return typename.equals("float") || typename.equals(Float.class.getName());
	}
	
	public static boolean isDouble(Class<?> clazz) {
		String typename = clazz.getTypeName();
		return typename.equals("double") || typename.equals(Double.class.getName());
	}
	
	public static boolean isShort(Class<?> clazz) {
		String typename = clazz.getTypeName();
		return typename.equals("short") || typename.equals(Short.class.getName());
	}
	
	public static boolean isChar(Class<?> clazz) {
		String typename = clazz.getTypeName();
		return typename.equals("char") || typename.equals(Character.class.getName());
	}
	
	public static boolean isBool(Class<?> clazz) {
		String typename = clazz.getTypeName();
		return typename.equals("boolean") || typename.equals(Boolean.class.getName());
	}
	
	/**
	 * @param clazz class
	 * @return return true if the typename is primitive, otherwise return false
	 */
	public static boolean isPrimitive(Class<?> clazz) {
		return isPrimitive(clazz.getName());
	}
	
	/**
	 * @param typename typename
	 * @return return true if the classname is primitive, otherwise return false
	 */
	public static boolean isPrimitive(String typename) {
		return isNull(typename) ? false : m_primitive.contains(typename);
	}

	/*********************************************************************
	 *
	 * 
	 * Java collection
	 * 
	 * 
	 *********************************************************************/
	
	
	/**
	 * @param clazz class
	 * @return return true if the typename is starts with java.util.Map, otherwise
	 *         return false
	 */
	public static boolean isMap(Class<?> clazz) {
		return isNull(clazz) ? false : Map.class.isAssignableFrom(clazz);
	}
	

	/**
	 * @param typeName  typeName
	 * @return class or null
	 */
	private static Class<?> getRawType(String typeName) {
		try {
			int idx = typeName.indexOf("<");
			return Class.forName(idx == -1 ? typeName : typeName.substring(0, idx));
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	/**
	 * @param type typename
	 * @return true if the typename is java.util.Map with (String, String) style,
	 *         otherwise return false
	 */
	public static boolean isStringStringMap(Type type) {
			try {
				String typeName = type.getTypeName();
				return isMap(getRawType(typeName)) && 
						typeName.endsWith("<java.lang.String, java.lang.String>");
			} catch (Exception e) {
				m_logger.warning(type + " is not " + Map.class.getName() + "," + e);
				return false;
			} 
	}


	/**
	 * @param type typename
	 * @return return true if the typename is Map, but not java.util.Map with
	 *         (String, String) style, otherwise return false
	 */
	public static boolean isStringObjectMap(Type type) {
		return !isStringStringMap(type);
	}

	/**
	 * @param clz typename
	 * @return return true if the typename is starts with java.util.List, otherwise
	 *         return false
	 */
	public static boolean isList(Class<?> clz) {
		return isNull(clz) ? false : List.class.isAssignableFrom(clz);
	}
	
	/**
	 * @param type typename
	 * @return return true if the typename is java.util.List with String style,
	 *         otherwise return false
	 */
	public static boolean isStringList(Type type) {
		try {
			String typeName = type.getTypeName();
			return isList(getRawType(typeName)) && typeName.endsWith("<java.lang.String>");
		} catch (Exception e) {
			m_logger.warning(type + " is not " + List.class.getName() + "," + e);
			return false;
		}
	}

	/**
	 * @param type typename
	 * @return return true if the typename is java.util.List with String style,
	 *         otherwise return false
	 */
	public static boolean isObjectList(Type type) {
		return !isStringList(type);
	}

	/**
	 * @param clz typename
	 * @return return true if the typename is starts with java.util.List, but not
	 *         java.util.List with String style, otherwise return false
	 */
	public static boolean isObjectList(Class<?> clz) {
		return !isStringList(clz);
	}

	/**
	 * @param clz typename
	 * @return return true if the typename is starts with java.util.Set
	 */
	public static boolean isSet(Class<?> clz) {
		return isNull(clz) ? false : Set.class.isAssignableFrom(clz);
	}
	
	/**
	 * @param type typename
	 * @return return true if the typename is starts with java.util.List with String style, 
	 * otherwise return false
	 */
	public static boolean isStringSet(Type type) {
		try {
			String typeName = type.getTypeName();
			return isSet(getRawType(typeName)) && typeName.endsWith("<java.lang.String>");
		} catch (Exception e) {
			m_logger.warning(type + " is not " + Set.class.getName() + "," + e);
			return false;
		}
	}

	
	/**
	 * @param type typename
	 * @return return true if the typename is starts with java.util.List, but not
	 *         java.util.List with String style, otherwise return false
	 */
	public static boolean isObjectSet(Type type) {
		return !isStringSet(type);
	}

	/**
	 * @param type              Map
	 * @return classname
	 */
	public static String getValueClassForGenericMap(Type type) {
		if (!isStringObjectMap(type)) {
			m_logger.warning(type + " is not " + Map.class.getName());
			return null;
		}
		String typeName = type.getTypeName();
		int start = typeName.indexOf(",");
		int end = typeName.indexOf(">");
		return (start == -1) ? typeName : typeName.substring(start + 1, end).trim(); // <String, Object>的,后有一个空格
	}
	
	/**
	 * @param type              List
	 * @return classname
	 */
	public static String getClassForGenericList(Type type) {
		if (!isObjectList(type)) {
			m_logger.warning(type + " is not " + List.class.getName());
			return null;
		}
		String typeName = type.getTypeName();
		int start = typeName.indexOf("<");
		int end = typeName.indexOf(">");
		return (start == -1) ? typeName : typeName.substring(start, end).trim(); 
	}

	/**
	 * @param type              Set
	 * @return classname
	 */
	public static String getClassForGenericSet(Type type) {
		if (!isObjectSet(type)) {
			m_logger.warning(type + " is not " + Set.class.getName());
			return null;
		}
		String typeName = type.getTypeName();
		int start = typeName.indexOf("<");
		int end = typeName.indexOf(">");
		return (start == -1) ? typeName : typeName.substring(start, end).trim();
	}
	

	/**
	 * Check whether a string is null
	 * 
	 * @param str string
	 * @return return true of the string is null, otherwise return false
	 */
	public static boolean isNull(String str) {
		return (str == null || "".equals(str)) ? true : false;
	}

	/**
	 * Check whether a object is null
	 * 
	 * @param obj object
	 * @return return true of the object is null, otherwise return false
	 */
	public static boolean isNull(Object obj) {
		return (obj == null) ? true : false;
	}
}
