package org.features.dictionary;

import java.util.*;

public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.options = new String[]{optionA, optionB, optionC, optionD};
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void shuffleOptions(Random random) {
        for (int i = options.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            String temp = options[index];
            options[index] = options[i];
            options[i] = temp;
        }
    }

    public void displayOptions() {
        for (String option : options) {
            System.out.println(option);
        }
    }
}