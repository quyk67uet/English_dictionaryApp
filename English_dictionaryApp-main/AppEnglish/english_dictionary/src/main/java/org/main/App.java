package org.main;

import java.io.IOException;

import org.features.*;

public class App {
    public static void main(String[] args) throws IOException {
        DictionaryCommandline dcl = new DictionaryCommandline();
       
        //Scanner sc = new Scanner(System.in);
        System.out.println("*** Test nhap string: ");
        System.out.println("Nhap so luong tu muon nhap: (sau do nhap them cac tu muon nhap)");
        
        dcl.insertFromFile("english_dictionary/src/main/resources/dictionaries.txt");
        System.out.println(dcl.getList_word().size());
        dcl.showAllWords();
        // sc.close();
    }
}