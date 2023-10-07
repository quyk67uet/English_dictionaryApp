package org.features;

import java.util.*;

public class DictionaryCommandline extends DictionaryManagement {
    private static final int COLUMN_NO_WIDTH = 8;
    
    public DictionaryCommandline() {

    }

    public int columnNumberNoWidth() {
        if (Integer.toString(getListWord().size()).length() > COLUMN_NO_WIDTH) {
            return Integer.toString(getListWord().size()).length() + "No".length();
        }
        return COLUMN_NO_WIDTH + "No".length();
    }

    public int columnEnglishWidth() {
        int column_Width = Integer.MIN_VALUE;
        for (int i = 0; i < getListWord().size(); i++) {
            column_Width = Math.max(column_Width, getListWord().get(i).getWord_target().length());
        }
        return column_Width + " English".length();
    }

    public int columnVietnameseWidth() {
        int column_Width = Integer.MIN_VALUE;
        for (int i = 0; i < getListWord().size(); i++) {
            column_Width = Math.max(column_Width, getListWord().get(i).getWord_explain().length());
        }
        return column_Width + " Vietnamese".length();
    }

    public void showMenu() {
        System.out.print("No");
        for (int i = 0; i < columnNumberNoWidth() - "No".length(); i++) {
            System.out.print(" ");
        }

        System.out.print("| English");
        for (int i = 0; i < columnEnglishWidth() - "English".length(); i++) {
            System.out.print(" ");
        }

        System.out.print("| Vietnamese");
        System.out.println();
    }

    public void showAllWords() {
        showMenu();
        for (int i = 0; i < getListWord().size(); i++) {
            System.out.print(Integer.toString(i + 1));
            for (int j = 0; j < columnNumberNoWidth() - Integer.toString(i + 1).length(); j++) {
                System.out.print(" ");
            }
        
            System.out.print("| ");
            System.out.print(getListWord().get(i).getWord_target());
            for (int j = 0; j < columnEnglishWidth() - getListWord().get(i).getWord_target().length(); j++) {
                System.out.print(" ");
            }
            
            System.out.print("| ");
            System.out.print(getListWord().get(i).getWord_explain());
            for (int j = 0; j < columnVietnameseWidth() - getListWord().get(i).getWord_explain().length(); j++) {
                System.out.print(" ");
            }
            
            System.out.println();
        }
    }

    public void dictionaryBasic() {
        super.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() throws Exception {
        boolean userUse = true;
        do {
            try {
                System.out.println();
                System.out.println("-----------------------------------------");
                System.out.println("Welcome to My Application!");
                System.out.println("[0] Exit\n"
                                    + "[1] Add\n"
                                    + "[2] Remove\n"
                + "[3] Update\n"
                + "[4] Display\n"
                + "[5] Lookup\n"
                + "[6] Search\n"
                + "[7] Game\n"
                + "[8] Import from file\n"
                + "[9] Export to file\n");
                System.out.print("Your action: ");

                int userAction = getScanner().nextInt();
                if(userAction == 0) {
                    userUse = false;
                    // System.exit(0);
                } else if (userAction == 1) {
                    addUserWordToList();
                } else if (userAction == 2) {
                    deleteWordFromUser();
                } else if (userAction == 3) {
                    editWordFromUser();
                } else if (userAction == 4) {
                    showAllWords();
                } else if (userAction == 5) {
                    dictionaryLookup();
                } else if (userAction == 6) {
                    dictionarySearcher();
                } else if (userAction == 7) {
                    while (true) {
                        System.out.println("Welcome to the Fantastic Game!");
                        VocabularyQuizGame();
                        System.out.println("Do you want to play again? If you want please press Y to continue... Otherwise press othes to stop the game! ");
                        String action = getScanner().nextLine();
                        if (!action.equals("Y")) {
                            break;
                        }
                    }
                } else if (userAction == 8) {
                    insertFromUserFile();
                } else if (userAction == 9) {
                    dictionaryExportToFile();
                }
                else {
                    System.out.println("Action not supported.");
                    System.out.print("If you want to exit, press Y. Otherwise, press any other key: ");
                    String userException = getScanner().next();
                    if (userException.equals("Y")) {
                        userUse = false;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Wrong input datatype.");
                System.out.print("If you want to exit, press Y. Otherwise, press any other key: ");
                String userException = getScanner().next();
                if (userException.equals("Y")) {
                    userUse = false;
                }
            }   
        } while(userUse);
    }
}
