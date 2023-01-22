/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.webfrk.orm.current.examples;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * This code is generated by httpfrk-springboot-orm project
 *
 */
//@Repository
public interface UserOrm extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {

}