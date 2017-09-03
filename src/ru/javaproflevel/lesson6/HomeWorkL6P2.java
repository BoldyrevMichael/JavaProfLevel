package ru.javaproflevel.lesson6;

public class HomeWorkL6P2 {

    public boolean checkArrayFor1and4(int[] array) {
        boolean f = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 1 && array[i] != 4) {
                f = false;
                break;
            }
        }
        return f;
    }
}
