package io.github.devfrk.data.search.daos;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import io.github.devfrk.data.search.models.Product;

/**
 * This repository is indirectly used in the {@code movies.spring.data.neo4j.api.MovieController} via a dedicated movie service.
 * It is not a public interface to indicate that access is either through the rest resources or through the service.
 *
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Michael J. Simons
 */
@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {

	List<Product> findByName(String name);
}
