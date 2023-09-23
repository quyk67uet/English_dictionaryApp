package org.main;

import org.features.*;

public class App {
    public static void main(String[] args) {
        DictionaryCommandline dcl = new DictionaryCommandline();
       
        //Scanner sc = new Scanner(System.in);
        System.out.println("*** Test nhap string: ");
        System.out.println("Nhap so luong tu muon nhap: (sau do nhap them cac tu muon nhap)");
        dcl.insertFromCommandline();
        
        dcl.showAllWords();
        // sc.close();
    }
}