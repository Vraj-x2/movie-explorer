# 🎬 Movie Explorer

A Spring Boot + Thymeleaf web application that allows users to:
- Search for movies using the **OMDb API**
- Get **AI-powered movie recommendations** using **Gemini (Google AI)**
- Click on AI-recommended titles to instantly search for them

---

## 🌐 Live Demo 
> Coming Soon...

---

## 🚀 Features

- 🔍 Search movies by title (real-time OMDb integration)
- 🧠 AI recommendations using Gemini (Gemini 2.0 Flash model)
- 📸 Displays movie details: title, year, director, rating, poster, and plot
- 💡 Clickable recommendations to auto-search similar movies
- 🖼️ Responsive, modern UI styled with CSS

---

## 🧰 Technologies Used

| Layer        | Stack                          |
|--------------|---------------------------------|
| Backend      | Spring Boot 3, Java 21          |
| Frontend     | Thymeleaf, HTML/CSS             |
| AI/ML        | Google Gemini API (v1beta)      |
| Movie Data   | OMDb API                        |
| HTTP Client  | OkHttp, Jackson (for JSON)      |

---

## 🛠️ Setup Instructions

### 1. Clone the project

```bash
git clone https://github.com/Vraj-x2/movie-explorer.git
cd movie-explorer
```

### 2. Create `application.properties`

Create a new file at `src/main/resources/application.properties` and add:

```properties
spring.application.name=Movie_Explorer

omdb.api.key=your_omdb_api_key
gemini.api.key=your_gemini_api_key
```

> 🔐 **Note:** Never commit your real API keys to GitHub!

### 3. Run the project

```bash
./mvnw spring-boot:run
```

Then go to: [http://localhost:8080](http://localhost:8080)

---

## 📸 Screenshots

| Search Movie | AI Recommendations |
|--------------|--------------------|
| ![Search](docs/search-screenshot.png) | ![AI](docs/ai-recommendation.png) |

---

## 💡 Future Enhancements

- Pagination for results
- Autocomplete suggestions
- Save favorite movies
- Dark mode toggle
- Deploy to Render/Vercel with public demo

---

## 🤝 Contributing

Pull requests and suggestions are welcome! Feel free to open an issue or fork the repo.

---

## 📄 License

This project is open source under the [MIT License](LICENSE).

---

## 👨‍💻 Author

**Vraj Contractor**  
[LinkedIn](https://linkedin.com/in/your-profile) | [GitHub](https://github.com/contracv)
