package com.moviequiz.backend.models;

import java.util.List;

public class UserResponse {
    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
