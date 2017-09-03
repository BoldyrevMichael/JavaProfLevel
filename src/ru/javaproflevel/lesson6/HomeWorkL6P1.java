package ru.javaproflevel.lesson6;

import java.util.Arrays;

public class HomeWorkL6P1 {

    public int[] copyAfter4(int[] array) {
        int[] arrayOut;
        int pos4 = -1;
        for (int i = (array.length - 1); i >= 0; i--) {
            if (array[i] == 4) {
                pos4 = i;
                break;
            }
        }
        if (pos4 == -1) {
            throw new RuntimeException();
        } else {
            arrayOut = new int[array.length - pos4];
            arrayOut = Arrays.copyOfRange(array, pos4 + 1, array.length);
            return arrayOut;
        }
    }
}
