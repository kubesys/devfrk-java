package io.github.webfrk.orm.current.examples;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;
import jakarta.validation.Valid;

@ServiceDefinition
public class UserService extends AbstractHttpHandler {

	@Autowired
	private UserOrm orm;
	
	public void createUser(@Valid User user) {
		orm.save(user);
	}

}