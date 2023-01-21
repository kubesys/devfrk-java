/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.orm;


import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 * 
 */

@NoRepositoryBean
public interface OrmRepository<T extends OrmEntity, ID> 
							extends CrudRepository<T, ID>, 
							PagingAndSortingRepository<T, ID> {

}