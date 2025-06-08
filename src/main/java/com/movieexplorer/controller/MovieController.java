/*
 * Created by Vraj Contractor
 */

package com.movieexplorer.controller;

import com.movieexplorer.model.Feedback;
import com.movieexplorer.model.Movie;
import com.movieexplorer.repository.FeedbackRepository;
import com.movieexplorer.service.GeminiService;
import com.movieexplorer.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller class to handle all web requests related to movies and feedback.
 * This includes searching for movies, getting recommendations, comparing movies,
 * processing AI-based natural language queries, and handling user feedback.
 */
@Controller
public class MovieController {

    // Service to get movie data from OMDb or database
    private final MovieService movieService;

    // Service to interact with Gemini AI for recommendations and queries
    private final GeminiService geminiService;

    // Repository to save and retrieve user feedback from the database
    private final FeedbackRepository feedbackRepo;

    // Allowed genres for filtering movie recommendations
    private static final Set<String> VALID_GENRES = Set.of(
        "action", "sci-fi", "drama", "comedy", "horror",
        "romantic", "thriller", "adventure"
    );

    // Allowed moods for filtering movie recommendations
    private static final Set<String> VALID_MOODS = Set.of(
        "uplifting", "dark", "romantic", "suspenseful",
        "nostalgic", "emotional", "funny", "thought-provoking"
    );

    /**
     * Constructor to initialize services and repository via dependency injection.
     */
    public MovieController(MovieService movieService, GeminiService geminiService, FeedbackRepository feedbackRepo) {
        this.movieService = movieService;
        this.geminiService = geminiService;
        this.feedbackRepo = feedbackRepo;
    }

    /**
     * Displays the home page of the application.
     */
    @GetMapping("/")
    public String showHomePage() {
        return "index"; // Return Thymeleaf template named 'index.html'
    }

    /**
     * Handles movie search by title.
     * Adds the found movie or error message to the model.
     */
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

    /**
     * Provides movie recommendations based on title and optional genre/mood filters.
     * Validates filters and sends request to Gemini service.
     */
    @GetMapping("/recommend")
    public String recommendMovies(
        @RequestParam String title,
        @RequestParam(required = false) List<String> genres,
        @RequestParam(required = false) List<String> moods,
        Model model) {

        try {
            Movie movie = movieService.getMovieByTitle(title);

            // Validate filters to allow only predefined genres and moods
            List<String> validGenres = validateFilters(genres, VALID_GENRES);
            List<String> validMoods = validateFilters(moods, VALID_MOODS);

            // Get recommendations from Gemini AI service
            String recommendations = geminiService.getFilteredRecommendations(title, validGenres, validMoods);

            model.addAttribute("movie", movie);
            model.addAttribute("recommendationList", cleanRecommendations(recommendations));
        } catch (Exception e) {
            model.addAttribute("error", "Recommendation error: " + e.getMessage());
        }
        return "index";
    }

    /**
     * Shows the "Ask" page where users can submit natural language queries.
     */
    @GetMapping("/ask")
    public String showAskPage(Model model) {
        model.addAttribute("showAskPage", true);
        return "ask"; // Returns 'ask.html' view
    }

    /**
     * Handles POST requests for natural language queries processed by Gemini AI.
     * If a movie title is provided, fetches and adds movie data to the model.
     */
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

    /**
     * Compares two movies and returns a comparison summary from Gemini AI.
     */
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

    // === Feedback related endpoints ===

    /**
     * Shows the feedback page listing all submitted feedback from users.
     */
    @GetMapping("/feedback")
    public String showFeedbackPage(Model model) {
        List<Feedback> allFeedback = feedbackRepo.findAll();
        model.addAttribute("feedbacks", allFeedback);
        return "feedback";  // Renders 'feedback.html' view
    }

    /**
     * Handles feedback form submission from users.
     * Validates inputs and saves feedback to the database.
     */
    @PostMapping("/feedback")
    public String submitFeedback(@RequestParam String userName,
                                 @RequestParam String userEmail,
                                 @RequestParam String message,
                                 Model model) {
        if (userName.isBlank() || userEmail.isBlank() || message.isBlank()) {
            model.addAttribute("error", "All fields are required!");
            model.addAttribute("feedbacks", feedbackRepo.findAll());
            return "feedback";
        }

        Feedback feedback = new Feedback();
        feedback.setUserName(userName);
        feedback.setUserEmail(userEmail);
        feedback.setMessage(message);

        feedbackRepo.save(feedback);

        model.addAttribute("success", "Thank you for your feedback!");
        model.addAttribute("feedbacks", feedbackRepo.findAll());
        return "feedback";
    }

    /**
     * REST API controller to handle feedback CRUD operations.
     * This is separate from MVC controllers and handles JSON requests/responses.
     */
    @RestController
    @RequestMapping("/api/feedback")
    public static class FeedbackApiController {

        private final FeedbackRepository feedbackRepository;

        public FeedbackApiController(FeedbackRepository feedbackRepository) {
            this.feedbackRepository = feedbackRepository;
        }

        /**
         * Accepts JSON feedback and stores it, returning the new feedback ID.
         */
        @PostMapping
        public Long submitFeedback(@RequestBody Feedback feedback) {
            return feedbackRepository.save(feedback).getId();
        }

        /**
         * Retrieves a single feedback by its ID.
         * Throws runtime exception if not found.
         */
        @GetMapping("/{id}")
        public Feedback getFeedback(@PathVariable Long id) {
            return feedbackRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Feedback not found"));
        }

        /**
         * Returns a list of all feedback entries.
         */
        @GetMapping
        public List<Feedback> getAllFeedback() {
            return feedbackRepository.findAll();
        }
        
    }

    // === Helper methods ===

    /**
     * Validates a list of filters by converting to lowercase and only keeping allowed values.
     * Returns an empty list if input is null.
     */
    private List<String> validateFilters(List<String> inputs, Set<String> validValues) {
        if (inputs == null) return Collections.emptyList();
        return inputs.stream()
            .map(String::toLowerCase)
            .filter(validValues::contains)
            .toList();
    }

    /**
     * Cleans up recommendation text by splitting lines and removing numbering.
     * Filters out empty lines.
     */
    private List<String> cleanRecommendations(String text) {
        return Arrays.stream(text.split("\n"))
            .map(line -> line.replaceAll("^\\d+[\\.\\)]\\s*", "").trim())
            .filter(line -> !line.isEmpty())
            .toList();
    }
}
