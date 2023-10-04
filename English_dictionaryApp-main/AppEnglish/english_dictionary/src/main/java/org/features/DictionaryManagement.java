package org.features;

import java.io.*;
// import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    private Scanner scanner;
    private Dictionary dictionary = new Dictionary();
    private Map<StringBuilder, StringBuilder> word_tuple;
    private String dictionaries_txt = "";

    public DictionaryManagement() {
        scanner = new Scanner(System.in);
        word_tuple = new HashMap<>();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void insertFromCommandline() {
        int number_of_words = scanner.nextInt();
        scanner.nextLine();
        while (number_of_words-- > 0) {
            addList_word(scanner.nextLine(), scanner.nextLine());
        }
    }

    public void dictionaryLookup() {
        System.out.println("Hello! Here is the Lookup Option! Please try agian soon!");
    }

    public void dictionarySearcher() {
        if (word_tuple.size() > 0) {
            word_tuple.clear();
        }
        for (int i = 0; i < getList_word().size(); i++) {
            word_tuple.put(new StringBuilder(getList_word().get(i).getWord_target()), new StringBuilder(getList_word().get(i).getWord_explain()));
        }

        System.out.println("Vui long nhap tu ma ban muon tra: ");
        StringBuilder userWord = new StringBuilder(scanner.next());
        for (StringBuilder x : word_tuple.keySet()) {
            if (x.toString().substring(0, userWord.length()).equals(userWord.toString())) {
                System.out.println(x);
            }
        }
    }

    public void addUserWordToList() {
        // scanner.nextLine();
        System.out.println("Nhap tu tieng Anh: ");
        String englishWord = scanner.nextLine();
        System.out.println("Nhap tu tieng Viet giai thich nghia cua tu vua roi: ");
        String vietnameseWord = scanner.nextLine();
        addList_word(englishWord, vietnameseWord);
    }

    public void editWordFromUser() {
        System.out.println("Nhap tu tieng Anh: ");
        String englishWord = scanner.nextLine();
        System.out.println("Nhap tu tieng Viet thay the nghia cua tu: ");
        String vietnameseWord = scanner.nextLine();
        editList_word(englishWord, vietnameseWord);
    }

    public void deleteWordFromUser() {
        System.out.println("Nhap tu tieng Anh: ");
        String englishWord = scanner.nextLine();
        System.out.println("Ban co chac chan muon xoa tu nay ra khoi tu dien ?");
        System.out.println("Bam Y de dong y, bam phim bat ki de huy bo");
        if (scanner.next() == "Y") deleteList_word(englishWord);
    }

    public void insertFromFile(String fileName) throws Exception {
        dictionary = new Dictionary();
        File dictionaryFile = new File(dictionaries_txt + fileName);
        Scanner sc = new Scanner(dictionaryFile);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] words = line.split("|");
            addList_word(words[0].trim(), words[1].trim());
        }
       /* Sort.sortDictionaryInAlphabeticalOrder(dictionary.getList_word());*/
        sc.close();
    }

    public void dictionaryExportToFile() throws IOException {
        //Create file
        String exportDictionaryFile = dictionaries_txt + "EXPORT_dictionary.txt";
        File exportFile = new File(exportDictionaryFile);
        if (exportFile.createNewFile()) {
            System.out.println("EXPORTED: " + dictionaries_txt + " --INTO-- " + exportFile);
        } else {
            System.out.println(exportDictionaryFile + "EXISTS.");
        }
        //Write dictionary into file
        StringBuilder newDictionaryContent = new StringBuilder();
        for (Word word : getList_word()) {
            newDictionaryContent.append(word.getWord_target()).append("| ")
                    .append(word.getWord_explain()).append("\n");
        }
        FileWriter fileWriter = new FileWriter(exportDictionaryFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(newDictionaryContent);
        printWriter.close();
        fileWriter.close();

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
    }

    public void VocabularyQuizGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("I have been waiting for you……………", "[A] since early morning", "[B] since 9 a.m", "[C] for two hours", "[D] all are correct", "D"));
        questions.add(new Question("My sister…………for you since yesterday.", "[A] is looking", "[B] was looking", "[C] has been looking", "[D] looked", "C"));
        questions.add(new Question("Jack…………the door.", "[A] has just painted", "[B] paint", "[C] will have painted", "[D] painting", "A"));
        questions.add(new Question("The train…………half an hour.", "[A] has been leaving", "[B] left", "[C] has left", "[D] had left", "B"));
        questions.add(new Question("We…………Doris since last Sunday.", "[A] don’t see", "[B] haven’t seen", "[C] didn’t see", "[D] hadn’t seen", "B"));
        questions.add(new Question("When I last saw him, he…………in London.", "[A] has lived", "[B] is living", "[C] was living", "[D] has been living", "C"));
        questions.add(new Question("After I……………lunch, I looked for my bag.", "[A] had", "[B] had had", "[C] have has", "[D] have had", "B"));
        questions.add(new Question("By the end of next year, Geoge……………English for 2 years", "[A] will have learned", "[B] will learn", "[C] has learned", "[D] would learn", "A"));
        questions.add(new Question("Oil …………if you pour it on water.", "[A] floated", "[B] floats", "[C] will be floated", "[D] float", "B"));
        questions.add(new Question("Manchester United on this year will……………", "[A] win the Champions League!", "[B] win the Premier League!", "[C] become the king of Carabao Cup!", "[D] won the second great 3 in history!", "C"));

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question current_Q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + current_Q.getQuestion());
            current_Q.shuffleOptions(random);
            current_Q.displayOptions();

            System.out.print("Your choice [A/B/C/D]: ");
            String playerAnswer = scanner.nextLine();

            if (playerAnswer.equalsIgnoreCase(current_Q.getCorrectAnswer())) {
                System.out.println("Correct! You got it right.");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is " + current_Q.getCorrectAnswer());
            }

            System.out.println();
        }

        if(score == 10) {
            System.out.println("You are fantastic! I think only Mu fans can be great like you.");
        }
        else {
            System.out.println("Your final score: " + score + "/" + questions.size());
            System.out.println("Try agian to get Ten Hag Point!");
        }
    }
}

