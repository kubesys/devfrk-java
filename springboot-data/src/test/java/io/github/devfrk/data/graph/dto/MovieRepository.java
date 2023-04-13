package io.github.devfrk.data.graph.dto;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.devfrk.data.graph.models.Movie;

/**
 * This repository is indirectly used in the {@code movies.spring.data.neo4j.api.MovieController} via a dedicated movie service.
 * It is not a public interface to indicate that access is either through the rest resources or through the service.
 *
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Michael J. Simons
 */
@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long> {

	@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Movie> findSearchResults(@Param("title") String title);
}
