package ru.javaproflevel.lesson4;

public class Mfu {
    Object obj1 = new Object();
    Object obj2 = new Object();

    public static void main(String[] args) {
        Mfu m = new Mfu();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t1 = new Thread(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m.print(finalI + 1);
            });
            t1.start();

            Thread t2 = new Thread(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m.scun(finalI + 1);
            });
            t2.start();
        }


    }

    public void print(int page) {
        synchronized (obj1) {
            System.out.println("Отпечатано " + page + " страниц");
        }
    }

    public void scun(int page) {
        synchronized (obj2) {
            System.out.println("Отсканировано " + page + " страниц");
        }
    }

}
