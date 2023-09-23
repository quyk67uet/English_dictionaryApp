package org.features;

import java.util.*;
public class DictionaryManagement extends Dictionary {
    private Scanner scanner;
    public DictionaryManagement() {
        scanner = new Scanner(System.in);
    }

    public void insertFromCommandline() {
        int number_of_words = scanner.nextInt();
        scanner.nextLine();
        while (number_of_words-- > 0) {
            addList_word(scanner.nextLine(), scanner.nextLine());
        }
        scanner.close();
    }
    
}
