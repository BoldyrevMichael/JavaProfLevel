package ru.javaproflevel.lesson7;

public class ClassWithTests4 {
    @BeforeSuite
    public void doBefore(){
        System.out.println("Метод до тестов");
    }

    @Test(priority = 1)
    public void method1(){
        System.out.println("Метод с самым низким приоритетом");
    }

    @Test(priority = 10)
    public void method2(){
        System.out.println("Метод с самым высоким приоритетом");
    }

    @Test()
    public void method3(){
        System.out.println("Метод с приоритетом по умолчанию");
    }
}
