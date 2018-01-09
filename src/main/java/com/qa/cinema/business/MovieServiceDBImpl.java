package com.qa.cinema.business;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.cinema.persistence.Movie;
import com.qa.cinema.util.JSONUtil;

@Transactional(SUPPORTS)
public class MovieServiceDBImpl implements MovieService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllMovies() {
		Query query = manager.createQuery("Select m FROM Movie m");
		Collection<Movie> movies = (Collection<Movie>) query.getResultList();
		return util.getJSONForObject(movies);
	}

	@Override
	@Transactional(REQUIRED)
	public String createMovie(String movie) {
		Movie aMovie = util.getObjectForJSON(movie, Movie.class);
		manager.persist(aMovie);
		return "{\"message\": \"movie sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateMovie(Long id, String movie) {
		Movie updatedMovie = util.getObjectForJSON(movie, Movie.class);
		Movie movieInDB = findMovie(id);
		if (movieInDB != null) {
			movieInDB = updatedMovie;
			manager.merge(movieInDB);
		}
		return "{\"message\": \"movie sucessfully updated\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		Movie movieInDB = findMovie(id);
		if (movieInDB != null) {
			manager.remove(movieInDB);
		}
		return "{\"message\": \"movie sucessfully deleted\"}";
	}

	private Movie findMovie(Long id) {
		return manager.find(Movie.class, id);
	}

}
