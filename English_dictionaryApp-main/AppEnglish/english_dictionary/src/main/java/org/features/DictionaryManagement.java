package org.features;

import java.io.*;
// import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    private Scanner scanner;
    private Map<StringBuilder, StringBuilder> word_tuple; 

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
    
    /*public void insertFromFile(String filePath)
    {
        File file = new File(filePath) ;

        try {
            List<String> allText = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (String line : allText) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/


    public void dictionaryLookup() 
    {
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
}
