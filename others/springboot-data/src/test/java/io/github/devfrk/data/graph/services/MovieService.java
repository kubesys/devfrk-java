package io.github.devfrk.data.graph.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.devfrk.data.graph.daos.MovieRepository;
import io.github.devfrk.data.graph.models.Movie;
import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Jennifer Reif
 * @author Michael J. Simons
 */
@ServiceDefinition
public class MovieService extends AbstractHttpHandler {

	@Autowired
	private MovieRepository movieRepository;

	public List<Movie> getByTitle(@PathVariable("title") String title) {
		List<Movie> findSearchResults = movieRepository.findSearchResults(title);
		return findSearchResults;
	}

}
