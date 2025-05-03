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

@Service
public class GeminiService {

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    
    @Value("${gemini.api.key}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getFilteredRecommendations(String movieTitle, List<String> genres, List<String> moods) {
        String prompt = buildRecommendationPrompt(movieTitle, genres, moods);
        String rawResponse = processGeminiRequest(prompt);
        return extractCleanTitles(rawResponse);
    }

    public String processNaturalLanguageQuery(String query) {
        String prompt = "Act as a movie expert. Answer concisely: " + query;
        return processGeminiRequest(prompt);
    }

    public String compareMovies(String movie1, String movie2) {
        String prompt = "Compare these two movies in a detailed pros/cons format: " + 
                      movie1 + " and " + movie2 + ". Focus on plot, themes, and filmmaking style.";
        return processGeminiRequest(prompt);
    }

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

    private String extractCleanTitles(String rawResponse) {
        List<String> titles = new ArrayList<>();
        Pattern pattern = Pattern.compile("^\\d+\\.\\s+(.+)$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(rawResponse);
        
        while (matcher.find()) {
            titles.add(matcher.group(1).trim());
        }
        
        return titles.isEmpty() ? rawResponse : String.join("\n", titles);
    }

    private String processGeminiRequest(String promptText) {
        try {
            String sanitizedPrompt = promptText.replace("\"", "'");
            String jsonBody = String.format(
                "{ \"contents\": [{ \"parts\": [{ \"text\": \"%s\" }] }] }",
                sanitizedPrompt
            );

            Request request = new Request.Builder()
                .url(GEMINI_API_URL + "?key=" + apiKey)
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    return "API Error: " + response.code();
                }

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