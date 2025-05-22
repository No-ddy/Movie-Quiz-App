package com.moviequiz.backend.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    // Hardcoded genre → movies list
    private static final Map<String, List<String>> genreToMovies = Map.of(
        "Drama", List.of("The Pursuit of Happyness", "Whiplash", "Forrest Gump"),
        "Action", List.of("John Wick", "Mad Max: Fury Road", "The Dark Knight"),
        "Romance", List.of("La La Land", "Pride & Prejudice", "The Notebook"),
        "Comedy", List.of("Superbad", "The Grand Budapest Hotel", "The Intern"),
        "Sci-fi", List.of("Interstellar", "The Matrix", "Blade Runner 2049"),
        "Thriller", List.of("Gone Girl", "Shutter Island", "Se7en")
    );

    // Takes a list of genres and returns 3 movies from each
    public List<String> getMoviesFromGenres(List<String> genres) {
        List<String> recommendations = new ArrayList<>();

        for (String genre : genres) {
            List<String> movies = genreToMovies.getOrDefault(genre, Collections.emptyList());
            // Add 1–3 movies per genre (or all if <3)
            recommendations.addAll(movies.subList(0, Math.min(3, movies.size())));
        }

        return recommendations;
    }
}
