/*
 * Created by Vraj Contractor
 */

package com.movieexplorer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Movie class models the data structure returned by the OMDb API.
 * The @JsonProperty annotations map JSON keys to Java fields.
 * Lombok's @Data generates boilerplate code like getters, setters, equals, hashcode, and toString.
 */
@Data
public class Movie {

    @JsonProperty("Title")
    private String title;          // Movie title

    @JsonProperty("Year")
    private String year;           // Release year

    @JsonProperty("Rated")
    private String rated;          // Movie rating (e.g., PG-13)

    @JsonProperty("Released")
    private String released;       // Release date

    @JsonProperty("Runtime")
    private String runtime;        // Duration of the movie

    @JsonProperty("Genre")
    private String genre;          // Genre(s) of the movie

    @JsonProperty("Director")
    private String director;       // Director's name(s)

    @JsonProperty("Writer")
    private String writer;         // Writer(s) of the movie

    @JsonProperty("Actors")
    private String actors;         // Main actors

    @JsonProperty("Plot")
    private String plot;           // Plot summary

    @JsonProperty("Language")
    private String language;       // Language(s)

    @JsonProperty("Country")
    private String country;        // Country of production

    @JsonProperty("Awards")
    private String awards;         // Awards won or nominated

    @JsonProperty("Poster")
    private String poster;         // URL to the poster image

    @JsonProperty("imdbRating")
    private String imdbRating;     // IMDb rating

    @JsonProperty("imdbID")
    private String imdbID;         // IMDb unique identifier
}
