package org.features;

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

    
}
