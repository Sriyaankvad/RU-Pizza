package pizza;

/**
 * Stores Information about a Meatzza Pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class Meatzza extends Pizza {

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
        return switch(super.getSize()) {
            case SMALL -> 15.99;
            case MEDIUM -> 17.99;
            case LARGE -> 19.99;
        };
    }

    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {return pizzaType;}
}
