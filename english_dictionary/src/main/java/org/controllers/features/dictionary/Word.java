package org.features.dictionary;

public class Word {
    
    private String word_target;
    private String word_explain;
    private String word_pronunciation;

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(String word_target, String word_pronunciation, String word_explain) {
        this.word_target = word_target;
        this.word_pronunciation = word_pronunciation;
        this.word_explain = word_explain;
    }
    
    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_pronunciation() {
        return word_pronunciation;
    }

    public void setWord_pronunciation(String word_pronunciation) {
        this.word_pronunciation = word_pronunciation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof org.features.dictionary.Word) {
            if (((Word) obj).getWord_target() == this.getWord_target() && ((Word) obj).getWord_explain() == this.getWord_explain()) 
            {   
                return true;
            }
        }
        return false;
    }

}
