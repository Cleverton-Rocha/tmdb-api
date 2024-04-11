package com.tmdb.api.controller;

import com.tmdb.api.model.MovieDetailed;
import com.tmdb.api.model.MoviePageResponse;
import com.tmdb.api.service.TmdbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TmdbController {

  private final TmdbService tmdbService;

  public TmdbController(TmdbService tmdbService) {
    this.tmdbService = tmdbService;
  }

  @GetMapping("/movies/top-rated")
  public ResponseEntity<MoviePageResponse> getTopRatedMovies(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "pt-BR") String language) {
    return ResponseEntity.ok(tmdbService.getTopRatedMovies(page, language));
  }

  @GetMapping("/movie/{movieName}")
  public ResponseEntity<MoviePageResponse> getMovieByName(
      @PathVariable String movieName,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "pt-BR") String language) {
    return ResponseEntity.ok(tmdbService.getMovieByName(movieName, page, language));
  }

  @GetMapping("/genre/{genreName}")
  public ResponseEntity<MoviePageResponse> getMovieByGenre(
      @PathVariable String genreName,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "pt-BR") String language) {
    return ResponseEntity.ok(tmdbService.getMoviesByGenre(genreName, page, language));
  }

  @GetMapping("/movie/{movieId}/details")
  public ResponseEntity<MovieDetailed> getMovieDetails(
      @PathVariable Integer movieId,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "pt-BR") String language) {
    return ResponseEntity.ok(tmdbService.getMovieDetails(movieId, page, language));
  }

}
