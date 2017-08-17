package ru.javaproflevel.lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    private ArrayList<T> content = new ArrayList<>();
    private float weightOfBox;

    public ArrayList<T> getContent(){
        return content;
    }

    public void addContent(T e){
        content.add(e);
    }

    public float getWeight() {
        for (T each :
                content) {
            weightOfBox = weightOfBox + each.getWeight();
        }
        return weightOfBox;
    }

    public boolean compare(Box<?> another) {
        return Math.abs(getWeight() - another.getWeight()) < 0.0001f;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T each :
                content) {
            str.append(each.toString());
        }
        return str.toString();
    }
}
