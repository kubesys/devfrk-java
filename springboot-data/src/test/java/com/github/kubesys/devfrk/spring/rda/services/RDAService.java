package com.github.kubesys.devfrk.spring.rda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.spring.data.RelationalDataAccess;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Jennifer Reif
 * @author Michael J. Simons
 */
@ServiceDefinition
public class RDAService extends AbstractHttpHandler  {

	@Autowired
	private RelationalDataAccess rda;

	public List<Object> list(String tableName) {
		return rda.list(tableName);
	}
}
