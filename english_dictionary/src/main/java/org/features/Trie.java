package org.features;

public class Trie {
    private static TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // public void setRoot(TrieNode root) {
    //     this.root = root;
    // }

    public TrieNode getRoot() {
        return root;
    }

    public static void insert(String word) {
        TrieNode current = root;
        for (Character x : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(x, curr -> new TrieNode());
        }
        current.setEndOfWord(true);
        // current.setWordMeaning(word);
        System.out.println("Insert succees");
    }

    private static void printWordWithPrefixCore (TrieNode charNode, String word) {
        if (charNode.isEndOfWord()) {
            System.out.println(word);
            return;
        }
        for (Character x : charNode.getChildren().keySet()) {
            if (charNode.getChildren().get(x) != null) {
                printWordWithPrefixCore(charNode.getChildren().get(x), word + x);
            }
        }
    }

    public static void printWordWithPrefix (String word) {
        TrieNode current = root;
        for (Character x : word.toCharArray()) {
            if (!current.getChildren().containsKey(x)) {
                System.out.println("No words with this prefix");
                return;
            }
            current = current.getChildren().get(x);
        }
        printWordWithPrefixCore(current, word);
    }

    public static boolean searchPrefix(String word) {
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

    public static boolean searchWord(String word) {
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

    public static boolean deleteWord (String word) {
        if (!searchWord(word)) {
            // System.out.println("Not found this word to delete");
            return false;
        }
        boolean core = deleteWordCore(root, word, 0);
        return core;
    }

    private static boolean deleteWordCore (TrieNode current, String word, int lengthCount) {
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

    public static boolean editWord(String oldWord, String newWord) {
        if (!searchWord(oldWord)) {
            System.out.println("cant find that word want to edit");
            return false;
        }
        deleteWord(oldWord);
        insert(newWord);
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        Trie.insert("Programming");
        Trie.insert("is");
        Trie.insert("a");
        Trie.insert("way");
        Trie.insert("of");
        Trie.insert("life");
        Trie.insert("Perfexrt");

        searchPrefix("P");
        System.out.println(deleteWord("Programming"));
        searchPrefix("P");
        editWord("Perfexrt", "UwU");
        searchPrefix("P");
        searchPrefix("U");  
    }
}

