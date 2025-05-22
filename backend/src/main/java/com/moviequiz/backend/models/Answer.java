package com.moviequiz.backend.models;

public class Answer {
    private int questionId;
    private String selectedOption; // was "answer" before, now matching QuizService

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
