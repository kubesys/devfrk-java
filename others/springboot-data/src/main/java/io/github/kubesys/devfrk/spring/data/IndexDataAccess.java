/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.data;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.2.0
 * 
 */
//@Component
public class IndexDataAccess  {

	@Autowired
	private QueryBuilder queryBuilder;
	    

	public boolean create(Object object) {
		return true;
	}
	
	public boolean delete(Object object) {
		return true;
	}

	public boolean update(Object object) {
		return true;
	}

	public Object get(String tableName) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> list(String tableName) {
		return (List<Object>) QueryBuilders.matchQuery(null, null);
	}

}
