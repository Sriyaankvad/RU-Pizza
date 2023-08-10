package pizza;

/**
 * Stores Information about a Custom Pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class BuildYourOwn extends Pizza {

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
        double price = switch(super.getSize()) {
            case SMALL -> 8.99;
            case MEDIUM -> 10.99;
            case LARGE -> 12.99;
        };
        price += 1.59 * super.numberOfToppings();

        return Math.round(price * 100.00) / 100.00;
    }

    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {return pizzaType;}
}
