package org.features;

import java.util.Random;

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

/*public void insertFromFile(String fileName) throws Exception {
        dictionary = new Dictionary();
        File dictionaryFile = new File(dictionaries_txt + fileName);
        Scanner sc = new Scanner(dictionaryFile);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] words = line.split("|");
            addList_word(words[0].trim(), words[1].trim());
        }
       *//* Sort.sortDictionaryInAlphabeticalOrder(dictionary.getList_word());*//*
        sc.close();
    }


    public void dictionaryImportFromFile(String fileName) throws FileNotFoundException {
        dictionary = new Dictionary();
        File dictionaryFile = new File(dictionaries_txt + fileName);
        Scanner sc = new Scanner(dictionaryFile);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] words = line.split(":");
            addList_word(words[0].trim(), words[1].trim());
        }

        sc.close();
    }*/