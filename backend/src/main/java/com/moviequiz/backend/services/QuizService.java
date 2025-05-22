package com.moviequiz.backend.services;

import com.moviequiz.backend.models.Answer;
import com.moviequiz.backend.models.Question;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class QuizService {

    private static final List<Question> questions = List.of(
        new Question(1, "When faced with a difficult decision, do you rely more on logic or emotion?", List.of("Logic", "Emotion")),
        new Question(2, "Do you find comfort in routine, or do you crave unexpected experiences?", List.of("Routine", "Unexpected")),
        new Question(3, "How do you usually react to conflict?", List.of("Avoid it", "Confront it")),
        new Question(4, "Do you enjoy thinking about 'what if' scenarios?", List.of("Yes", "No")),
        new Question(5, "Do you enjoy stories that explore deep emotional relationships?", List.of("Yes", "No")),
        new Question(6, "Do you prefer stories with a clear resolution or an open ending?", List.of("Clear", "Open")),
        new Question(7, "How do you feel about morally grey characters?", List.of("Fascinating", "Uncomfortable")),
        new Question(8, "Do you feel energized by social events or drained by them?", List.of("Energized", "Drained")),
        new Question(9, "Do you enjoy exploring dark or mysterious ideas in stories?", List.of("Yes", "No")),
        new Question(10, "Would you rather experience an emotional journey or an adrenaline rush?", List.of("Emotional", "Adrenaline"))
    );

    public List<Question> getAllQuestions() {
        return questions;
    }

    public List<String> getRecommendations(List<Answer> answers) {
        Map<String, Integer> genreScore = new HashMap<>();

        genreScore.put("Drama", 0);
        genreScore.put("Action", 0);
        genreScore.put("Romance", 0);
        genreScore.put("Comedy", 0);
        genreScore.put("Sci-fi", 0);
        genreScore.put("Thriller", 0);

        for (Answer ans : answers) {
            String choice = ans.getSelectedOption();
            int qid = ans.getQuestionId();

            switch (qid) {
                case 1 -> genreScore.computeIfPresent(choice.equals("Logic") ? "Sci-fi" : "Drama", (k, v) -> v + 1);
                case 2 -> genreScore.computeIfPresent(choice.equals("Routine") ? "Drama" : "Thriller", (k, v) -> v + 1);
                case 3 -> genreScore.computeIfPresent(choice.equals("Avoid it") ? "Romance" : "Action", (k, v) -> v + 1);
                case 4 -> genreScore.computeIfPresent(choice.equals("Yes") ? "Sci-fi" : "Comedy", (k, v) -> v + 1);
                case 5 -> genreScore.computeIfPresent(choice.equals("Yes") ? "Romance" : "Action", (k, v) -> v + 1);
                case 6 -> genreScore.computeIfPresent(choice.equals("Clear") ? "Comedy" : "Thriller", (k, v) -> v + 1);
                case 7 -> genreScore.computeIfPresent(choice.equals("Fascinating") ? "Thriller" : "Comedy", (k, v) -> v + 1);
                case 8 -> genreScore.computeIfPresent(choice.equals("Energized") ? "Comedy" : "Drama", (k, v) -> v + 1);
                case 9 -> genreScore.computeIfPresent(choice.equals("Yes") ? "Thriller" : "Sci-fi", (k, v) -> v + 1);
                case 10 -> genreScore.computeIfPresent(choice.equals("Emotional") ? "Drama" : "Action", (k, v) -> v + 1);
            }
        }

        return genreScore.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(2)
                .map(Map.Entry::getKey)
                .toList();
    }
}
