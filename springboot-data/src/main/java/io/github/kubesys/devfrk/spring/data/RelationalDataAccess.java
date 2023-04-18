/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

/**
 * @author wuheng@iscas.ac.cn
 * @since  2.2.0
 * 
 */
@Component
public class RelationalDataAccess  {

	@Autowired
	private EntityManager entityManager;

	public boolean create(Object object) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(object);
		transaction.commit();
		return true;
	}
	
	public boolean delete(Object object) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(object);
		transaction.commit();
		return true;
	}

	public boolean update(Object object) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(object);
		transaction.commit();
		return true;
	}

	public Object get(String tableName) {
		Query query = entityManager.createNativeQuery("SELECT * FROM " + tableName);
		return query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> list(String tableName) {
		Query query = entityManager.createNativeQuery("SELECT * FROM " + tableName);
		return query.getResultList();
	}

}
