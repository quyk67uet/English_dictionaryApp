package org.features.dictionary;

import java.util.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

}
