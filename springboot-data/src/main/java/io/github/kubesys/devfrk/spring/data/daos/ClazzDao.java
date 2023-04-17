/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.data.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.kubesys.devfrk.spring.data.models.Clazz;

@Repository
public interface ClazzDao extends JpaRepository<Clazz, Long> {

	
}
