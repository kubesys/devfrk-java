package io.github.webfrk.orm.examples;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserOrm extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {

	<S extends User> S save(@Param("user") User user);

}