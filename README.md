# 🎬 Movie Explorer

Movie Explorer is a Spring Boot web application that allows users to search for movie details using the OMDb API and receive AI-based movie recommendations using Google's Gemini AI.

## 🌟 Features

- 🔎 Search any movie title using the OMDb API
- 🧠 Get AI-powered movie recommendations based on the title you searched
- 📸 View movie details like title, year, director, plot, rating, and poster
- 💡 Beautiful, modern UI built with Thymeleaf and custom CSS

## 🚀 Demo

You can run the app locally with the instructions below.

## 🛠 Tech Stack

- Spring Boot 3.4.5
- Thymeleaf
- OMDb API
- Gemini AI (Google Generative Language API)
- OkHttp + Jackson for HTTP and JSON
- HTML/CSS for the frontend

## 📂 File Structure

```
src/
├── main/
│   ├── java/com/movieexplorer/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── service/
│   ├── resources/
│   │   ├── templates/index.html
│   │   ├── static/css/style.css
│   │   └── application.properties.example
```

## 🧪 Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Vraj-x2/movie-explorer.git
   cd movie-explorer
   ```

2. **Configure Your API Keys**
   - Copy the provided demo properties file:
     ```bash
     cp src/main/resources/application.properties.example src/main/resources/application.properties
     ```
   - Replace `YOUR_OMDB_API_KEY_HERE` and `YOUR_GEMINI_API_KEY_HERE` with your actual keys.

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Visit the App**
   - Open your browser at: [http://localhost:8080](http://localhost:8080)

## 🔐 API Keys

- [OMDb API Key](https://www.omdbapi.com/apikey.aspx)
- [Google Gemini API Key](https://ai.google.dev/)

> ⚠️ Never commit your real keys to GitHub. Use `.gitignore` to exclude `application.properties` and track only the `.example` file.

## 🧠 Gemini Prompt Example

```
Suggest 3 movies similar to 'Inception'. Only list titles without years.
```

## 👤 Author

**Vraj Contractor**  
GitHub: [@Vraj-x2](https://github.com/Vraj-x2)

---

📬 Pull requests and contributions are welcome!
