<<<<<<< HEAD
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
=======
# 🎬 Movie Explorer: AI-Powered Movie Recommendation App

Movie Explorer is a full-stack web application built with **Spring Boot** and **Thymeleaf** that lets users:

- 🔍 Search for detailed movie information using the **OMDb API**
- 🤖 Get intelligent movie recommendations powered by **Google Gemini AI**
- 💬 Ask natural language questions about movies
- ⚖️ Compare two movies based on plot, themes, and filmmaking style

This project combines traditional movie lookup with modern AI to offer a seamless and smart movie discovery experience.

---

## 🚀 Features

- 🔎 **Search Movies:** Look up any movie by title using OMDb API
- 🤝 **Gemini AI Recommendations:** Get AI-curated recommendations based on genre and mood filters
- 🧠 **AI Chat Interface:** Ask the assistant any movie-related question
- 📊 **Movie Comparison:** Compare two movies using detailed AI-generated insights
- 🌗 **Light/Dark Mode Toggle:** Accessible and responsive UI with dynamic theme switching

---

## 🛠️ Tech Stack

| Layer        | Technology                     |
|--------------|--------------------------------|
| Backend      | Java 17, Spring Boot, REST     |
| Frontend     | Thymeleaf, HTML5, CSS3         |
| APIs         | [OMDb API](https://www.omdbapi.com/), [Google Gemini API](https://ai.google.dev/) |
| Tools        | Maven, OkHttp, Jackson, IntelliJ/Eclipse |

## ⚙️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/movie-explorer.git
cd movie-explorer
```

### 2. Add API Keys

You must supply your own API keys to use this application:

Edit `src/main/resources/application.properties` and insert:

```properties
omdb.api.key=YOUR_OMDB_API_KEY
gemini.api.key=YOUR_GEMINI_API_KEY
```

- Get your **OMDb API Key** from: https://www.omdbapi.com/apikey.aspx
- Get your **Gemini AI Key** from: https://ai.google.dev/

> ❗ The app will not work without valid API keys.

### 3. Build and Run the App

```bash
./mvnw spring-boot:run
```

Open in your browser: `http://localhost:8080`

---

## 🔍 Directory Structure

```bash
src
├── main
│   ├── java
│   │   └── com.movieexplorer
│   │       ├── controller        # Web Controllers (MovieController.java)
│   │       ├── model             # Data model (Movie.java)
│   │       └── service           # OMDb & Gemini logic (MovieService, GeminiService)
│   └── resources
│       ├── templates             # Thymeleaf HTML views (index.html, ask.html)
│       ├── static                # CSS/JS assets
│       └── application.properties # Configuration
```

---

## 🧪 Example Queries

- **Ask AI:**  
  _"Show me romantic comedies from the 90s"_  
  _"What are similar movies to Inception?"_

- **Compare Movies:**  
  _In the browser: `/compare?movie1=Inception&movie2=Interstellar`_

---

## 🌐 Live Features

- Intelligent movie suggestions based on real-time Gemini API calls
- Clean UI using modern CSS
- Filters for genres and moods
- Optimized prompt formatting for concise Gemini results

---

## 🧱 Future Enhancements

- User login with JWT & session history
- Caching frequently accessed movies
- Deploy to AWS or Render
- React frontend (next iteration)

---

## 👨‍💻 Author

**Vraj Contractor**  
[GitHub](https://github.com/Vraj-x2) | [Portfolio](https://vraj-x2.github.io/Portfolio) | vrajcontractor20@gmail.com

---

## 📄 License

This project is licensed under the MIT License.
>>>>>>> 6f205b34981cc6eb928538da1d83d7856ae3165b
