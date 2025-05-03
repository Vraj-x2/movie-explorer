// MovieService.java
package com.movieexplorer.service;

import com.movieexplorer.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    @Value("${omdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Movie getMovieByTitle(String title) {
        String url = String.format(
            "https://www.omdbapi.com/?apikey=%s&t=%s&plot=full",
            apiKey,
            title.replace(" ", "+")
        );
        
        Movie movie = restTemplate.getForObject(url, Movie.class);
        
        if (movie == null || movie.getImdbID() == null) {
            throw new RuntimeException("Movie not found: " + title);
        }
        return movie;
    }
}