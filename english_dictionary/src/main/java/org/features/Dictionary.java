package org.features;

import java.io.*;
import java.util.*;

public class Dictionary {
    protected static final int FIRST_MEANING = 0;
    private Scanner scanner;
    private List<Word> listWord;
    private Map<String, List<String>> mapWord; 

    public Dictionary() {
        listWord = new ArrayList<Word>();
        this.scanner = new Scanner(System.in);
        mapWord = new HashMap<>();
    }
    
    public Dictionary(List<Word> listWord) {
        this.listWord = listWord;
    }

    public List<Word> getListWord() {
        return listWord;
    }

    public void setListWord(List<Word> listWord) {
        this.listWord = listWord;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public Map<String, List<String>> getMapWord() {
        return mapWord;
    }

    public void setMapWord(Map<String, List<String>> mapWord) {
        this.mapWord = mapWord;
    }
    
    public void addListWord(String word_target, String word_explain) {
        listWord.add(new Word(word_target, word_explain));
    }

    public void addMapWord(String word_target, String word_explain) {
        if (mapWord.containsKey(word_target)) {
            if (!mapWord.get(word_target).contains(word_explain)) {
                mapWord.get(word_target).add(word_explain);
            }
            else 
            {
                System.out.println("Da co nghia nay, vui long khong nhap lai.");
            }
        }
        else 
        {
            List<String> word_Explain = new ArrayList<>();
            word_Explain.add(word_explain);
            mapWord.put(word_target, word_Explain);
        }
    }

    public void addFileWord(String word_target, String word_explain, String filePath) {
        try (BufferedWriter buffer_writter = new BufferedWriter(new FileWriter(filePath, true))) {
            buffer_writter.newLine();
            buffer_writter.write(word_target + '\t' + word_explain);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Having problem in writing to files");
        } 
    }

    public void editListWord(String word_target, String word_explain_new) {
        for (int i = 0; i < getListWord().size(); i++) {
            if (getListWord().get(i).getWord_target().compareTo(word_target) == 0) {
                getListWord().get(i).setWord_explain(word_explain_new);
                break;
            }
        }
    }

    public void editMapWord(String word_target, String word_explain_new) {
        mapWord.get(word_target).set(FIRST_MEANING, word_explain_new);   
    }

    public void editFileWord(String word_target, String word_explain_new, String filePath) {
        editListWord(word_target, word_explain_new);
        try (BufferedWriter buffer_writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < listWord.size(); i++) {
                buffer_writer.write(listWord.get(i).getWord_target() + '\t' + listWord.get(i).getWord_explain());
                buffer_writer.newLine();
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            System.out.println("Failures on editing file word");
        }
    }

    public void deleteListWord(String word_target) {
        for (int i = 0; i < getListWord().size(); i++) {
            if (getListWord().get(i).getWord_target().equals(word_target)) {
                System.out.println("True");
                getListWord().remove(i);
                i--;
            }
        }
    }

    public void deleteMapWord(String word_target) {
        mapWord.remove(word_target);
    }

    public void deleteFileWord(String word_target, String filePath) {
        deleteListWord(word_target);
        try (BufferedWriter buffer_writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < listWord.size(); i++) {
                buffer_writer.write(listWord.get(i).getWord_target() + '\t' + listWord.get(i).getWord_explain());
                buffer_writer.newLine();
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            System.out.println("Failures on editing file word");
        }
    }
}