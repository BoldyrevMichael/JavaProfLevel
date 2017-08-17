package ru.javaproflevel.lesson1;

public class Apple extends Fruit {
    private float weight = 1.0f;

    float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Яблоко ";
    }
}
