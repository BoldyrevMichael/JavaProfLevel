package ru.javaproflevel.lesson6;

public class HomeWorkL6P2 {

    public boolean checkArrayFor1and4(int[] array) {
        boolean f = true;
        boolean one = false;
        boolean four = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 1 && array[i] != 4) {
                f = false;
                break;
            } else if (array[i] == 1) {
                one = true;
            } else if (array[i] == 4) {
                four = true;
            }
            f = one & four;
        }
        return f;
    }
}
