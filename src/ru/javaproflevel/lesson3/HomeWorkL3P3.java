package ru.javaproflevel.lesson3;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class HomeWorkL3P3 {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null && s.length() != 0)
            printPage(Integer.parseInt(s));
        printPage(Integer.parseInt(s));
    }

    public static void printPage(int pageNumber) throws Exception{
        File f = new File("TD3times.txt");
        long n = f.length();
        byte [] bytes = new byte[1800];
        //StringBuilder sb = new StringBuilder();

        RandomAccessFile raf = new RandomAccessFile(f,"rw");
        raf.seek((pageNumber*1800)-1800);
        raf.read(bytes,0,1800);
        /*for (int i = 0; i < 1800; i++) {
           sb.append((char)bytes[i]);
        }*/
        String sb1 = new String(bytes);
        //System.out.println(sb.toString());
        System.out.println(sb1);
    }

}
