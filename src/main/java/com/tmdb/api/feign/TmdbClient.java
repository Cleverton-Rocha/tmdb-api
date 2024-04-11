package com.tmdb.api.feign;

import com.tmdb.api.model.tmdb.MovieDetailed;
import com.tmdb.api.model.tmdb.MoviePageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tmdb-api", url = "https://api.themoviedb.org/3")
public interface TmdbClient {

  @GetMapping("/movie/top_rated")
  MoviePageResponse getTopRatedMovies(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "pt-BR") String language);

  @GetMapping("/search/movie?query={movieName}")
  MoviePageResponse getMovieByName(
      @PathVariable String movieName,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "pt-BR") String language);

  @GetMapping("/discover/movie?with_genres={genreId}")
  MoviePageResponse getMovieByGenre(
      @PathVariable Integer genreId,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "pt-BR") String language);

  @GetMapping("/movie/{movieId}")
  MovieDetailed getMovieDetails(
      @PathVariable Integer movieId,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "pt-BR") String language);
}
