package com.moviequiz.backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> getMoviesByGenre(String genreName) {
        int genreId = getGenreIdByName(genreName);
        if (genreId == -1) return List.of();

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.themoviedb.org/3/discover/movie")
                .queryParam("api_key", apiKey)
                .queryParam("with_genres", genreId)
                .queryParam("sort_by", "popularity.desc")
                .queryParam("language", "en-US")
                .queryParam("page", "1")
                .toUriString();

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody().get("results");

        return results.stream()
                .limit(3)
                .map(movie -> (String) movie.get("title"))
                .toList();
    }

    private int getGenreIdByName(String genreName) {
        return switch (genreName.toLowerCase()) {
            case "action" -> 28;
            case "comedy" -> 35;
            case "drama" -> 18;
            case "romance" -> 10749;
            case "sci-fi" -> 878;
            case "thriller" -> 53;
            default -> -1;
        };
    }
}
