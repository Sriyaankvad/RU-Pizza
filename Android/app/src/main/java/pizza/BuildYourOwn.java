package pizza;

import java.io.Serializable;

/**
 * Stores Information about a Custom Pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class BuildYourOwn extends Pizza implements Serializable {

    private static String pizzaType = "BuildYourOwn";

    /**
     * Constructor for BuildYourOwn object
     */
    public BuildYourOwn() {
        super();
    }

    /**
     * Constructor for BuildYourOwn object
     */
    public BuildYourOwn(Crust crust) {
        super(crust);
    }

    /**
     * Returns the price depending on the pizza size and number of toppings
     * @return 1.59 per topping + 8.99 if Pizza is small, 10.99 if medium, 12.99 if large
     */
    @Override
    public double price() {
        double price;
        switch (super.getSize()) {
            case SMALL:
                price = 8.99;
                break;
            case MEDIUM:
                price = 10.99;
                break;
            case LARGE:
                price = 12.99;
                break;
            default:
                throw new IllegalArgumentException();
        }
        price += 1.59 * super.numberOfToppings();

        return Math.round(price * 100.00) / 100.00;
    }

    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {return pizzaType;}
}
