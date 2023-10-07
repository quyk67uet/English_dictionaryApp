package org.main;

import java.io.*;

import org.features.*;

public class App {
    public static void main(String[] args) throws IOException {
        try {
            DictionaryCommandline dcl = new DictionaryCommandline();
       
            //Scanner sc = new Scanner(System.in);
            System.out.println("*** Test nhap string: ");
            System.out.println("Nhap so luong tu muon nhap: (sau do nhap them cac tu muon nhap)");
            
            dcl.insertFromDefaultFile("english_dictionary/src/main/resources/dictionaries.txt");
            // dcl.showAllWords();
            System.out.println("----------------");;
            System.out.println("Test");
            // dcl.dictionaryLookup();
            // dcl.addUserWordToList();
            // dcl.insertFromCommandline();
            // for (int i = 0; i < dcl.getListWord().size(); i++) {
            //     System.out.print(dcl.getListWord().get(i).getWord_target() + " " + dcl.getListWord().get(i).getWord_explain());
            //     System.out.println();
            // }
            // dcl.editWordFromUser();
            // dcl.dictionarySearcher();
            // dcl.dictionaryExportToFile();
            // dcl.deleteWordFromUser();
            // dcl.dictionaryLookup();
            // dcl.editWordFromUser();                                  -- Không nhập được tiếng Việt
            dcl.dictionaryAdvanced();
            // System.out.println(dcl.getList_word().size());
            // dcl.dictionaryBasic();
            dcl.showAllWords();    
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exiting...");
        }
        

    }
}