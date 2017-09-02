package ru.javaproflevel.lesson3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HomeWorkL3P1and2 {

    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream("file50byte.txt");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();
        System.out.println(Arrays.toString(bytes));

        ArrayList<FileInputStream> arrayList = new ArrayList<>();
        arrayList.add(new FileInputStream("file100byte1.txt"));
        arrayList.add(new FileInputStream("file100byte2.txt"));
        arrayList.add(new FileInputStream("file100byte3.txt"));
        arrayList.add(new FileInputStream("file100byte4.txt"));
        arrayList.add(new FileInputStream("file100byte5.txt"));

        SequenceInputStream sin = new SequenceInputStream(Collections.enumeration(arrayList));
        FileOutputStream out = new FileOutputStream("file500byte.txt");
        int r;
        while ((r = sin.read()) != -1) {
            out.write(r);
        }
        sin.close();
        out.close();
    }
}
