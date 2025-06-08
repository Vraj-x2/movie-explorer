[1mdiff --git a/README.md b/README.md[m
[1mnew file mode 100644[m
[1mindex 0000000..01bea5b[m
[1m--- /dev/null[m
[1m+++ b/README.md[m
[36m@@ -0,0 +1,83 @@[m
[32m+[m[32m# 🎬 Movie Explorer[m
[32m+[m
[32m+[m[32mMovie Explorer is a Spring Boot web application that allows users to search for movie details using the OMDb API and receive AI-based movie recommendations using Google's Gemini AI.[m
[32m+[m
[32m+[m[32m## 🌟 Features[m
[32m+[m
[32m+[m[32m- 🔎 Search any movie title using the OMDb API[m
[32m+[m[32m- 🧠 Get AI-powered movie recommendations based on the title you searched[m
[32m+[m[32m- 📸 View movie details like title, year, director, plot, rating, and poster[m
[32m+[m[32m- 💡 Beautiful, modern UI built with Thymeleaf and custom CSS[m
[32m+[m
[32m+[m[32m## 🚀 Demo[m
[32m+[m
[32m+[m[32mYou can run the app locally with the instructions below.[m
[32m+[m
[32m+[m[32m## 🛠 Tech Stack[m
[32m+[m
[32m+[m[32m- Spring Boot 3.4.5[m
[32m+[m[32m- Thymeleaf[m
[32m+[m[32m- OMDb API[m
[32m+[m[32m- Gemini AI (Google Generative Language API)[m
[32m+[m[32m- OkHttp + Jackson for HTTP and JSON[m
[32m+[m[32m- HTML/CSS for the frontend[m
[32m+[m
[32m+[m[32m## 📂 File Structure[m
[32m+[m
[32m+[m[32m```[m
[32m+[m[32msrc/[m
[32m+[m[32m├── main/[m
[32m+[m[32m│   ├── java/com/movieexplorer/[m
[32m+[m[32m│   │   ├── controller/[m
[32m+[m[32m│   │   ├── model/[m
[32m+[m[32m│   │   ├── service/[m
[32m+[m[32m│   ├── resources/[m
[32m+[m[32m│   │   ├── templates/index.html[m
[32m+[m[32m│   │   ├── static/css/style.css[m
[32m+[m[32m│   │   └── application.properties.example[m
[32m+[m[32m```[m
[32m+[m
[32m+[m[32m## 🧪 Setup Instructions[m
[32m+[m
[32m+[m[32m1. **Clone the Repository**[m
[32m+[m[32m   ```bash[m
[32m+[m[32m   git clone https://github.com/Vraj-x2/movie-explorer.git[m
[32m+[m[32m   cd movie-explorer[m
[32m+[m[32m   ```[m
[32m+[m
[32m+[m[32m2. **Configure Your API Keys**[m
[32m+[m[32m   - Copy the provided demo properties file:[m
[32m+[m[32m     ```bash[m
[32m+[m[32m     cp src/main/resources/application.properties.example src/main/resources/application.properties[m
[32m+[m[32m     ```[m
[32m+[m[32m   - Replace `YOUR_OMDB_API_KEY_HERE` and `YOUR_GEMINI_API_KEY_HERE` with your actual keys.[m
[32m+[m
[32m+[m[32m3. **Run the Application**[m
[32m+[m[32m   ```bash[m
[32m+[m[32m   ./mvnw spring-boot:run[m
[32m+[m[32m   ```[m
[32m+[m
[32m+[m[32m4. **Visit the App**[m
[32m+[m[32m   - Open your browser at: [http://localhost:8080](http://localhost:8080)[m
[32m+[m
[32m+[m[32m## 🔐 API Keys[m
[32m+[m
[32m+[m[32m- [OMDb API Key](https://www.omdbapi.com/apikey.aspx)[m
[32m+[m[32m- [Google Gemini API Key](https://ai.google.dev/)[m
[32m+[m
[32m+[m[32m> ⚠️ Never commit your real keys to GitHub. Use `.gitignore` to exclude `application.properties` and track only the `.example` file.[m
[32m+[m
[32m+[m[32m## 🧠 Gemini Prompt Example[m
[32m+[m
[32m+[m[32m```[m
[32m+[m[32mSuggest 3 movies similar to 'Inception'. Only list titles without years.[m
[32m+[m[32m```[m
[32m+[m
[32m+[m[32m## 👤 Author[m
[32m+[m
[32m+[m[32m**Vraj Contractor**[m[41m  [m
[32m+[m[32mGitHub: [@Vraj-x2](https://github.com/Vraj-x2)[m
[32m+[m
[32m+[m[32m---[m
[32m+[m
[32m+[m[32m📬 Pull requests and contributions are welcome![m
[1mdiff --git a/src/main/java/com/movieexplorer/controller/MovieController.java b/src/main/java/com/movieexplorer/controller/MovieController.java[m
[1mindex cead8f9..cea3346 100644[m
[1m--- a/src/main/java/com/movieexplorer/controller/MovieController.java[m
[1m+++ b/src/main/java/com/movieexplorer/controller/MovieController.java[m
[36m@@ -1,4 +1,3 @@[m
[31m-// MovieController.java[m
 package com.movieexplorer.controller;[m
 [m
 import com.movieexplorer.model.Movie;[m
[36m@@ -7,24 +6,16 @@[m [mimport com.movieexplorer.service.GeminiService;[m
 import org.springframework.stereotype.Controller;[m
 import org.springframework.ui.Model;[m
 import org.springframework.web.bind.annotation.*;[m
[31m-import java.util.*;[m
[32m+[m
[32m+[m[32mimport java.util.Arrays;[m
[32m+[m[32mimport java.util.List;[m
[32m+[m[32mimport java.util.stream.Collectors;[m
 [m
 @Controller[m
 public class MovieController {[m
 [m
     private final MovieService movieService;[m
     private final GeminiService geminiService;[m
[31m-    [m
[31m-    // Supported filters for validation[m
[31m-    private static final Set<String> VALID_GENRES = Set.of([m
[31m-        "action", "sci-fi", "drama", "comedy", "horror", [m
[31m-        "romantic", "thriller", "adventure"[m
[31m-    );[m
[31m-    [m
[31m-    private static final Set<String> VALID_MOODS = Set.of([m
[31m-        "uplifting", "dark", "romantic", "suspenseful", [m
[31m-        "nostalgic", "emotional", "funny", "thought-provoking"[m
[31m-    );[m
 [m
     public MovieController(MovieService movieService, GeminiService geminiService) {[m
         this.movieService = movieService;[m
[36m@@ -48,85 +39,25 @@[m [mpublic class MovieController {[m
     }[m
 [m
     @GetMapping("/recommend")[m
[31m-    public String recommendMovies([m
[31m-        @RequestParam String title,[m
[31m-        @RequestParam(required = false) List<String> genres,[m
[31m-        @RequestParam(required = false) List<String> moods,[m
[31m-        Model model) {[m
[31m-        [m
[32m+[m[32m    public String recommendMovies(@RequestParam String title, Model model) {[m
         try {[m
             Movie movie = movieService.getMovieByTitle(title);[m
[31m-            String recommendations = geminiService.getFilteredRecommendations([m
[31m-                title, [m
[31m-                validateFilters(genres, VALID_GENRES),[m
[31m-                validateFilters(moods, VALID_MOODS)[m
[31m-            );[m
[32m+[m[32m            String recommendationsText = geminiService.getRecommendations(title);[m
 [m
[31m-            model.addAttribute("movie", movie);[m
[31m-            model.addAttribute("recommendationList", cleanRecommendations(recommendations));[m
[31m-        } catch (Exception e) {[m
[31m-            model.addAttribute("error", "Recommendation error: " + e.getMessage());[m
[31m-        }[m
[31m-        return "index";[m
[31m-    }[m
[31m- // Add to MovieController.java[m
[31m-    @GetMapping("/ask")[m
[31m-    public String showAskPage(Model model) {[m
[31m-        model.addAttribute("showAskPage", true); // Flag for conditional rendering[m
[31m-        return "ask"; // Renders ask.html[m
[31m-    }[m
[32m+[m[32m            // Split and clean recommendations[m
[32m+[m[32m            List<String> cleanedTitles = Arrays.stream(recommendationsText.split("\n"))[m
[32m+[m[32m                .map(line -> line.replaceAll("^\\d+\\.\\s*", "").trim()) // Remove leading "1. ", "2. " etc.[m
[32m+[m[32m                .filter(line -> !line.isEmpty())[m
[32m+[m[32m                .toList();[m
 [m
[31m-    @PostMapping("/ask")[m
[31m-    public String handleNaturalLanguageQuery([m
[31m-        @RequestParam String query,[m
[31m-        @RequestParam(required = false) String title,[m
[31m-        Model model) {[m
[31m-        [m
[31m-        try {[m
[31m-            String response = geminiService.processNaturalLanguageQuery(query);[m
[31m-            if (title != null && !title.isEmpty()) {[m
[31m-                Movie movie = movieService.getMovieByTitle(title);[m
[31m-                model.addAttribute("movie", movie);[m
[31m-            }[m
[31m-            model.addAttribute("aiResponse", response);[m
[31m-        } catch (Exception e) {[m
[31m-            model.addAttribute("error", "AI query failed: " + e.getMessage());[m
[31m-        }[m
[31m-        return "ask";[m
[31m-    }[m
[31m-[m
[31m-    @GetMapping("/compare")[m
[31m-    public String compareMovies([m
[31m-        @RequestParam String movie1,[m
[31m-        @RequestParam String movie2,[m
[31m-        Model model) {[m
[31m-        [m
[31m-        try {[m
[31m-            Movie first = movieService.getMovieByTitle(movie1);[m
[31m-            Movie second = movieService.getMovieByTitle(movie2);[m
[31m-            String comparison = geminiService.compareMovies(first.getTitle(), second.getTitle());[m
[31m-            [m
[31m-            model.addAttribute("movie", first);[m
[31m-            model.addAttribute("comparisonMovie", second);[m
[31m-            model.addAttribute("comparisonText", comparison);[m
[32m+[m[32m            model.addAttribute("movie", movie);[m
[32m+[m[32m            model.addAttribute("recommendationList", cleanedTitles);[m
         } catch (Exception e) {[m
[31m-            model.addAttribute("error", "Comparison failed: " + e.getMessage());[m
[32m+[m[32m            model.addAttribute("error", "Error getting recommendations: " + e.getMessage());[m
         }[m
         return "index";[m
     }[m
 [m
[31m-    private List<String> validateFilters(List<String> inputs, Set<String> validValues) {[m
[31m-        if (inputs == null) return Collections.emptyList();[m
[31m-        return inputs.stream()[m
[31m-            .map(String::toLowerCase)[m
[31m-            .filter(validValues::contains)[m
[31m-            .toList();[m
[31m-    }[m
[31m-[m
[31m-    private List<String> cleanRecommendations(String text) {[m
[31m-        return Arrays.stream(text.split("\n"))[m
[31m-            .map(line -> line.replaceAll("^\\d+[\\.\\)]\\s*", "").trim())[m
[31m-            .filter(line -> !line.isEmpty())[m
[31m-            .toList();[m
[31m-    }[m
[31m-}[m
\ No newline at end of file[m
[32m+[m[41m    [m
[32m+[m[41m    [m
[32m+[m[32m}[m
[1mdiff --git a/src/main/java/com/movieexplorer/service/GeminiService.java b/src/main/java/com/movieexplorer/service/GeminiService.java[m
[1mindex a768c82..262fa8c 100644[m
[1m--- a/src/main/java/com/movieexplorer/service/GeminiService.java[m
[1m+++ b/src/main/java/com/movieexplorer/service/GeminiService.java[m
[36m@@ -6,14 +6,11 @@[m [mimport com.fasterxml.jackson.databind.ObjectMapper;[m
 import org.springframework.beans.factory.annotation.Value;[m
 import org.springframework.stereotype.Service;[m
 import java.io.IOException;[m
[31m-import java.util.ArrayList;[m
[31m-import java.util.List;[m
[31m-import java.util.regex.Matcher;[m
[31m-import java.util.regex.Pattern;[m
 [m
 @Service[m
 public class GeminiService {[m
 [m
[32m+[m[32m    // Use the endpoint that works for your account[m
     private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";[m
     [m
     @Value("${gemini.api.key}")[m
[36m@@ -22,73 +19,39 @@[m [mpublic class GeminiService {[m
     private final OkHttpClient client = new OkHttpClient();[m
     private final ObjectMapper objectMapper = new ObjectMapper();[m
 [m
[31m-    public String getFilteredRecommendations(String movieTitle, List<String> genres, List<String> moods) {[m
[31m-        String prompt = buildRecommendationPrompt(movieTitle, genres, moods);[m
[31m-        String rawResponse = processGeminiRequest(prompt);[m
[31m-        return extractCleanTitles(rawResponse);[m
[31m-    }[m
[31m-[m
[31m-    public String processNaturalLanguageQuery(String query) {[m
[31m-        String prompt = "Act as a movie expert. Answer concisely: " + query;[m
[31m-        return processGeminiRequest(prompt);[m
[31m-    }[m
[31m-[m
[31m-    public String compareMovies(String movie1, String movie2) {[m
[31m-        String prompt = "Compare these two movies in a detailed pros/cons format: " + [m
[31m-                      movie1 + " and " + movie2 + ". Focus on plot, themes, and filmmaking style.";[m
[31m-        return processGeminiRequest(prompt);[m
[31m-    }[m
[31m-[m
[31m-    private String buildRecommendationPrompt(String title, List<String> genres, List<String> moods) {[m
[31m-        StringBuilder prompt = new StringBuilder("Suggest exactly 3 movies similar to '")[m
[31m-            .append(title)[m
[31m-            .append("'");[m
[31m-            [m
[31m-        if (!genres.isEmpty()) {[m
[31m-            prompt.append(" in genres: ").append(String.join(", ", genres));[m
[31m-        }[m
[31m-        if (!moods.isEmpty()) {[m
[31m-            prompt.append(" with moods: ").append(String.join(", ", moods));[m
[31m-        }[m
[31m-        [m
[31m-        prompt.append(". Respond ONLY with a numbered list of movie titles (1. Title) without any explanations, descriptions, formatting, or additional text.");[m
[31m-        return prompt.toString();[m
[31m-    }[m
[31m-[m
[31m-    private String extractCleanTitles(String rawResponse) {[m
[31m-        List<String> titles = new ArrayList<>();[m
[31m-        Pattern pattern = Pattern.compile("^\\d+\\.\\s+(.+)$", Pattern.MULTILINE);[m
[31m-        Matcher matcher = pattern.matcher(rawResponse);[m
[31m-        [m
[31m-        while (matcher.find()) {[m
[31m-            titles.add(matcher.group(1).trim());[m
[31m-        }[m
[31m-        [m
[31m-        return titles.isEmpty() ? rawResponse : String.join("\n", titles);[m
[31m-    }[m
[31m-[m
[31m-    private String processGeminiRequest(String promptText) {[m
[32m+[m[32m    public String getRecommendations(String movieTitle) {[m
         try {[m
[31m-            String sanitizedPrompt = promptText.replace("\"", "'");[m
[32m+[m[32m            // Build JSON payload using proper escaping[m
             String jsonBody = String.format([m
[31m-                "{ \"contents\": [{ \"parts\": [{ \"text\": \"%s\" }] }] }",[m
[31m-                sanitizedPrompt[m
[32m+[m[32m                "{ \"contents\": [{ \"parts\": [{ \"text\": \"Suggest 3 movies similar to '%s'. Only list titles without years.\" }] }] }",[m
[32m+[m[32m                movieTitle.replace("\"", "") // Remove quotes to prevent JSON issues[m
             );[m
 [m
[32m+[m[32m            RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json"));[m
[32m+[m[41m            [m
             Request request = new Request.Builder()[m
                 .url(GEMINI_API_URL + "?key=" + apiKey)[m
[31m-                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))[m
[32m+[m[32m                .post(body)[m
                 .build();[m
 [m
             try (Response response = client.newCall(request).execute()) {[m
[32m+[m[32m                String responseBody = response.body().string();[m
[32m+[m[41m                [m
[32m+[m[32m                // Debug output[m
[32m+[m[32m                System.out.println("Request URL: " + request.url());[m
[32m+[m[32m                System.out.println("Request Body: " + jsonBody);[m
[32m+[m[32m                System.out.println("Response Code: " + response.code());[m
[32m+[m[32m                System.out.println("Response Body: " + responseBody);[m
[32m+[m
                 if (!response.isSuccessful()) {[m
[31m-                    return "API Error: " + response.code();[m
[32m+[m[32m                    return "API Error " + response.code() + ": " +[m[41m [m
[32m+[m[32m                           objectMapper.readTree(responseBody).path("error").path("message").asText();[m
                 }[m
 [m
[31m-                JsonNode root = objectMapper.readTree(response.body().string());[m
[32m+[m[32m                JsonNode root = objectMapper.readTree(responseBody);[m
                 return root.path("candidates").get(0)[m
                           .path("content").path("parts").get(0)[m
[31m-                          .path("text").asText("No response found");[m
[32m+[m[32m                          .path("text").asText("No recommendations found");[m
             }[m
         } catch (IOException e) {[m
             return "Error: " + e.getMessage();[m
[1mdiff --git a/src/main/java/com/movieexplorer/service/MovieService.java b/src/main/java/com/movieexplorer/service/MovieService.java[m
[1mindex 7295d41..a855cf6 100644[m
[1m--- a/src/main/java/com/movieexplorer/service/MovieService.java[m
[1m+++ b/src/main/java/com/movieexplorer/service/MovieService.java[m
[36m@@ -1,4 +1,3 @@[m
[31m-// MovieService.java[m
 package com.movieexplorer.service;[m
 [m
 import com.movieexplorer.model.Movie;[m
[36m@@ -15,16 +14,11 @@[m [mpublic class MovieService {[m
     private final RestTemplate restTemplate = new RestTemplate();[m
 [m
     public Movie getMovieByTitle(String title) {[m
[31m-        String url = String.format([m
[31m-            "https://www.omdbapi.com/?apikey=%s&t=%s&plot=full",[m
[31m-            apiKey,[m
[31m-            title.replace(" ", "+")[m
[31m-        );[m
[31m-        [m
[32m+[m[32m        String url = "https://www.omdbapi.com/?apikey=" + apiKey + "&t=" + title.replace(" ", "+");[m
         Movie movie = restTemplate.getForObject(url, Movie.class);[m
         [m
         if (movie == null || movie.getImdbID() == null) {[m
[31m-            throw new RuntimeException("Movie not found: " + title);[m
[32m+[m[32m            throw new RuntimeException("Movie not found in OMDB");[m
         }[m
         return movie;[m
     }[m
[1mdiff --git a/src/main/resources/application.properties b/src/main/resources/application.properties[m
[1mnew file mode 100644[m
[1mindex 0000000..c23ee4d[m
[1m--- /dev/null[m
[1m+++ b/src/main/resources/application.properties[m
[36m@@ -0,0 +1,4 @@[m
[32m+[m[32m# application.properties.example[m
[32m+[m[32mspring.application.name=Movie_Explorer[m
[32m+[m[32momdb.api.key=YOUR_OMDB_API_KEY_HERE[m
[32m+[m[32mgemini.api.key=YOUR_GEMINI_API_KEY_HERE[m
[1mdiff --git a/src/main/resources/static/css/style.css b/src/main/resources/static/css/style.css[m
[1mindex 8db5805..457305a 100644[m
[1m--- a/src/main/resources/static/css/style.css[m
[1m+++ b/src/main/resources/static/css/style.css[m
[36m@@ -1,237 +1,191 @@[m
[31m-/* Global Styles */[m
[31m-:root {[m
[31m-  --primary-color: #2c3e50;[m
[31m-  --accent-color: #3498db;[m
[31m-  --text-color: #333;[m
[31m-  --bg-color: #f8f9fa;[m
[31m-  --card-bg: #ffffff;[m
[31m-  --transition: all 0.3s ease;[m
[31m-}[m
[31m-[m
[31m-[data-theme="dark"] {[m
[31m-  --primary-color: #ecf0f1;[m
[31m-  --accent-color: #3498db;[m
[31m-  --text-color: #ecf0f1;[m
[31m-  --bg-color: #2c3e50;[m
[31m-  --card-bg: #34495e;[m
[31m-}[m
[31m-[m
[31m-* {[m
[31m-  margin: 0;[m
[31m-  padding: 0;[m
[31m-  box-sizing: border-box;[m
[31m-  font-family: 'Inter', sans-serif;[m
[31m-}[m
[31m-[m
[31m-body {[m
[31m-  background-color: var(--bg-color);[m
[31m-  color: var(--text-color);[m
[31m-  line-height: 1.6;[m
[31m-  transition: var(--transition);[m
[31m-  min-height: 100vh;[m
[31m-}[m
[31m-[m
[31m-/* Header Styles */[m
[31m-header {[m
[31m-  background-color: var(--card-bg);[m
[31m-  padding: 1rem 2rem;[m
[31m-  box-shadow: 0 2px 5px rgba(0,0,0,0.1);[m
[31m-  position: fixed;[m
[31m-  width: 100%;[m
[31m-  top: 0;[m
[31m-  z-index: 1000;[m
[31m-  display: flex;[m
[31m-  justify-content: space-between;[m
[31m-  align-items: center;[m
[31m-}[m
[31m-[m
[31m-h1 {[m
[31m-  font-size: 1.5rem;[m
[31m-  color: var(--accent-color);[m
[31m-}[m
[31m-[m
[31m-nav {[m
[31m-  display: flex;[m
[31m-  gap: 2rem;[m
[31m-}[m
[31m-[m
[31m-nav a {[m
[31m-  color: var(--text-color);[m
[31m-  text-decoration: none;[m
[31m-  font-weight: 500;[m
[31m-  transition: var(--transition);[m
[31m-}[m
[31m-[m
[31m-nav a:hover {[m
[31m-  color: var(--accent-color);[m
[31m-}[m
[31m-[m
[31m-.theme-toggle, #darkModeToggle {[m
[31m-  background: none;[m
[31m-  border: 2px solid var(--accent-color);[m
[31m-  color: var(--text-color);[m
[31m-  padding: 0.5rem 1rem;[m
[31m-  border-radius: 25px;[m
[31m-  cursor: pointer;[m
[31m-  transition: var(--transition);[m
[31m-}[m
[31m-[m
[31m-.theme-toggle:hover, #darkModeToggle:hover {[m
[31m-  background-color: var(--accent-color);[m
[31m-  color: white;[m
[31m-}[m
[31m-[m
[31m-/* Main Content Styles */[m
[31m-main {[m
[31m-  max-width: 1200px;[m
[31m-  margin: 6rem auto 2rem;[m
[31m-  padding: 0 1rem;[m
[31m-}[m
[31m-[m
[31m-/* Form Styles */[m
[31m-form {[m
[31m-  background: var(--card-bg);[m
[31m-  padding: 2rem;[m
[31m-  border-radius: 10px;[m
[31m-  box-shadow: 0 4px 6px rgba(0,0,0,0.1);[m
[31m-  margin-bottom: 2rem;[m
[31m-}[m
[31m-[m
[31m-textarea, input[type="text"] {[m
[31m-  width: 100%;[m
[31m-  padding: 1rem;[m
[31m-  margin: 1rem 0;[m
[31m-  border: 2px solid #ddd;[m
[31m-  border-radius: 8px;[m
[31m-  resize: vertical;[m
[31m-  background-color: var(--bg-color);[m
[31m-  color: var(--text-color);[m
[31m-  transition: var(--transition);[m
[31m-}[m
[31m-[m
[31m-textarea:focus, input[type="text"]:focus {[m
[31m-  border-color: var(--accent-color);[m
[31m-  outline: none;[m
[31m-}[m
[31m-[m
[31m-button[type="submit"] {[m
[31m-  background-color: var(--accent-color);[m
[31m-  color: white;[m
[31m-  border: none;[m
[31m-  padding: 0.8rem 2rem;[m
[31m-  border-radius: 8px;[m
[31m-  cursor: pointer;[m
[31m-  font-weight: 600;[m
[31m-  transition: var(--transition);[m
[31m-}[m
[31m-[m
[31m-button[type="submit"]:hover {[m
[31m-  opacity: 0.9;[m
[31m-  transform: translateY(-1px);[m
[31m-}[m
[31m-[m
[31m-/* Response Styles */[m
[31m-.response, .error {[m
[31m-  background: var(--card-bg);[m
[31m-  padding: 2rem;[m
[31m-  border-radius: 10px;[m
[31m-  margin: 2rem 0;[m
[31m-  box-shadow: 0 4px 6px rgba(0,0,0,0.1);[m
[31m-}[m
[31m-[m
[31m-.error {[m
[31m-  border-left: 4px solid #e74c3c;[m
[31m-}[m
[31m-[m
[31m-.response {[m
[31m-  border-left: 4px solid #2ecc71;[m
[31m-}[m
[31m-[m
[31m-pre {[m
[31m-  white-space: pre-wrap;[m
[31m-  word-wrap: break-word;[m
[31m-}[m
[31m-[m
[31m-/* Movie Info Styles */[m
[31m-.movie-info {[m
[31m-  display: grid;[m
[31m-  grid-template-columns: 1fr 2fr;[m
[31m-  gap: 2rem;[m
[31m-  margin: 2rem 0;[m
[31m-}[m
[31m-[m
[31m-.movie-info img {[m
[31m-  max-width: 300px;[m
[31m-  border-radius: 8px;[m
[31m-  box-shadow: 0 4px 6px rgba(0,0,0,0.1);[m
[31m-}[m
[31m-[m
[31m-/* Recommendations Styles */[m
[31m-.recommendation-list {[m
[31m-  display: grid;[m
[31m-  gap: 1rem;[m
[31m-  margin-top: 2rem;[m
[31m-}[m
[31m-[m
[31m-.recommendation-list a {[m
[31m-  color: var(--accent-color);[m
[31m-  text-decoration: none;[m
[31m-  padding: 0.5rem;[m
[31m-  border-radius: 5px;[m
[31m-  transition: var(--transition);[m
[31m-}[m
[31m-[m
[31m-.recommendation-list a:hover {[m
[31m-  background-color: rgba(52, 152, 219, 0.1);[m
[31m-}[m
[31m-[m
[31m-/* Filters Styles */[m
[31m-.filters {[m
[31m-  margin: 2rem 0;[m
[31m-}[m
[31m-[m
[31m-.filter-group {[m
[31m-  margin-bottom: 1.5rem;[m
[31m-}[m
[31m-[m
[31m-.filter-group label {[m
[31m-  display: inline-flex;[m
[31m-  align-items: center;[m
[31m-  margin: 0.5rem 1rem 0.5rem 0;[m
[31m-  padding: 0.5rem 1rem;[m
[31m-  background-color: var(--bg-color);[m
[31m-  border-radius: 20px;[m
[31m-  cursor: pointer;[m
[31m-  transition: var(--transition);[m
[31m-}[m
[31m-[m
[31m-.filter-group input[type="checkbox"] {[m
[31m-  margin-right: 0.5rem;[m
[31m-}[m
[31m-[m
[31m-/* Responsive Design */[m
[31m-@media (max-width: 768px) {[m
[31m-  .movie-info {[m
[31m-    grid-template-columns: 1fr;[m
[31m-  }[m
[31m-  [m
[31m-  header {[m
[31m-    flex-direction: column;[m
[31m-    gap: 1rem;[m
[31m-    padding: 1rem;[m
[31m-  }[m
[31m-  [m
[31m-  main {[m
[31m-    margin-top: 8rem;[m
[31m-  }[m
[31m-}[m
[31m-[m
[31m-@media (max-width: 480px) {[m
[31m-  textarea, input[type="text"] {[m
[31m-    padding: 0.8rem;[m
[31m-  }[m
[31m-  [m
[31m-  button[type="submit"] {[m
[31m-    width: 100%;[m
[31m-  }[m
[31m-}[m
\ No newline at end of file[m
[32m+[m
[32m+[m[32m    :root {[m
[32m+[m[32m        --primary: #0f172a;[m
[32m+[m[32m        --secondary: #1e293b;[m
[32m+[m[32m        --accent: #06b6d4;[m
[32m+[m[32m        --text: #f8fafc;[m
[32m+[m[32m        --text-secondary: #94a3b8;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    body {[m
[32m+[m[32m        font-family: 'Segoe UI', system-ui, sans-serif;[m
[32m+[m[32m        line-height: 1.6;[m
[32m+[m[32m        margin: 0;[m
[32m+[m[32m        padding: 2rem;[m
[32m+[m[32m        background: linear-gradient(160deg, var(--primary), var(--secondary));[m
[32m+[m[32m        color: var(--text);[m
[32m+[m[32m        min-height: 100vh;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    h1 {[m
[32m+[m[32m        font-size: 3rem;[m
[32m+[m[32m        text-align: center;[m
[32m+[m[32m        margin-bottom: 2.5rem;[m
[32m+[m[32m        letter-spacing: -0.05em;[m
[32m+[m[32m        text-shadow: 0 4px 12px rgba(0,0,0,0.2);[m
[32m+[m[32m        animation: fadeInDown 0.8s ease;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    .movie-info {[m
[32m+[m[32m        background: rgba(255,255,255,0.05);[m
[32m+[m[32m        border-radius: 1rem;[m
[32m+[m[32m        padding: 2rem;[m
[32m+[m[32m        margin: 2rem auto;[m
[32m+[m[32m        max-width: 800px;[m
[32m+[m[32m        backdrop-filter: blur(10px);[m
[32m+[m[32m        box-shadow: 0 8px 32px rgba(0,0,0,0.1);[m
[32m+[m[32m        animation: slideIn 0.6s ease;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    form {[m
[32m+[m[32m        display: flex;[m
[32m+[m[32m        gap: 1rem;[m
[32m+[m[32m        max-width: 600px;[m
[32m+[m[32m        margin: 2rem auto;[m
[32m+[m[32m        animation: fadeInUp 0.8s ease;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    input[type="text"] {[m
[32m+[m[32m        flex: 1;[m
[32m+[m[32m        padding: 0.8rem 1.2rem;[m
[32m+[m[32m        border: 2px solid var(--accent);[m
[32m+[m[32m        border-radius: 0.5rem;[m
[32m+[m[32m        background: rgba(255,255,255,0.1);[m
[32m+[m[32m        color: var(--text);[m
[32m+[m[32m        font-size: 1rem;[m
[32m+[m[32m        transition: all 0.3s ease;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    input[type="text"]:focus {[m
[32m+[m[32m        outline: none;[m
[32m+[m[32m        border-color: #22d3ee;[m
[32m+[m[32m        box-shadow: 0 0 0 3px rgba(6,182,212,0.2);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    button {[m
[32m+[m[32m        padding: 0.8rem 1.5rem;[m
[32m+[m[32m        background: var(--accent);[m
[32m+[m[32m        border: none;[m
[32m+[m[32m        border-radius: 0.5rem;[m
[32m+[m[32m        color: white;[m
[32m+[m[32m        font-weight: 600;[m
[32m+[m[32m        cursor: pointer;[m
[32m+[m[32m        transition: all 0.3s ease;[m
[32m+[m[32m        transform-origin: center;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    button:hover {[m
[32m+[m[32m        background: #22d3ee;[m
[32m+[m[32m        transform: scale(1.05);[m
[32m+[m[32m        box-shadow: 0 4px 15px rgba(6,182,212,0.3);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    .recommend-btn {[m
[32m+[m[32m        background: #4f46e5;[m
[32m+[m[32m        margin: 1.5rem 0;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    .recommend-btn:hover {[m
[32m+[m[32m        background: #6366f1;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    img {[m
[32m+[m[32m        border-radius: 0.75rem;[m
[32m+[m[32m        box-shadow: 0 8px 24px rgba(0,0,0,0.2);[m
[32m+[m[32m        margin: 1.5rem 0;[m
[32m+[m[32m        transition: transform 0.3s ease;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    img:hover {[m
[32m+[m[32m        transform: translateY(-4px);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    .recommendations {[m
[32m+[m[32m        background: rgba(255,255,255,0.05);[m
[32m+[m[32m        border-radius: 0.75rem;[m
[32m+[m[32m        padding: 1.5rem;[m
[32m+[m[32m        margin: 2rem 0;[m
[32m+[m[32m        animation: fadeIn 0.6s ease;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    .recommendations h3 {[m
[32m+[m[32m        margin-top: 0;[m
[32m+[m[32m        color: var(--accent);[m
[32m+[m[32m        font-size: 1.5rem;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    .recommendations a {[m
[32m+[m[32m        display: block;[m
[32m+[m[32m        padding: 0.8rem;[m
[32m+[m[32m        margin: 0.5rem 0;[m
[32m+[m[32m        background: rgba(255,255,255,0.05);[m
[32m+[m[32m        border-radius: 0.5rem;[m
[32m+[m[32m        color: var(--text);[m
[32m+[m[32m        text-decoration: none;[m
[32m+[m[32m        transition: all 0.3s ease;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    .recommendations a:hover {[m
[32m+[m[32m        background: rgba(6,182,212,0.1);[m
[32m+[m[32m        transform: translateX(8px);[m
[32m+[m[32m        box-shadow: 2px 0 8px rgba(6,182,212,0.1);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    .error {[m
[32m+[m[32m        background: #ef4444;[m
[32m+[m[32m        color: white;[m
[32m+[m[32m        padding: 1.25rem;[m
[32m+[m[32m        border-radius: 0.75rem;[m
[32m+[m[32m        margin: 2rem auto;[m
[32m+[m[32m        max-width: 600px;[m
[32m+[m[32m        animation: shake 0.4s ease;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @keyframes fadeInDown {[m
[32m+[m[32m        from {[m
[32m+[m[32m            opacity: 0;[m
[32m+[m[32m            transform: translateY(-20px);[m
[32m+[m[32m        }[m
[32m+[m[32m        to {[m
[32m+[m[32m            opacity: 1;[m
[32m+[m[32m            transform: translateY(0);[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @keyframes slideIn {[m
[32m+[m[32m        from {[m
[32m+[m[32m            opacity: 0;[m
[32m+[m[32m            transform: translateY(20px);[m
[32m+[m[32m        }[m
[32m+[m[32m        to {[m
[32m+[m[32m            opacity: 1;[m
[32m+[m[32m            transform: translateY(0);[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @keyframes fadeInUp {[m
[32m+[m[32m        from {[m
[32m+[m[32m            opacity: 0;[m
[32m+[m[32m            transform: translateY(20px);[m
[32m+[m[32m        }[m
[32m+[m[32m        to {[m
[32m+[m[32m            opacity: 1;[m
[32m+[m[32m            transform: translateY(0);[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @keyframes shake {[m
[32m+[m[32m        0%, 100% { transform: translateX(0); }[m
[32m+[m[32m        25% { transform: translateX(-8px); }[m
[32m+[m[32m        50% { transform: translateX(8px); }[m
[32m+[m[32m        75% { transform: translateX(-4px); }[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    strong {[m
[32m+[m[32m        color: var(--accent);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    p {[m
[32m+[m[32m        margin: 0.8rem 0;[m
[32m+[m[32m        color: var(--text-secondary);[m
[32m+[m[32m    }[m
[1mdiff --git a/src/main/resources/static/js/script.js b/src/main/resources/static/js/script.js[m
[1mdeleted file mode 100644[m
[1mindex 70c13e0..0000000[m
[1m--- a/src/main/resources/static/js/script.js[m
[1m+++ /dev/null[m
[36m@@ -1,35 +0,0 @@[m
[31m-// script.js[m
[31m-document.addEventListener('DOMContentLoaded', () => {[m
[31m-    // Theme handling[m
[31m-    const themeToggleButtons = document.querySelectorAll('.theme-toggle, #darkModeToggle');[m
[31m-    const currentTheme = localStorage.getItem('theme') || 'light';[m
[31m-[m
[31m-    // Set initial theme[m
[31m-    document.body.setAttribute('data-theme', currentTheme);[m
[31m-[m
[31m-    // Update button state[m
[31m-    const updateButton = () => {[m
[31m-        const isDark = document.body.getAttribute('data-theme') === 'dark';[m
[31m-        themeToggleButtons.forEach(button => {[m
[31m-            button.textContent = isDark ? '🌞' : '🌓';[m
[31m-        });[m
[31m-    };[m
[31m-[m
[31m-    // Toggle theme function[m
[31m-    const toggleTheme = () => {[m
[31m-        const currentTheme = document.body.getAttribute('data-theme');[m
[31m-        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';[m
[31m-        [m
[31m-        document.body.setAttribute('data-theme', newTheme);[m
[31m-        localStorage.setItem('theme', newTheme);[m
[31m-        updateButton();[m
[31m-    };[m
[31m-[m
[31m-    // Add event listeners[m
[31m-    themeToggleButtons.forEach(button => {[m
[31m-        button.addEventListener('click', toggleTheme);[m
[31m-    });[m
[31m-[m
[31m-    // Initialize button state[m
[31m-    updateButton();[m
[31m-});[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/templates/ask.html b/src/main/resources/templates/ask.html[m
[1mdeleted file mode 100644[m
[1mindex 1a36c32..0000000[m
[1m--- a/src/main/resources/templates/ask.html[m
[1m+++ /dev/null[m
[36m@@ -1,41 +0,0 @@[m
[31m-<!DOCTYPE html>[m
[31m-<html xmlns:th="http://www.thymeleaf.org">[m
[31m-<head>[m
[31m-    <title>Ask Movie AI</title>[m
[31m-    <meta charset="UTF-8">[m
[31m-    <link rel="stylesheet" href="/css/style.css">[m
[31m-    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fontsource/inter@5.0.3/css/400;500;600;700.css">[m
[31m-</head>[m
[31m-<body>[m
[31m-    <header>[m
[31m-        <h1>Movie AI Assistant</h1>[m
[31m-        <nav>[m
[31m-            <a href="/">Home</a>[m
[31m-            <a href="/ask">Ask AI</a>[m
[31m-        </nav>[m
[31m-        <button class="theme-toggle">🌓</button>[m
[31m-    </header>[m
[31m-[m
[31m-    <main>[m
[31m-        <div th:if="${error}" class="error">[m
[31m-            <h3>Error:</h3>[m
[31m-            <p th:text="${error}"></p>[m
[31m-        </div>[m
[31m-[m
[31m-        <form th:action="@{/ask}" method="post">[m
[31m-            <textarea name="query" placeholder="Ask me anything about movies... [m
[31m-Example: 'Show me romantic comedies from the 90s'[m
[31m-Example: 'What are similar movies to Inception?'" [m
[31m-                     rows="5"></textarea>[m
[31m-            <button type="submit">Ask AI</button>[m
[31m-        </form>[m
[31m-[m
[31m-        <div th:if="${aiResponse}" class="response">[m
[31m-            <h3>AI Response:</h3>[m
[31m-            <pre th:text="${aiResponse}"></pre>[m
[31m-        </div>[m
[31m-    </main>[m
[31m-[m
[31m-    <script src="/js/script.js"></script>[m
[31m-</body>[m
[31m-</html>[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/templates/index.html b/src/main/resources/templates/index.html[m
[1mindex 2ccd735..346c71b 100644[m
[1m--- a/src/main/resources/templates/index.html[m
[1m+++ b/src/main/resources/templates/index.html[m
[36m@@ -1,78 +1,55 @@[m
 <!DOCTYPE html>[m
 <html xmlns:th="http://www.thymeleaf.org">[m
 <head>[m
[31m-[m
     <title>Movie Explorer</title>[m
     <meta charset="UTF-8">[m
     <link rel="stylesheet" href="/css/style.css">[m
[31m-    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fontsource/inter@5.0.3/css/400;500;600;700.css">[m
 </head>[m
 <body>[m
[31m-    <header>[m
[31m-        <h1>Movie Explorer</h1>[m
[31m-        <nav>[m
[31m-            <a href="/">Home</a>[m
[31m-            <a href="/ask">Ask AI</a>[m
[31m-        </nav>[m
[31m-        <button id="darkModeToggle">Toggle Dark Mode</button>[m
[31m-    </header>[m
[32m+[m[32m<h1>Movie Explorer 🎬</h1>[m
 [m
[31m-    <main>[m
[31m-        <div th:if="${error}" class="error">[m
[31m-            <h3>Error:</h3>[m
[31m-            <p th:text="${error}"></p>[m
[31m-        </div>[m
[32m+[m[32m<!-- Error Messages -->[m
[32m+[m[32m<div th:if="${error}" class="error">[m
[32m+[m[32m    <h3>⚠️ Error:</h3>[m
[32m+[m[32m    <p th:text="${error}"></p>[m
[32m+[m[32m</div>[m
 [m
[31m-        <form th:action="@{/search}" method="get">[m
[31m-            <input type="text" name="title" placeholder="Search movie..." required>[m
[31m-            <button type="submit">Search</button>[m
[31m-        </form>[m
[32m+[m[32m<!-- Search Form -->[m
[32m+[m[32m<form th:action="@{/search}" method="get">[m
[32m+[m[32m    <label for="title">Enter movie title:</label>[m
[32m+[m[32m    <input type="text" id="title" name="title" required[m[41m [m
[32m+[m[32m           th:value="${movie?.title ?: ''}">[m
[32m+[m[32m    <button type="submit">Search</button>[m
[32m+[m[32m</form>[m
 [m
[31m-        <div th:if="${movie != null}">[m
[31m-            <div class="movie-info">[m
[31m-                <h2 th:text="${movie.title}"></h2>[m
[31m-                <img th:src="${movie.poster}" alt="Poster">[m
[31m-                <p>Year: <span th:text="${movie.year}"></span></p>[m
[31m-                <p>Director: <span th:text="${movie.director}"></span></p>[m
[31m-                <p>Plot: <span th:text="${movie.plot}"></span></p>[m
[31m-                <p>Rating: <span th:text="${movie.imdbRating}"></span></p>[m
[31m-            </div>[m
[32m+[m[32m<!-- Movie Info -->[m
[32m+[m[32m<div th:if="${movie != null}">[m
[32m+[m[32m    <div class="movie-info">[m
[32m+[m[32m        <h2 th:text="${movie.title}"></h2>[m
[32m+[m[32m        <img th:src="${movie.poster}" alt="Movie Poster">[m
[32m+[m[32m        <p><strong>Year:</strong> <span th:text="${movie.year}"></span></p>[m
[32m+[m[32m        <p><strong>Director:</strong> <span th:text="${movie.director}"></span></p>[m
[32m+[m[32m        <p><strong>Plot:</strong> <span th:text="${movie.plot}"></span></p>[m
[32m+[m[32m        <p><strong>IMDb Rating:</strong> <span th:text="${movie.imdbRating}"></span></p>[m
[32m+[m[32m    </div>[m
 [m
[31m-            <form th:action="@{/recommend}" method="get">[m
[31m-                <input type="hidden" name="title" th:value="${movie.title}">[m
[31m-                [m
[31m-                <div class="filters">[m
[31m-                    <div class="filter-group">[m
[31m-                        <h4>Genres</h4>[m
[31m-                        <div th:if="${movie?.genre}">[m
[31m-                            <label th:each="genre : ${#strings.arraySplit(movie.genre, ',')}">[m
[31m-                                <input type="checkbox" name="genres" th:value="${#strings.trim(genre)}">[m
[31m-                                <span th:text="${#strings.trim(genre)}"></span>[m
[31m-                            </label>[m
[31m-                        </div>[m
[31m-                        <div th:unless="${movie?.genre}">[m
[31m-                            <label th:each="genre : ${['Action', 'Drama', 'Comedy']}">[m
[31m-                                <input type="checkbox" name="genres" th:value="${genre}">[m
[31m-                                <span th:text="${genre}"></span>[m
[31m-                            </label>[m
[31m-                        </div>[m
[31m-                    </div>[m
[31m-                </div>[m
[31m-                <button type="submit">Get Recommendations</button>[m
[31m-            </form>[m
[32m+[m[32m    <!-- AI Recommendation Trigger -->[m
[32m+[m[32m    <form th:action="@{/recommend}" method="get">[m
[32m+[m[32m        <input type="hidden" name="title" th:value="${movie.title}">[m
[32m+[m[32m        <button type="submit" class="recommend-btn">Get AI Recommendations</button>[m
[32m+[m[32m    </form>[m
 [m
[31m-            <div th:if="${recommendationList != null}">[m
[31m-                <h3>Recommendations:</h3>[m
[31m-                <ul>[m
[31m-                    <li th:each="rec : ${recommendationList}">[m
[31m-                        <a th:href="@{/search(title=${#strings.replace(rec, '^\\d+\\.\\s*', '')})}"[m
[31m-                           th:text="${rec}"></a>[m
[31m-                    </li>[m
[31m-                </ul>[m
[31m-            </div>[m
[31m-        </div>[m
[31m-    </main>[m
[32m+[m[32m    <!-- Clickable AI Recommendations -->[m
[32m+[m[32m    <div th:if="${recommendationList != null}" class="recommendations">[m
[32m+[m[32m        <h3>🎥 AI Recommendations:</h3>[m
[32m+[m[32m        <ul>[m
[32m+[m[32m            <li th:each="rec : ${recommendationList}">[m
[32m+[m[32m                <a th:href="@{/search(title=${#strings.replace(rec, '^\\d+\\.\\s*', '')})}"[m
[32m+[m[32m                   th:text="${rec}"></a>[m
[32m+[m[32m            </li>[m
[32m+[m[32m        </ul>[m
[32m+[m[32m    </div>[m
[32m+[m[32m</div>[m
 [m
[31m-    <script src="/js/script.js"></script>[m
 </body>[m
[31m-</html>[m
\ No newline at end of file[m
[32m+[m[32m</html>[m
