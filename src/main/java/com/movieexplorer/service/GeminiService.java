/*
 * Created by Vraj Contractor
 */

package com.movieexplorer.service;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GeminiService interacts with the Gemini API for
 * AI-powered movie recommendations, comparisons,
 * and natural language queries.
 */
@Service
public class GeminiService {

    // Gemini API endpoint URL for content generation
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    
    // API key injected from application.properties (must be set by user)
    @Value("${gemini.api.key}")
    private String apiKey;

    // HTTP client for making API requests
    private final OkHttpClient client = new OkHttpClient();

    // JSON object mapper to parse Gemini API responses
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Get movie recommendations filtered by title, genres, and moods.
     * @param movieTitle The base movie title for recommendations.
     * @param genres List of genres to filter recommendations.
     * @param moods List of moods to filter recommendations.
     * @return String containing recommended movie titles.
     */
    public String getFilteredRecommendations(String movieTitle, List<String> genres, List<String> moods) {
        String prompt = buildRecommendationPrompt(movieTitle, genres, moods);
        String rawResponse = processGeminiRequest(prompt);
        return extractCleanTitles(rawResponse);
    }

    /**
     * Process a general natural language query related to movies.
     * @param query User's natural language question.
     * @return AI-generated answer as string.
     */
    public String processNaturalLanguageQuery(String query) {
        String prompt = "Act as a movie expert. Answer concisely: " + query;
        return processGeminiRequest(prompt);
    }

    /**
     * Compare two movies with detailed pros and cons.
     * @param movie1 First movie title.
     * @param movie2 Second movie title.
     * @return Comparison text from AI.
     */
    public String compareMovies(String movie1, String movie2) {
        String prompt = "Compare these two movies in a detailed pros/cons format: " + 
                      movie1 + " and " + movie2 + ". Focus on plot, themes, and filmmaking style.";
        return processGeminiRequest(prompt);
    }

    /**
     * Helper to build the recommendation prompt string for Gemini API.
     */
    private String buildRecommendationPrompt(String title, List<String> genres, List<String> moods) {
        StringBuilder prompt = new StringBuilder("Suggest exactly 3 movies similar to '")
            .append(title)
            .append("'");
            
        if (!genres.isEmpty()) {
            prompt.append(" in genres: ").append(String.join(", ", genres));
        }
        if (!moods.isEmpty()) {
            prompt.append(" with moods: ").append(String.join(", ", moods));
        }
        
        prompt.append(". Respond ONLY with a numbered list of movie titles (1. Title) without any explanations, descriptions, formatting, or additional text.");
        return prompt.toString();
    }

    /**
     * Extract clean movie titles from raw Gemini API response.
     */
    private String extractCleanTitles(String rawResponse) {
        List<String> titles = new ArrayList<>();
        Pattern pattern = Pattern.compile("^\\d+\\.\\s+(.+)$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(rawResponse);
        
        while (matcher.find()) {
            titles.add(matcher.group(1).trim());
        }
        
        return titles.isEmpty() ? rawResponse : String.join("\n", titles);
    }

    /**
     * Send the prompt to Gemini API and get the response text.
     */
    private String processGeminiRequest(String promptText) {
        try {
            // Replace double quotes with single quotes to avoid JSON issues
            String sanitizedPrompt = promptText.replace("\"", "'");
            
            // Prepare JSON request body
            String jsonBody = String.format(
                "{ \"contents\": [{ \"parts\": [{ \"text\": \"%s\" }] }] }",
                sanitizedPrompt
            );

            // Build HTTP POST request with API key as query param
            Request request = new Request.Builder()
                .url(GEMINI_API_URL + "?key=" + apiKey)
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();

            // Execute the request and handle response
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    return "API Error: " + response.code();
                }

                // Parse JSON response to extract generated text
                JsonNode root = objectMapper.readTree(response.body().string());
                return root.path("candidates").get(0)
                          .path("content").path("parts").get(0)
                          .path("text").asText("No response found");
            }
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
