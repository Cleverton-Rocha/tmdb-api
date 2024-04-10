package com.tmdb.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieDetailed(
    @JsonProperty("adult")
    boolean adult,

    @JsonProperty("backdrop_path")
    String backdropPath,

    @JsonProperty("belongs_to_collection")
    Collection belongsToCollection,

    @JsonProperty("budget")
    int budget,

    @JsonProperty("genres")
    List<Genre> genres,

    @JsonProperty("homepage")
    String homepage,

    @JsonProperty("id")
    int id,

    @JsonProperty("imdb_id")
    String imdbId,

    @JsonProperty("original_language")
    String originalLanguage,

    @JsonProperty("original_title")
    String originalTitle,

    @JsonProperty("overview")
    String overview,

    @JsonProperty("popularity")
    double popularity,

    @JsonProperty("poster_path")
    String posterPath,

    @JsonProperty("production_companies")
    List<ProductionCompany> productionCompanies,

    @JsonProperty("production_countries")
    List<ProductionCountry> productionCountries,

    @JsonProperty("release_date")
    String releaseDate,

    @JsonProperty("revenue")
    int revenue,

    @JsonProperty("runtime")
    int runtime,

    @JsonProperty("spoken_languages")
    List<SpokenLanguage> spokenLanguages,

    @JsonProperty("status")
    String status,

    @JsonProperty("tagline")
    String tagline,

    @JsonProperty("title")
    String title,

    @JsonProperty("video")
    boolean video,

    @JsonProperty("vote_average")
    double voteAverage,

    @JsonProperty("vote_count")
    int voteCount) {

  public static record Collection(
      @JsonProperty("id")
      int id,

      @JsonProperty("name")
      String name,

      @JsonProperty("poster_path")
      String posterPath,

      @JsonProperty("backdrop_path")
      String backdropPath) {
  }

  public static record Genre(
      @JsonProperty("id")
      int id,

      @JsonProperty("name")
      String name) {
  }

  public static record ProductionCompany(
      @JsonProperty("id")
      int id,

      @JsonProperty("logo_path")
      String logoPath,

      @JsonProperty("name")
      String name,

      @JsonProperty("origin_country")
      String originCountry) {
  }

  public static record ProductionCountry(
      @JsonProperty("iso_3166_1")
      String iso31661,

      @JsonProperty("name")
      String name) {
  }

  public static record SpokenLanguage(
      @JsonProperty("english_name")
      String englishName,

      @JsonProperty("iso_639_1")
      String iso6391,

      @JsonProperty("name")
      String name) {
  }
}