package es.kronox.foodinggest.model;

import java.util.LinkedList;


public class Table {


    private String mName;
    private LinkedList<Dish> mDishes;

    public Table(String name, LinkedList<Dish> tables) {
        mName = name;
        mDishes = tables;
    }

    public Table(String name) {
        mName = name;
        mDishes = new LinkedList<>();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }


    @Override
    public String toString() {
        return getName();
    }
}
