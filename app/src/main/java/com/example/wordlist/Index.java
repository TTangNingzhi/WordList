package com.example.wordlist;

public class Index {

    public static int[] indexes = new int[]{0, 0, 0, 0, 0};

    public static void setIndex(int listNumber, int index) {
        indexes[listNumber] = index;
    }

    public static int getIndex(int listNumber) {
        return indexes[listNumber];
    }

}
