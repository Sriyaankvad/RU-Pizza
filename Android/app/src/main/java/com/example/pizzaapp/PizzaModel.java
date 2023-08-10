package com.example.pizzaapp;

/**
 * Creates a model for the pizza containing the pizza type and pizza style
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class PizzaModel {
    private String pizzaType;
    private String pizzaStyle;
    private int image;

    public PizzaModel(String pizzaType, String pizzaStyle, int image) {
        this.pizzaType = pizzaType;
        this.pizzaStyle = pizzaStyle;
        this.image = image;
    }

    /**
     * Returns the pizza type (Deluxe, BBQ Chicken, Meatzza, or Build Your Own)
     * @return the pizza type
     */
    public String getPizzaType() {
        return this.pizzaType;
    }

    /**
     * Returns the pizza style (Chicago Style or New York Style)
     * @return the pizza style
     */
    public String getPizzaStyle() {
        return this.pizzaStyle;
    }

    /**
     * Returns the id of the image
     * @return the id of the image
     */
    public int getImage() {
        return this.image;
    }
}
