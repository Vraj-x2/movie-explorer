/*
 * Created by Vraj Contractor
 */

package com.movieexplorer.service;

import com.movieexplorer.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * MovieService interacts with the OMDb API
 * to fetch detailed movie information by title.
 */
@Service
public class MovieService {

    // OMDb API key injected from application.properties (must be set by user)
    @Value("${omdb.api.key}")
    private String apiKey;

    // RestTemplate instance to perform HTTP requests
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Fetch detailed movie information by its title from OMDb API.
     * @param title The movie title to search for.
     * @return Movie object with detailed info.
     * @throws RuntimeException if movie is not found or API returns no data.
     */
    public Movie getMovieByTitle(String title) {
        String url = String.format(
            "https://www.omdbapi.com/?apikey=%s&t=%s&plot=full",
            apiKey,
            title.replace(" ", "+")  // Replace spaces with + for URL encoding
        );
        
        Movie movie = restTemplate.getForObject(url, Movie.class);
        
        if (movie == null || movie.getImdbID() == null) {
            throw new RuntimeException("Movie not found: " + title);
        }
        return movie;
    }
}
