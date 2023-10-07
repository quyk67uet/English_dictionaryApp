package org.features;

import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {   

    public DictionaryManagement() {
        
    }

    public void insertFromCommandline() {
        System.out.print("Nhap so luong cap tu muon them vao, nhan enter de ghi nhan tu: ");
        int number_of_words = getScanner().nextInt();
        getScanner().nextLine();
        while (number_of_words-- > 0) {
            String englishWord = getScanner().nextLine();
            String vietnameseWord = getScanner().nextLine();
            addListWord(englishWord, vietnameseWord);
            addMapWord(englishWord, vietnameseWord);
            addFileWord(englishWord, vietnameseWord, "english_dictionary/src/main/resources/dictionaries.txt");
        }
    }
    
    public void insertFromDefaultFile(String filePath) 
    {
        try (BufferedReader buffer_reader = new BufferedReader(new FileReader(filePath))) {
            while (buffer_reader.ready())
            {
                String word_pair = buffer_reader.readLine();
                String[] word_from_pair = word_pair.split("\t");
                if (word_from_pair.length < 1) {
                    System.out.println("File format error");
                }
                else 
                {
                    addListWord(word_from_pair[0], word_from_pair[1]);
                    addMapWord(word_from_pair[0], word_from_pair[1]);
                    // addFileWord(word_from_pair[0], word_from_pair[1], "english_dictionary/src/main/resources/dictionaries.txt");
                }
            }
        } catch (IOException e) {
            System.out.println("Having problem with reading from files");
        }
    }

    public void insertFromUserFile() {
        getScanner().nextLine();
        System.out.println("Do you want to specify your own filePath ? Press Y to use your own file, otherwise press any other key.");
        System.out.println("The default dictionary text file is located in subfolder named resources.");
        System.out.println("The text in which you provided will not be included to the default text file.");
        System.out.print("This app will also use the default text file, regardless of specifying your own filePath. ");
        if (getScanner().nextLine().equals("Y")) {
            System.out.print("Please specify filePath: ");
            try (BufferedReader buffer_reader = new BufferedReader(new FileReader(getScanner().nextLine()))) {
            while (buffer_reader.ready())
            {
                String word_pair = buffer_reader.readLine();
                String[] word_from_pair = word_pair.split("\t");
                if (word_from_pair.length < 1) {
                    System.out.println("File format error");
                }
                else 
                {
                    addListWord(word_from_pair[0], word_from_pair[1]);
                    addMapWord(word_from_pair[0], word_from_pair[1]);
                    // addFileWord(word_from_pair[0], word_from_pair[1], "english_dictionary/src/main/resources/dictionaries.txt");
                }
            }
            } catch (IOException e) {
                System.out.println("Having problem with reading from files");
            }
        }
    }
    public void dictionaryLookup() 
    {
        System.out.print("Vui long nhap tu ma ban muon tra: ");
        getScanner().nextLine();
        String userWord = getScanner().nextLine();

        if (!getMapWord().containsKey(userWord)) {
            System.out.println("Khong tim thay tu duoc nhap vao.");
        }
        else
        {
            for (int i = 0; i < getMapWord().get(userWord).size(); i++) {
                System.out.println("Nghia thu " + (i + 1) + " cua tu " + userWord + " la: " + getMapWord().get(userWord).get(i));
            }
        }
    }

    public void addUserWordToList() {
        // scanner.nextLine();
        System.out.print("Nhap tu tieng Anh: ");
        getScanner().nextLine();
        String englishWord = getScanner().nextLine();
        System.out.print("Nhap tu tieng Viet giai thich nghia cua tu vua roi: ");
        String vietnameseWord = getScanner().nextLine();
        addListWord(englishWord, vietnameseWord);
        addMapWord(englishWord, vietnameseWord);
        addFileWord(englishWord, vietnameseWord, "english_dictionary/src/main/resources/dictionaries.txt");
        System.out.println("Bo tu cua ban da duoc them.");
    }

    public void editWordFromUser() {
        System.out.print("Nhap tu tieng Anh: ");
        getScanner().nextLine();
        String englishWord = getScanner().nextLine();
        System.out.print("Nhap tu tieng Viet thay the nghia cua tu: ");
        String vietnameseWord = getScanner().nextLine();
        editListWord(englishWord, vietnameseWord);
        editMapWord(englishWord, vietnameseWord);
        editFileWord(englishWord, vietnameseWord, "english_dictionary/src/main/resources/dictionaries.txt");
        System.out.println("Tu cua ban da duoc chinh sua.");
    }

    public void deleteWordFromUser() {
        System.out.print("Nhap tu tieng Anh: ");
        getScanner().nextLine();
        String englishWord = getScanner().nextLine();
        System.out.println("Ban co chac chan muon xoa tu nay ra khoi tu dien ?");
        System.out.print("Bam Y de dong y, bam phim bat ki de huy bo: ");
        
        if (getScanner().nextLine().equals("Y")) {
            deleteListWord(englishWord);
            deleteMapWord(englishWord);
            deleteFileWord(englishWord, "english_dictionary/src/main/resources/dictionaries.txt");
            System.out.println("Bo tu cua ban da duoc xoa.");
        }        
    }

    public void dictionarySearcher() {
        System.out.print("Nhap tu muon tra (co the chi can nhap nhung chu cai dau tien cua tu): ");
        getScanner().nextLine();
        String userWord = getScanner().nextLine();
        int count = 0;
        for (String x : getMapWord().keySet()) {
            if (x.length() >= userWord.length() && x.substring(Dictionary.FIRST_MEANING, userWord.length()).equals(userWord)) {
                for (int i = 0; i < getMapWord().get(x).size(); i++) {
                    System.out.println("Cap tu thu " + (count + 1) + " la: " + x + '\t' + getMapWord().get(x).get(i));
                    count++;
                }
            }
        }
    }

    public void dictionaryExportToFile() throws IOException {
        //Create file
        String exportDictionaryFile = "english_dictionary/src/main/resources/ExportDictionary.txt";
        try (BufferedWriter buffer_writter = new BufferedWriter(new FileWriter(exportDictionaryFile, false))) {
            // buffer_writter.newLine();
            // Write dictionary into file
            for (int i = 0; i < getListWord().size(); i++) {
                String extractedWord = getListWord().get(i).getWord_target() + "\t" + getListWord().get(i).getWord_explain();
                buffer_writter.write(extractedWord);
                buffer_writter.newLine();
            }
            System.out.println("Da xuat file trong subfolder resources thanh cong.");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Having problem in writing to files");
        }
    }

    public void VocabularyQuizGame() {
        Random random = new Random();
        getScanner().nextLine();
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("I have been waiting for you ... ", "[A] since early morning", "[B] since 9 a.m", "[C] for two hours", "[D] all are correct", "D"));
        questions.add(new Question("My sister ... for you since yesterday.", "[A] is looking", "[B] was looking", "[C] has been looking", "[D] looked", "C"));
        questions.add(new Question("Jack ... the door.", "[A] has just painted", "[B] paint", "[C] will have painted", "[D] painting", "A"));
        questions.add(new Question("The train ... half an hour.", "[A] has been leaving", "[B] left", "[C] has left", "[D] had left", "B"));
        questions.add(new Question("We ... Doris since last Sunday.", "[A] don’t see", "[B] haven’t seen", "[C] didn’t see", "[D] hadn’t seen", "B"));
        questions.add(new Question("When I last saw him, he ... in London.", "[A] has lived", "[B] is living", "[C] was living", "[D] has been living", "C"));
        questions.add(new Question("After I ... lunch, I looked for my bag.", "[A] had", "[B] had had", "[C] have has", "[D] have had", "B"));
        questions.add(new Question("By the end of next year, Geoge ... English for 2 years", "[A] will have learned", "[B] will learn", "[C] has learned", "[D] would learn", "A"));
        questions.add(new Question("Oil ... if you pour it on water.", "[A] floated", "[B] floats", "[C] will be floated", "[D] float", "B"));
        questions.add(new Question("Manchester United on this year will ... ", "[A] win the Champions League!", "[B] win the Premier League!", "[C] become the king of Carabao Cup!", "[D] won the second great 3 in history!", "C"));

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question current_Q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + current_Q.getQuestion());
            current_Q.shuffleOptions(random);
            current_Q.displayOptions();

            System.out.print("Your choice [A/B/C/D]: ");
            String playerAnswer = getScanner().nextLine();

            if (playerAnswer.equalsIgnoreCase(current_Q.getCorrectAnswer())) {
                System.out.println("Correct! You got it right.");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is " + current_Q.getCorrectAnswer());
            }

            System.out.println();
        }

        if(score == 10) {
            System.out.println("You are fantastic! I think only MU fans can be great like you.");
        }
        else {
            System.out.println("Your final score: " + score + "/" + questions.size());
            System.out.println("Try agian to get Ten Hag Point!");
        }
    }
}
