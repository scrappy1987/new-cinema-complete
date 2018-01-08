package com.qa.cinema.business;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class MovieServiceMapImpl implements MovieService {

	@Override
	public String getAllMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createMovie(String movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateMovie(Long id, String movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMovie(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
