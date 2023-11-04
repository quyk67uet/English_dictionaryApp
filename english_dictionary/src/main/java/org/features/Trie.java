package org.features;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // public void setRoot(TrieNode root) {
    //     this.root = root;
    // }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        TrieNode current = root;
        for (Character x : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(x, curr -> new TrieNode());
        }
        current.setEndOfWord(true);
        // current.setWordMeaning(word);
        // System.out.println("Insert succees");
    }

    private String printWordWithPrefixCore (TrieNode charNode, String word) {
        StringBuilder result = new StringBuilder();
        if (charNode.isEndOfWord()) {
            result.append(word).append("\n");
        }
        for (Character x : charNode.getChildren().keySet()) {
            if (charNode.getChildren().get(x) != null) {
                String subResult = printWordWithPrefixCore(charNode.getChildren().get(x), word + x);
                result.append(subResult);
            }
        }
        return result.toString();
    }

    public String printWordWithPrefix (String word) {
        TrieNode current = root;
        for (Character x : word.toCharArray()) {
            if (!current.getChildren().containsKey(x)) {
                return "No words with this prefix";
            }
            current = current.getChildren().get(x);
        }
        return printWordWithPrefixCore(current, word);
    }

    public boolean searchPrefix(String word) {
        TrieNode current = root;
        for (Character x : word.toCharArray()) {
            TrieNode charNode = current.getChildren().get(x);
            if (charNode == null) {
                // Nhét hàm kiếm tra event nhập từ
                System.out.println("Cant find any string with that prefix.");
                return false;
            }
            current = charNode;
        }
        // Print những từ có chứa prefix
        printWordWithPrefix(word);
        return true;
    } 

    public boolean searchWord(String word) {
        TrieNode current = root;
        for (Character x : word.toCharArray()) {
            TrieNode charNode = current.getChildren().get(x);
            if (charNode == null) {
                System.out.println("cann find string");
                return false;
            }
            current = charNode;
        }
        return current.isEndOfWord();
    }

    public boolean deleteWord (String word) {
        if (!searchWord(word)) {
            // System.out.println("Not found this word to delete");
            return false;
        }
        boolean core = deleteWordCore(root, word, 0);
        return core;
    }

    private boolean deleteWordCore (TrieNode current, String word, int lengthCount) {
        if (lengthCount == word.length()) {
            if (!current.isEndOfWord()) {
                // System.out.println("at end of word");
                return false;
            }
            current.setEndOfWord(false);
            // System.out.println("at end of word and empty");
            return current.getChildren().isEmpty();

        }
        TrieNode trieNode = current.getChildren().get(word.charAt(lengthCount));
        if (trieNode == null) {
            // System.out.println("null");
            return false;
        }
        if (deleteWordCore(trieNode, word, lengthCount + 1) && !trieNode.isEndOfWord()) {
            // System.out.println("UwU");
            // System.out.println(word.charAt(lengthCount) + " removed");
            current.getChildren().remove(word.charAt(lengthCount));
            // System.out.println("trienode emptu");
            return current.getChildren().isEmpty();
        }
        return true;
    }

    public boolean editWord(String oldWord, String newWord) {
        if (!searchWord(oldWord)) {
            System.out.println("cant find that word want to edit");
            return false;
        }
        deleteWord(oldWord);
        insert(newWord);
        return true;
    }

    public static void main(String[] args) {
        // Trie trie = new Trie();

        // Trie.insert("Programming");
        // Trie.insert("is");
        // Trie.insert("a");
        // Trie.insert("way");
        // Trie.insert("of");
        // Trie.insert("life");
        // Trie.insert("Perfexrt");

        // searchPrefix("P");
        // System.out.println(deleteWord("Programming"));
        // searchPrefix("P");
        // editWord("Perfexrt", "UwU");
        // searchPrefix("P");
        // searchPrefix("U");  
    }
}

