package com.sandeepdolia.poem.rule;

public enum PoemKeywords {
    $END("\r"), $LINEBREAK("\n");

    private String value;

    private PoemKeywords(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}	