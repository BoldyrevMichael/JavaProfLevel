package ru.javaproflevel.lesson4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeWorkL4P2 {

    private File file = new File("D:\\GeekBrains\\Java Core Профессиональный уровень\\JavaProfLevel\\text.txt");
    static String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String s = "";

    public HomeWorkL4P2() {
        try (FileOutputStream fos = new FileOutputStream(file)) {

            byte[] bytesToWrite = s.getBytes();
            fos.write(bytesToWrite);
        } catch (IOException ex) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    public static void main(String[] args) {

        HomeWorkL4P2 w = new HomeWorkL4P2();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                w.wrightFile(" 1п-" + str[i]);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                w.wrightFile(" 2п-" + str[i]);
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                w.wrightFile(" 3п-" + str[i]);
            }
        });
        t1.start();
        t2.start();
        t3.start();

    }

    synchronized void wrightFile(String text) {
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            byte[] bytesToWrite = text.getBytes();
            fos.write(bytesToWrite);
        } catch (IOException ex) {
            System.out.println("Ошибка ввода-вывода");
        }
    }


}
