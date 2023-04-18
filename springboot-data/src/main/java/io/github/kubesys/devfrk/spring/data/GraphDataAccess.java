/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.data;

import java.util.List;

import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.2.0
 * 
 */
@Component
public class GraphDataAccess  {

	private Neo4jTemplate neo4jTemplate;

	public boolean create(Object object) {
		neo4jTemplate.save(object);
		return true;
	}
	
	public boolean delete(Object object) {
		return true;
	}

	public boolean update(Object object) {
		return true;
	}

	public Object get(String tableName) {
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> list(Class<?> clazz, int age) {
//		String query = "MATCH (p:Person) WHERE p.age = $age RETURN p";
//        ExecutableQuery executableQuery = neo4jTemplate.toExecutableQuery(query, clazz);
//        return executableQuery.bind(age).fetch().all();
		return null;
	}

}
