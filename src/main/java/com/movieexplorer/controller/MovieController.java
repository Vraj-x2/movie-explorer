package com.movieexplorer.controller;

import com.movieexplorer.model.Movie;
import com.movieexplorer.service.MovieService;
import com.movieexplorer.service.GeminiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final GeminiService geminiService;

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
    public String recommendMovies(@RequestParam String title, Model model) {
        try {
            Movie movie = movieService.getMovieByTitle(title);
            String recommendationsText = geminiService.getRecommendations(title);

            // Split and clean recommendations
            List<String> cleanedTitles = Arrays.stream(recommendationsText.split("\n"))
                .map(line -> line.replaceAll("^\\d+\\.\\s*", "").trim()) // Remove leading "1. ", "2. " etc.
                .filter(line -> !line.isEmpty())
                .toList();

            model.addAttribute("movie", movie);
            model.addAttribute("recommendationList", cleanedTitles);
        } catch (Exception e) {
            model.addAttribute("error", "Error getting recommendations: " + e.getMessage());
        }
        return "index";
    }

    
    
}
