package com.movieexplorer.service;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class GeminiService {

    // Use the endpoint that works for your account
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    
    @Value("${gemini.api.key}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getRecommendations(String movieTitle) {
        try {
            // Build JSON payload using proper escaping
            String jsonBody = String.format(
                "{ \"contents\": [{ \"parts\": [{ \"text\": \"Suggest 3 movies similar to '%s'. Only list titles without years.\" }] }] }",
                movieTitle.replace("\"", "") // Remove quotes to prevent JSON issues
            );

            RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json"));
            
            Request request = new Request.Builder()
                .url(GEMINI_API_URL + "?key=" + apiKey)
                .post(body)
                .build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                
                // Debug output
                System.out.println("Request URL: " + request.url());
                System.out.println("Request Body: " + jsonBody);
                System.out.println("Response Code: " + response.code());
                System.out.println("Response Body: " + responseBody);

                if (!response.isSuccessful()) {
                    return "API Error " + response.code() + ": " + 
                           objectMapper.readTree(responseBody).path("error").path("message").asText();
                }

                JsonNode root = objectMapper.readTree(responseBody);
                return root.path("candidates").get(0)
                          .path("content").path("parts").get(0)
                          .path("text").asText("No recommendations found");
            }
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}