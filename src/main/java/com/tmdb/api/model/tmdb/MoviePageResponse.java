package com.tmdb.api.model.tmdb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MoviePageResponse(
    @JsonProperty("page")
    int page,

    @JsonProperty("results")
    List<Movie> results,

    @JsonProperty("total_pages")
    int totalPages,

    @JsonProperty("total_results")
    int totalResults) {

}