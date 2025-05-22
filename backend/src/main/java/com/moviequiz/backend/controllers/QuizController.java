package com.moviequiz.backend.controllers;

import com.moviequiz.backend.models.UserResponse;
import com.moviequiz.backend.models.Question;
import com.moviequiz.backend.services.QuizService;
import com.moviequiz.backend.services.TmdbService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend to talk to backend
public class QuizController {

    private final QuizService quizService;
    private final TmdbService tmdbService;

    public QuizController(QuizService quizService, TmdbService tmdbService) {
        this.quizService = quizService;
        this.tmdbService = tmdbService;
    }

    // ✅ Get all quiz questions
    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return quizService.getAllQuestions();
    }

    // ✅ Submit user answers and return movie recommendations
    @PostMapping("/submit")
    public List<String> submitAnswers(@RequestBody UserResponse response) {
        // Step 1: Get top genres from answers
        List<String> recommendedGenres = quizService.getRecommendations(response.getAnswers());

        // Step 2: For each genre, get movie titles from TMDB
        List<String> movieTitles = new ArrayList<>();
        for (String genre : recommendedGenres) {
            movieTitles.addAll(tmdbService.getMoviesByGenre(genre));
        }

        return movieTitles;
    }
}
