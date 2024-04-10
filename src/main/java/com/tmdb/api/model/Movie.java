package com.tmdb.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Movie(
    @JsonProperty("adult")
    boolean adult,

    @JsonProperty("backdrop_path")
    String backdropPath,

    @JsonProperty("genre_ids")
    List<Integer> genreIds,

    @JsonProperty("id")
    int id,

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

    @JsonProperty("release_date")
    String releaseDate,

    @JsonProperty("title")
    String title,

    @JsonProperty("video")
    boolean video,

    @JsonProperty("vote_average")
    double voteAverage,

    @JsonProperty("vote_count")
    int voteCount
) {

}