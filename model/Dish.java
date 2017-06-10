package es.kronox.foodinggest.model;

import java.io.Serializable;

public class Dish implements Serializable {

    private String mName;
    private String mIngredients;
    private Double mPrice;
    private String mImageName;
    private String mVariants;
    // private LinkedList<Allergens>


    // Constructor

    public Dish(String name, String ingredients, Double price, String imageName, String variants) {
        mName = name;
        mIngredients = ingredients;
        mPrice = price;
        mImageName = imageName;
        mVariants = variants;
    }


    // Getter

    public String getName() {
        return mName;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public Double getPrice() {
        return mPrice;
    }

    public String getImageName() {
        return mImageName;
    }

    public String getVariants() {
        return mVariants;
    }



    // Setters

    public void setVariants(String variants) {
        mVariants = variants;
    }



}
