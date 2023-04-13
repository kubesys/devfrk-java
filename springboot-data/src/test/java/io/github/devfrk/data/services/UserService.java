/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.devfrk.data.services;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.devfrk.data.daos.UserDao;
import io.github.devfrk.data.models.User;
import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;

@ServiceDefinition
public class UserService extends AbstractHttpHandler  {

	@Autowired
	private UserDao userDao;
	
	public User getUserById(Long id) {
		return userDao.getReferenceById(id);
	}
	
	public long userCount() {
		return userDao.count();
	}
}
