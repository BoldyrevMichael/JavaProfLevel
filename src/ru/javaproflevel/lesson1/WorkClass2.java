package ru.javaproflevel.lesson1;

public class WorkClass2 {

    private static Apple apl = new Apple();
    private static Orange or = new Orange();


    public static void main(String[] args) {

        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();
        Box<Apple> box3 = new Box<>();
        Box<Orange> box4 = new Box<>();

        for (int i = 0; i < 3; i++) {
            box1.addContent(apl);
            box2.addContent(or);
        }
        for (int i = 0; i < 2; i++) {
            box3.addContent(apl);
            box4.addContent(or);
        }
        System.out.println(box1);
        System.out.println(box1.getWeight());
        System.out.println(box2);
        System.out.println(box2.getWeight());
        System.out.println(box3);
        System.out.println(box3.getWeight());
        System.out.println(box4);
        System.out.println(box4.getWeight());
        replaceCont(box1,box3);
        System.out.println(box1);
        System.out.println(box3);
        replaceCont(box2,box4);
        System.out.println(box2);
        System.out.println(box4);

    }

    public static <T extends Fruit> void replaceCont(Box<T> src, Box<T> dst){
        for (T s:src.getContent()) {
            dst.addContent(s);
        }
        src.getContent().clear();
    }

}
