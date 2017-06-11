package es.kronox.foodinggest.model;

import java.util.LinkedList;


public class Dishes {


    // Singleton de platos (instancio y expongo con método getInstance)


    private static Dishes mInstance;

    private static LinkedList<Dish> mDishes;

    public static Dishes getInstance() {
        if (mInstance == null) {
            mInstance = new Dishes();
        }

        return mInstance;
    }

    // Inicializamos dish con LinkedList vacía
    public Dishes() {
        mDishes = new LinkedList<>();
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public Dish getDish(int index) {
        return mDishes.get(index);
    }

    public int getCount() {
        return mDishes.size();
    }


    public void add(Dish dish) {
        mDishes.add(dish);
    }
}








