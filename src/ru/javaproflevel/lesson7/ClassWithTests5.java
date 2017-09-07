package ru.javaproflevel.lesson7;

public class ClassWithTests5 {
    @BeforeSuite
    public void doBefore(){
        System.out.println("Метод до тестов");
    }

    @AfterSuite
    public void doAfter(){
        System.out.println("Метод после тестов");
    }

    @Test(priority = 1)
    public void method1(){
        System.out.println("Метод с самым низким приоритетом");
    }

    @Test(priority = 1)
    public void method2(){
        System.out.println("Метод с самым низким приоритетом");
    }

    @Test()
    public void method3(){
        System.out.println("Метод с приоритетом по умолчанию");
    }

    @Test()
    public void method4(){
        System.out.println("Метод с приоритетом по умолчанию");
    }
}
