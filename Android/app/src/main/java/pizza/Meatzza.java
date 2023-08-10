package pizza;

import java.io.Serializable;

/**
 * Stores Information about a Meatzza Pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class Meatzza extends Pizza implements Serializable {

    private static String pizzaType = "Meatzza";

    /**
     * Constructor for Meatzza Pizza Object
     */
    public Meatzza() {
        super();
        this.addAllToppings();
    }

    /**
     * Constructor for Meatzza Pizza Object
     */
    public Meatzza(Crust crust) {
        super(crust);
        this.addAllToppings();
    }

    /**
     * Adds all the toppings to the pizza
     */
    private void addAllToppings() {
        super.add(new Topping("Sausage"));
        super.add(new Topping("Pepperoni"));
        super.add(new Topping("Beef"));
        super.add(new Topping("Ham"));
    }

    /**
     * Returns the price depending on the pizza size
     * @return 15.99 if Pizza is small, 17.99 if medium, 19.99 if large
     */
    @Override
    public double price() {
        switch (super.getSize()) {
            case SMALL:
                return 15.99;
            case MEDIUM:
                return 17.99;
            case LARGE:
                return 19.99;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {return pizzaType;}
}
