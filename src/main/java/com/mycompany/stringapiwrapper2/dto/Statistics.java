package com.mycompany.stringapiwrapper2.dto;

public class Statistics {

    public boolean isWord;
    public boolean isNumber;
    public boolean isLower;
    public boolean isUpper;
    public long characterCount;
    public long letterCount;
    public long digitCount;
    public long lowercaseLetterCount;
    public long uppercaseLetterCount;
    public long whitespaceCount;
    public long specialCharactersCount;

    @Override
    public String toString() {
        return "isWord: " + isWord + "\n" +
                "isNumber: " + isNumber + "\n" +
                "isLower: " + isLower + "\n" +
                "isUpper: " + isUpper + "\n" +
                "characterCount: " + characterCount + "\n" +
                "letterCount: " + letterCount + "\n" +
                "digitCount: " + digitCount + "\n" +
                "lowercaseLetterCount: " + lowercaseLetterCount + "\n" +
                "uppercaseLetterCount: " + uppercaseLetterCount + "\n" +
                "whitespaceCount: " + whitespaceCount + "\n" +
                "specialCharactersCount: " + specialCharactersCount + "\n";
    }
}
