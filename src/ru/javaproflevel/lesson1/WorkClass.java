package ru.javaproflevel.lesson1;

public class WorkClass {

    private static Integer testArray1[] = {1, 2, 3, 4, 5};
    private static String testArray2[] = {"a", "b", "c", "d", "e"};

    public static void main(String[] args) {
        for (int i = 0; i < testArray1.length; i++) {
            System.out.print(testArray1[i]);
        }
        System.out.println();
        changeElements(testArray1, 2, 4);
        for (int i = 0; i < testArray1.length; i++) {
            System.out.print(testArray1[i]);
        }
        System.out.println();
        for (int i = 0; i < testArray2.length; i++) {
            System.out.print(testArray2[i]);
        }
        System.out.println();
        changeElements(testArray2, 2, 4);
        for (int i = 0; i < testArray2.length; i++) {
            System.out.print(testArray2[i]);
        }
    }

    private static <T> void changeElements(T[] array, int firstPos, int secondPos) {
        T temp = array[firstPos];
        array[firstPos] = array[secondPos];
        array[secondPos] = temp;
    }
}
