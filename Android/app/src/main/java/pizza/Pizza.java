package pizza;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Abstract Class for the Different Kinds of Pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public abstract class Pizza implements Customizable, Serializable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Constructor for Pizza
     */
    public Pizza() {
        this.toppings = new ArrayList<Topping>();
    }

    /**
     * Constructor for Pizza
     * @param toppings list of toppings
     * @param crust the crust for the pizza
     * @param size the size of the pizza
     */
    public Pizza(ArrayList<Topping> toppings, Crust crust, Size size) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    /**
     * Constructor for pizza given only the crust
     * @param crust the crust of the pizza
     */
    public Pizza(Crust crust) {
        this();
        this.crust = crust;
    }

    /**
     * Adds a topping to the pizza if there are less than 7 toppings already on the pizza
     * @param obj the topping to be added
     * @return true if the topping is added successfully, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Topping) {
            if(this.numberOfToppings() < 7) {
                this.toppings.add((Topping) obj);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a topping from the pizza
     * @param obj the topping to be removed
     * @return true if the topping is removed successfully, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Topping) {
            this.toppings.remove((Topping) obj);
            return true;
        }
        return false;
    }

    /**
     * Returns all information about the pizza
     * @return All information about the pizza
     */
    public String toString() {
        String s = "-Toppings on the Pizza-\n";
        for (Topping topping : this.toppings) {
            s += topping + "\n";
        }
        return s + "\nCrust Used: " + this.crust
                + "\n\nSize of Pizza: " + this.size
                + "\n\nPrice of Pizza: $" + this.price();
    }

    /**
     * Returns the number of toppings on the pizza
     * @return the number of toppings on the pizza
     */
    public int numberOfToppings() {
        return this.toppings.size();
    }

    /**
     * Returns the price of the pizza
     * @return the price of the pizza
     */
    public abstract double price();

    /**
     * Returns the array list containing all the toppings
     * @return the array list containing all the toppings
     */
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    /**
     * Sets the array list containing all the toppings
     * @param toppings the array list containing all the toppings
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Returns the crust of the pizza
     * @return the crust of the pizza
     */
    public Crust getCrust() {
        return this.crust;
    }

    /**
     * Sets the crust of the pizza
     * @param crust the crust of the pizza
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Returns the size of the pizza
     * @return the size of the pizza
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * Sets the size of the pizza
     * @param size the size of the pizza
     */
    public void setSize(String size) {
        switch (size.toLowerCase()) {
            case "small":
                this.size = Size.SMALL;
                break;
            case "medium":
                this.size = Size.MEDIUM;
                break;
            case "large":
                this.size = Size.LARGE;
                break;
        }
    }
}