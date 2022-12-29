package net.harmoniamc.harmonia.Utils;

import java.util.Random;

public class AlphaNumericStringGenerator {

    private static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int RANDOM_STRING_LENGTH = 6;

    public String randomString() {
        StringBuffer randomString = new StringBuffer();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randomString.append(ch);
        }
        return randomString.toString();
    }

    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    public String generateAlphaNumString(){
        AlphaNumericStringGenerator num = new AlphaNumericStringGenerator();
        return num.toString();
    }
}
