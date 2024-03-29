/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.devfrk.data.jdbc.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.devfrk.data.jdbc.models.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	
}
