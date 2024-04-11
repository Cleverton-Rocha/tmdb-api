package com.tmdb.api.service;

import com.tmdb.api.exception.NotFoundException;
import com.tmdb.api.feign.TmdbClient;
import com.tmdb.api.model.tmdb.MovieDetailed;
import com.tmdb.api.model.tmdb.MoviePageResponse;
import com.tmdb.api.util.GenreMapping;
import org.springframework.stereotype.Service;

@Service
public class TmdbService {

  private final TmdbClient tmdbClient;

  public TmdbService(TmdbClient tmdbClient) {
    this.tmdbClient = tmdbClient;
  }

  public MoviePageResponse getTopRatedMovies(Integer page, String language) {
    return tmdbClient.getTopRatedMovies(page, language);
  }

  public MoviePageResponse getMovieByName(String movieName, Integer page, String language) {

    MoviePageResponse moviePageResponse = tmdbClient.getMovieByName(movieName, page, language);

    if (moviePageResponse.results().isEmpty()) {
      throw new NotFoundException("Movie not found.");
    }
    System.out.println(moviePageResponse.results());
    return moviePageResponse;
  }

  public MoviePageResponse getMoviesByGenre(String genreName, Integer page, String language) {
    Integer genreId = GenreMapping.getGenreId(genreName);

    if (genreId == null) {
      throw new NotFoundException("Genre not found.");
    }

    return tmdbClient.getMovieByGenre(genreId, page, language);
  }

  public MovieDetailed getMovieDetails(Integer movieId, Integer page, String language) {

    return tmdbClient.getMovieDetails(movieId, page, language);
  }
}
