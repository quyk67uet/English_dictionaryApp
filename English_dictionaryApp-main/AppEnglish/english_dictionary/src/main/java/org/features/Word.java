package org.features;

import javafx.concurrent.Worker;

import java.io.*;

public class Word implements Serializable{
    private String word_target;
    private String word_explain;

    public Word() {
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
    
    public String getWord_target() {
        return word_target;
    }
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }
    public String getWord_explain() {
        return word_explain;
    }
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof org.features.Word) {
            if (((Word) obj).getWord_target() == this.getWord_target() && ((Word) obj).getWord_explain() == this.getWord_explain()) 
            {   
                return true;
            }
        }
        return false;
    }

    public void dictionaryExportToFile(String filePath) {
        File file = new File(filePath) ;
        try {
            OutputStream os = new FileOutputStream(file) ;
            ObjectOutputStream oos = new ObjectOutputStream(os) ;

            Word newWord = new Word("football", "bong da");

            oos.writeObject(newWord);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void docDoiTuong(String filePath) {
        try {
            File file = new File(filePath) ;
            InputStream is = new FileInputStream(file) ;
            ObjectInputStream ois = new ObjectInputStream(is) ;

            Word newWord = (Word) ois.readObject();

            Dictionary d = new Dictionary();
            d.getList_word().add(newWord);
            System.out.println(newWord);
            System.out.println(d.getList_word());
            ois.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
