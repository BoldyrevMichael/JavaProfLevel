package ru.javaproflevel.lesson7;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class TestClass {

    public static void main(String[] args) throws Exception {
        makeTest("ru.javaproflevel.lesson7.ClassWithTests1");
        System.out.println();
        Thread.sleep(50);
        makeTest("ru.javaproflevel.lesson7.ClassWithTests2");
        System.out.println();
        Thread.sleep(50);
        makeTest("ru.javaproflevel.lesson7.ClassWithTests3");
        System.out.println();
        Thread.sleep(50);
        makeTest("ru.javaproflevel.lesson7.ClassWithTests4");
        System.out.println();
        Thread.sleep(50);
        makeTest("ru.javaproflevel.lesson7.ClassWithTests5");
        System.out.println();
        Thread.sleep(50);
        makeTest("ru.javaproflevel.lesson7.ClassWithTests6");
    }

    public static void makeTest(String className) throws Exception {

        Class cl = Class.forName(className);
        Method[] declaredMethods = cl.getDeclaredMethods();
        Constructor c = cl.getConstructor();

        int countBefore = 0;
        int countAfter = 0;
        ArrayList<TestMeths> arrayList = new ArrayList<>();

        for (Method ms : declaredMethods) {
            if (ms.isAnnotationPresent(BeforeSuite.class)) {
                countBefore++;
            }
            if (ms.isAnnotationPresent(AfterSuite.class)) {
                countAfter++;
            }
            if (countBefore > 1 || countAfter > 1) {
                throw new RuntimeException("Недопустимое количество методов до начала или после завершения тестов!");
            }
        }

        for (Method ms : declaredMethods) {
            if (ms.isAnnotationPresent(Test.class)) {
                if (ms.getAnnotation(Test.class).priority() > 10 || ms.getAnnotation(Test.class).priority() < 1)
                    continue;
            }
            arrayList.add(new TestMeths(ms));
        }

        Collections.sort(arrayList);

        switch (className) {
            case "ru.javaproflevel.lesson7.ClassWithTests1":
                ClassWithTests1 classWithTests1 = (ClassWithTests1) c.newInstance();
                for (TestMeths tm : arrayList) {
                    tm.getM().invoke(classWithTests1);
                }
                break;
            case "ru.javaproflevel.lesson7.ClassWithTests2":
                ClassWithTests2 classWithTests2 = (ClassWithTests2) c.newInstance();
                for (TestMeths tm : arrayList) {
                    tm.getM().invoke(classWithTests2);
                }
                break;
            case "ru.javaproflevel.lesson7.ClassWithTests3":
                ClassWithTests3 classWithTests3 = (ClassWithTests3) c.newInstance();
                for (TestMeths tm : arrayList) {
                    tm.getM().invoke(classWithTests3);
                }
                break;
            case "ru.javaproflevel.lesson7.ClassWithTests4":
                ClassWithTests4 classWithTests4 = (ClassWithTests4) c.newInstance();
                for (TestMeths tm : arrayList) {
                    tm.getM().invoke(classWithTests4);
                }
                break;
            case "ru.javaproflevel.lesson7.ClassWithTests5":
                ClassWithTests5 classWithTests5 = (ClassWithTests5) c.newInstance();
                for (TestMeths tm : arrayList) {
                    tm.getM().invoke(classWithTests5);
                }
                break;
            case "ru.javaproflevel.lesson7.ClassWithTests6":
                ClassWithTests6 classWithTests6 = (ClassWithTests6) c.newInstance();
                for (TestMeths tm : arrayList) {
                    tm.getM().invoke(classWithTests6);
                }
                break;
            default:
                break;
        }
    }

    private static class TestMeths implements Comparable<TestMeths> {
        Method m;

        TestMeths(Method m) {
            this.m = m;
        }

        public Method getM() {
            return m;
        }

        @Override
        public int compareTo(TestMeths o) {
            int res = 0;
            if (m.isAnnotationPresent(Test.class) && o.m.isAnnotationPresent(Test.class)) {
                res = -(m.getAnnotation(Test.class).priority() - o.m.getAnnotation(Test.class).priority());
            } else if (m.isAnnotationPresent(Test.class) && o.m.isAnnotationPresent(BeforeSuite.class)) {
                res = 100;
            } else if (m.isAnnotationPresent(Test.class) && o.m.isAnnotationPresent(AfterSuite.class)) {
                res = -100;
            } else if (m.isAnnotationPresent(BeforeSuite.class) && o.m.isAnnotationPresent(Test.class)) {
                res = -100;
            } else if (m.isAnnotationPresent(AfterSuite.class) && o.m.isAnnotationPresent(Test.class)) {
                res = 100;
            } else if (m.isAnnotationPresent(AfterSuite.class) && o.m.isAnnotationPresent(BeforeSuite.class)) {
                res = 100;
            } else if (m.isAnnotationPresent(BeforeSuite.class) && o.m.isAnnotationPresent(AfterSuite.class)) {
                res = -100;
            }
            return res;
        }
    }
}
