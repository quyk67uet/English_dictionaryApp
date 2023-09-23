package org.features;

import java.util.*;

public class Dictionary {
    private List<Word> list_word;

    public Dictionary() {
        list_word = new ArrayList<Word>();
    }

    public Dictionary(List<Word> list_word) {
        this.list_word = list_word;
    }

    public List<Word> getList_word() {
        return list_word;
    }

    public void setList_word(List<Word> list_word) {
        this.list_word = list_word;
    }
    
    public void addList_word(String word_target, String word_explain) {
        list_word.add(new Word(word_target, word_explain));
    }
}