package ru.javaproflevel.lesson3;

import java.io.FileInputStream;
import java.util.Arrays;

public class HomeWorkL3P1and2 {

    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream("file50byte.txt");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        System.out.println(Arrays.toString(bytes));

    }
}
