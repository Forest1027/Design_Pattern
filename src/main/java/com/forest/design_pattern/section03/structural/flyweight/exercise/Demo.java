package com.forest.design_pattern.section03.structural.flyweight.exercise;

import java.util.*;

class Sentence {
    private String plaintext;
    private List<WordToken> formatting = new ArrayList<>();

    public Sentence(String plainText) {
        this.plaintext = plainText;
    }

    public WordToken getWord(int index) {
        // todo
        WordToken wordToken = new WordToken();
        wordToken.wordIndex = index;
        formatting.add(wordToken);
        return wordToken;
    }

    @Override
    public String toString() {
        String[] words = plaintext.split(" ");
        String[] newWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (WordToken wordToken : formatting
            ) {
                if (wordToken.wordIndex == i && wordToken.capitalize) {
                    word = word.toUpperCase();
                }
            }
            newWords[i] = word;
        }
        return String.join(" ", newWords);
    }

    class WordToken {
        public boolean capitalize;
        public int wordIndex;
    }
}

public class Demo {
    public static void main(String[] args) {
        var sentence = new Sentence("hello world");
        sentence.getWord(1).capitalize = true;
        System.out.println(sentence);
    }
}
