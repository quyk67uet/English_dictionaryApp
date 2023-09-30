package org.features;

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
}
