package ru.javaproflevel.lesson7;

public class ClassWithTests1 {
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

    @Test(priority = 10)
    public void method2(){
        System.out.println("Метод с самым высоким приоритетом");
    }

    @Test()
    public void method3(){
        System.out.println("Метод с приоритетом по умолчанию");
    }

    @Test(priority = 6)
    public void method4(){
        System.out.println("Метод с приоритетом выше, чем приоритет по умолчанию");
    }

    @Test(priority = -1)
    public void method5(){
        System.out.println("Метод с недопустимым приоритетом(-1)");
    }

    @Test(priority = 11)
    public void method6(){
        System.out.println("Метод с недопустимым приоритетом(11)");
    }

}
