package org.features;

import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {   

    public DictionaryManagement() {
        
    }

    public void insertFromCommandline() {
        int number_of_words = getScanner().nextInt();
        getScanner().nextLine();
        while (number_of_words-- > 0) {
            String englishWord = getScanner().next();
            String vietnameseWord = getScanner().next();
            addListWord(englishWord, vietnameseWord);
            addMapWord(englishWord, vietnameseWord);
            addFileWord(englishWord, vietnameseWord, "english_dictionary/src/main/resources/dictionaries.txt");
        }
    }
    
    public void insertFromFile(String filePath) 
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
                    addFileWord(word_from_pair[0], word_from_pair[1], "english_dictionary/src/main/resources/dictionaries.txt");
                }
            }
        } catch (IOException e) {
            System.out.println("Having problem with reading from files");
        }
    }

    public void dictionaryLookup() 
    {
        System.out.print("Vui long nhap tu ma ban muon tra: ");
        String userWord = getScanner().nextLine();

        if (!getMapWord().containsKey(userWord)) {
            System.out.println("Khong tim thay tu duoc nhap vao.");
        }
        else
        {
            for (int i = 0; i < getMapWord().get(userWord).size(); i++) {
                System.out.println("Nghia thu " + (i + 1) + " cua tu " + userWord + "la: " + getMapWord().get(userWord).get(i));
            }
        }
    }

    public void addUserWordToList() {
        // scanner.nextLine();
        System.out.print("Nhap tu tieng Anh: ");
        String englishWord = getScanner().nextLine();
        System.out.print("Nhap tu tieng Viet giai thich nghia cua tu vua roi: ");
        String vietnameseWord = getScanner().nextLine();
        addListWord(englishWord, vietnameseWord);
        addMapWord(englishWord, vietnameseWord);
        addFileWord(englishWord, vietnameseWord, "english_dictionary/src/main/resources/dictionaries.txt");
    }

    public void editWordFromUser() {
        System.out.print("Nhap tu tieng Anh: ");
        String englishWord = getScanner().nextLine();
        System.out.print("Nhap tu tieng Viet thay the nghia cua tu: ");
        String vietnameseWord = getScanner().nextLine();
        editListWord(englishWord, vietnameseWord);
        editMapWord(englishWord, vietnameseWord);
        editFileWord(englishWord, vietnameseWord, "english_dictionary/src/main/resources/dictionaries.txt");
    }

    public void deleteWordFromUser() {
        System.out.print("Nhap tu tieng Anh: ");
        String englishWord = getScanner().nextLine();
        System.out.println("Ban co chac chan muon xoa tu nay ra khoi tu dien ?");
        System.out.print("Bam Y de dong y, bam phim bat ki de huy bo: ");
        
        if (getScanner().nextLine().equals("Y")) {
            deleteListWord(englishWord);
            deleteMapWord(englishWord);
            deleteFileWord(englishWord, "english_dictionary/src/main/resources/dictionaries.txt");
        }        
    }

    public void dictionarySearcher() {
        System.out.print("Nhap tu muon tra (co the chi can nhap nhung chu cai dau tien cua tu): ");
        String userWord = getScanner().nextLine();
        int count = 0;
        for (String x : getMapWord().keySet()) {
            if (x.substring(Dictionary.FIRST_MEANING, userWord.length()).equals(userWord)) {
                for (int i = 0; i < getMapWord().get(x).size(); i++) {
                    System.out.println("Cap tu thu " + (count + 1) + " la: " + x + '\t' + getMapWord().get(x).get(i));
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    
}
