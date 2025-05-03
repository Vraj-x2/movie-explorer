// MovieController.java
package com.movieexplorer.controller;

import com.movieexplorer.model.Movie;
import com.movieexplorer.service.MovieService;
import com.movieexplorer.service.GeminiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final GeminiService geminiService;
    
    // Supported filters for validation
    private static final Set<String> VALID_GENRES = Set.of(
        "action", "sci-fi", "drama", "comedy", "horror", 
        "romantic", "thriller", "adventure"
    );
    
    private static final Set<String> VALID_MOODS = Set.of(
        "uplifting", "dark", "romantic", "suspenseful", 
        "nostalgic", "emotional", "funny", "thought-provoking"
    );

    public MovieController(MovieService movieService, GeminiService geminiService) {
        this.movieService = movieService;
        this.geminiService = geminiService;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/search")
    public String searchMovie(@RequestParam String title, Model model) {
        try {
            Movie movie = movieService.getMovieByTitle(title);
            model.addAttribute("movie", movie);
        } catch (Exception e) {
            model.addAttribute("error", "Movie not found: " + e.getMessage());
        }
        return "index";
    }

    @GetMapping("/recommend")
    public String recommendMovies(
        @RequestParam String title,
        @RequestParam(required = false) List<String> genres,
        @RequestParam(required = false) List<String> moods,
        Model model) {
        
        try {
            Movie movie = movieService.getMovieByTitle(title);
            String recommendations = geminiService.getFilteredRecommendations(
                title, 
                validateFilters(genres, VALID_GENRES),
                validateFilters(moods, VALID_MOODS)
            );

            model.addAttribute("movie", movie);
            model.addAttribute("recommendationList", cleanRecommendations(recommendations));
        } catch (Exception e) {
            model.addAttribute("error", "Recommendation error: " + e.getMessage());
        }
        return "index";
    }
 // Add to MovieController.java
    @GetMapping("/ask")
    public String showAskPage(Model model) {
        model.addAttribute("showAskPage", true); // Flag for conditional rendering
        return "ask"; // Renders ask.html
    }

    @PostMapping("/ask")
    public String handleNaturalLanguageQuery(
        @RequestParam String query,
        @RequestParam(required = false) String title,
        Model model) {
        
        try {
            String response = geminiService.processNaturalLanguageQuery(query);
            if (title != null && !title.isEmpty()) {
                Movie movie = movieService.getMovieByTitle(title);
                model.addAttribute("movie", movie);
            }
            model.addAttribute("aiResponse", response);
        } catch (Exception e) {
            model.addAttribute("error", "AI query failed: " + e.getMessage());
        }
        return "ask";
    }

    @GetMapping("/compare")
    public String compareMovies(
        @RequestParam String movie1,
        @RequestParam String movie2,
        Model model) {
        
        try {
            Movie first = movieService.getMovieByTitle(movie1);
            Movie second = movieService.getMovieByTitle(movie2);
            String comparison = geminiService.compareMovies(first.getTitle(), second.getTitle());
            
            model.addAttribute("movie", first);
            model.addAttribute("comparisonMovie", second);
            model.addAttribute("comparisonText", comparison);
        } catch (Exception e) {
            model.addAttribute("error", "Comparison failed: " + e.getMessage());
        }
        return "index";
    }

    private List<String> validateFilters(List<String> inputs, Set<String> validValues) {
        if (inputs == null) return Collections.emptyList();
        return inputs.stream()
            .map(String::toLowerCase)
            .filter(validValues::contains)
            .toList();
    }

    private List<String> cleanRecommendations(String text) {
        return Arrays.stream(text.split("\n"))
            .map(line -> line.replaceAll("^\\d+[\\.\\)]\\s*", "").trim())
            .filter(line -> !line.isEmpty())
            .toList();
    }
}