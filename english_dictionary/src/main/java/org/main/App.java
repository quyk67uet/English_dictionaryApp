package org.main;

import java.io.*;
import org.features.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Scene/DictionaryGraphical.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Dictionary");
            primaryStage.setScene(scene);
            primaryStage.show();

            // DictionaryCommandline dcl = new DictionaryCommandline();
            
            //Scanner sc = new Scanner(System.in);
            // dcl.insertFromDefaultFile("english_dictionary/src/main/resources/dictionaries.txt");
            // dcl.showAllWords();
            // System.out.println("----------------");;
            // System.out.println("Test");
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
            // dcl.dictionaryAdvanced();
            // System.out.println(dcl.getList_word().size());
            // dcl.dictionaryBasic();
            // dcl.showAllWords();    
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please re-check the file directory");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Input - output wrong data types");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) throws IOException {
        launch(args);
    }
}