package org.main;

import java.io.IOException;

import org.features.*;

public class App {
    public static void main(String[] args) throws IOException {
        DictionaryCommandline dcl = new DictionaryCommandline();
        DictionaryManagement dm = new DictionaryManagement();
       
        //Scanner sc = new Scanner(System.in);
        System.out.println("*** Test nhap string: ");
        System.out.println("Nhap so luong tu muon nhap: (sau do nhap them cac tu muon nhap)");

        Word word = new Word();
        word.dictionaryExportToFile("english_dictionary/src/main/resources/data.txt");
       /* dm.insertFromFile("english_dictionary/src/main/resources/data.txt");*/
        /*System.out.println(dcl.getList_word().size());
        dcl.showAllWords();*/
        // sc.close();
        word.docDoiTuong("english_dictionary/src/main/resources/data.txt");
        dcl.dictionaryAdvanced();
    }
}