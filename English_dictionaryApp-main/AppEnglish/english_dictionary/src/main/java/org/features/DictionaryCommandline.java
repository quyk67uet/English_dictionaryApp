package org.features;

import java.util.ArrayList;
import java.util.List;

public class DictionaryCommandline extends DictionaryManagement {
    private static final int COLUMN_NO_WIDTH = 8;
    // private static final int COLUMN_ENGLISH_WIDTH = 16;
    // private static final int COLUMN_VIETNAMESE_WIDTH = 8;
    public DictionaryCommandline() {

    }

    public int columnNumberNoWidth() {
        if (Integer.toString(getList_word().size()).length() > COLUMN_NO_WIDTH) {
            return Integer.toString(getList_word().size()).length() + "No".length();
        }
        return COLUMN_NO_WIDTH + "No".length();
    }

    public int columnEnglishWidth() {
        int column_Width = Integer.MIN_VALUE;
        for (int i = 0; i < getList_word().size(); i++) {
            column_Width = Math.max(column_Width, getList_word().get(i).getWord_target().length());
        }
        return column_Width + " English".length();
    }

    public int columnVietnameseWidth() {
        int column_Width = Integer.MIN_VALUE;
        for (int i = 0; i < getList_word().size(); i++) {
            column_Width = Math.max(column_Width, getList_word().get(i).getWord_explain().length());
        }
        return column_Width + " Vietnamese".length();
    }

    public void showMenu() {
        System.out.print("No");
        for (int i = 0; i < columnNumberNoWidth() - "No".length(); i++) {
            System.out.print(" ");
        }

        // if (Integer.toString(getList_word().size() + "No".length()).length() > COLUMN_NO_WIDT) {
        //     for (int i = 0; i < getList_word().size(); i++) {
        //         System.out.print(" ");
        //     }
        //     System.out.print("    ");
        // }
        // else
        // {
        //     for (int i = 0; i < COLUMN_NO_WIDTH - "No".length(); i++) {
        //         System.out.print(" ");
        //     }
        // }

        System.out.print("| English");
        for (int i = 0; i < columnEnglishWidth() - "English".length(); i++) {
            System.out.print(" ");
        }

        // if (columnEnglishWidth() > COLUMN_ENGLISH_WIDTH) {
        //     for (int i = 0; i < columnEnglishWidth(); i++) {
        //         System.out.print(" ");
        //     }
        //     System.out.print("    ");
        // }
        // else
        // {
        //     for (int i = 0; i < COLUMN_ENGLISH_WIDTH - 2; i++) {
        //         System.out.print(" ");
        //     }
        // }

        System.out.print("| Vietnamese");
        // if (columnVietnameseWidth() > COLUMN_VIETNAMESE_WIDTH) {
        //     for (int i = 0; i < columnVietnameseWidth(); i++) {
        //         System.out.print(" ");
        //     }
        //     System.out.print("    ");
        // }
        // else
        // {
        //     for (int i = 0; i < COLUMN_ENGLISH_WIDTH - 2; i++) {
        //         System.out.print(" ");
        //     }
        // }
        System.out.println();
    }

    public void showAllWords() {
        showMenu();
        for (int i = 0; i < getList_word().size(); i++) {
            System.out.print(Integer.toString(i + 1));
            // if (Integer.toString(i).length() < columnNumberNoWidth()) {
            for (int j = 0; j < columnNumberNoWidth() - Integer.toString(i + 1).length(); j++) {
                System.out.print(" ");
            }
            // }
            // else
            // {
            //     for (int j = 0; j < Integer.toString(i).length(); j++) {
            //         System.out.print(" ");
            //     }
            //     System.out.print("      ");
            // }
            
            System.out.print("| ");
            System.out.print(getList_word().get(i).getWord_target());
            for (int j = 0; j < columnEnglishWidth() - getList_word().get(i).getWord_target().length(); j++) {
                System.out.print(" ");
            }
            
            System.out.print("| ");
            System.out.print(getList_word().get(i).getWord_explain());
            for (int j = 0; j < columnVietnameseWidth() - getList_word().get(i).getWord_explain().length(); j++) {
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
        String check = "";
        DictionaryCommandline com = new DictionaryCommandline();
        Dictionary dictionary = new Dictionary();
        do {
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
            System.out.println("Your action: ");

            check = this.getScanner().nextLine();


            if(check.equals("0")) {
                System.exit(0);
            } else if (check.equals("1")) {
                System.out.println("Mời bạn nhập từ Tiếng Anh: ");
                String word_target = this.getScanner().nextLine();
                System.out.println("Mời bạn nhập nghĩa Tiếng Việt của từ này: ");
                String word_explain = this.getScanner().nextLine();

                com.addList_word(word_target,word_explain);
                System.out.println("Bộ từ của bạn đã được thêm! ");
            } else if (check.equals("2")) {
                System.out.println("Mời bạn nhập từ Tiếng Anh cần xóa: ");
                String word_target = this.getScanner().nextLine();
                com.deleteList_word(word_target);
                System.out.println("Bộ từ của bạn đã được xóa");

            } else if (check.equals("3")) {
                System.out.println("Mời bạn nhập từ Tiếng Anh cần chỉnh sửa: ");
                String word_target = this.getScanner().nextLine();
                System.out.println("Mời bạn nhập từ Tiếng Việt cần chỉnh sửa: ");
                String word_explain = this.getScanner().nextLine();
                
                com.editList_word(word_target,word_explain);
                System.out.println("Bộ từ của bạn đã được chỉnh sửa!");
            } else if (check.equals("4")) {
                com.showAllWords();
            } else if (check.equals("5")) {
                com.dictionaryLookup();
            } else if (check.equals("6")) {
                System.out.println("Mời bạn nhập từ khóa để thực hiện chức năng tìm kiếm: ");
                String key = this.getScanner().nextLine();
                System.out.println("Đây là tất cả những từ có key mà bạn cần tìm: ");
                com.dictionarySearcher();
            } else if (check.equals("7")) {
                while (true) {
                    System.out.println("Welcome to the Fantastic Game!");
                    com.VocabularyQuizGame();
                    System.out.println("Do you want to play again? If you want please press any key to continue... Else press Y to stop the game! ");
                    String action = getScanner().nextLine();
                    if (action.equals("Y")) {
                        break;
                    }
                }
            } else if (check.equals("8")) {
                com.dictionaryImportFromFile("english_dictionary/src/main/resources/dictionary.txt");
            } else if (check.equals("9")) {

               com.dictionaryExportToFile();
            }
            else {
                System.out.println("Action not supported");
                dictionaryAdvanced();
            }
        }while(check != null);

    }
}
