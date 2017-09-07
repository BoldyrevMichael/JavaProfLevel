package ru.javaproflevel.lesson7;

public class ClassWithTests6 {
    @BeforeSuite
    public void doBefore(){
        System.out.println("Метод до тестов");
    }

    @BeforeSuite
    public void doBefore1(){
        System.out.println("Метод до тестов");
    }
}
