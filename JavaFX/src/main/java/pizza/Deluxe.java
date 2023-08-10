package pizza;

/**
 * Stores Information about a Deluxe Pizza
 * addAllToppings
 */
public class Deluxe extends Pizza {

    private static String pizzaType = "Deluxe";

    /**
     * Constructor for Deluxe Pizza Object
     */
    public Deluxe() {
        super();
        this.addAllTopings();
    }

    /**
     * Constructor for Deluxe Pizza Object
     */
    public Deluxe(Crust crust) {
        super(crust);
        this.addAllTopings();
    }

    /**
     * Adds all the toppings to the pizza
     */
    private void addAllTopings() {
        super.add(new Topping("Sausage"));
        super.add(new Topping("Pepperoni"));
        super.add(new Topping("Green Pepper"));
        super.add(new Topping("Onion"));
        super.add(new Topping("Mushroom"));
    }

    /**
     * Returns the price depending on the pizza size
     * @return 14.99 if Pizza is small, 16.99 if medium, 18.99 if large
     */
    @Override
    public double price() {
        return switch(super.getSize()) {
            case SMALL -> 14.99;
            case MEDIUM -> 16.99;
            case LARGE -> 18.99;
        };
    }

    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {return pizzaType;}
}
