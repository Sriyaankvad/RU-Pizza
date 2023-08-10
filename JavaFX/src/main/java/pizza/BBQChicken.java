package pizza;

/**
 * Stores Information about a BBQ Chicken Pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class BBQChicken extends Pizza {

    private static String pizzaType = "BBQChicken";
    /**
     * Constructor for BBQChicken Object
     */
    public BBQChicken() {
        super();
        this.addAllToppings();
    }

    /**
     * Constructor for BBQChicken Object
     */
    public BBQChicken(Crust crust) {
        super(crust);
        this.addAllToppings();
    }

    /**
     * Adds all the toppings to the pizza
     */
    private void addAllToppings() {
        super.add(new Topping("BBQ Chicken"));
        super.add(new Topping("Green Pepper"));
        super.add(new Topping("Provolone"));
        super.add(new Topping("Cheddar"));
    }

    /**
     * Returns the price depending on the pizza size
     * @return 13.99 if Pizza is small, 15.99 if medium, 17.99 if large
     */
    @Override
    public double price() {
        return switch(super.getSize()) {
            case SMALL -> 13.99;
            case MEDIUM -> 15.99;
            case LARGE -> 17.99;
        };
    }

    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {return pizzaType;}
}
