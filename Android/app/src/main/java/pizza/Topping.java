package pizza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for implementation of Pizza Toppings
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class Topping implements Serializable {

    private static final String[] TOPPINGS = {"Sausage",
            "Pepperoni", "Green Pepper", "Onion", "Mushroom",
            "BBQ Chicken", "Provolone", "Cheddar", "Beef", "Ham", "Jalapeno", "Olive", "Spinach"};

    private String topping;

    public Topping(String topping) {
        if (isValidTopping(topping))
            this.topping = topping;
    }

    /**
     * Checks if the specified topping is one of the available toppings
     * @param topping the topping to check if available
     * @return true if the topping is available, false otherwise
     */
    public static boolean isValidTopping(String topping) {
        for (String top : TOPPINGS) {
            if (top.equalsIgnoreCase(topping))
                return true;
        }
        return false;
    }

    /**
     * Returns the topping stored
     *      * @return the topping stored
     */
    public String toString() {
        return topping;
    }

    /**
     * Lists all available toppings
     * @return all available toppings
     */
    public static String listAllAvailableTopppings() {
        String s = "-List of All Available Toppings-\n";
        for (String topping : TOPPINGS) {
            s += topping + "\n";
        }
        return s + "-end of list-";
    }

    /**
     * Returns toppings as ArrayList
     * @return toppings as ArrayList
     */
    public static ArrayList<String> getToppings() {
        ArrayList<String> toppings = new ArrayList<String>();
        for (String topping : TOPPINGS) {
            toppings.add(topping);
        }
        return toppings;
    }

    /**
     * Checks if two different toppings are equal
     * @param obj the topping to check
     * @return true if both are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Topping) {
            return this.topping.equalsIgnoreCase(((Topping) obj).topping);
        }
        return false;
    }
}
