package pizza;

/**
 * Interface for creating pizzas
 * Used in ChicagoPizza and NYPizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public interface PizzaFactory {

    /**
     * Creates a new Deluxe Pizza
     * @return a new Deluxe Pizza
     */
    Pizza createDeluxe();

    /**
     * Creates a new Meatzza
     * @return a new Meatzza
     */
    Pizza createMeatzza();

    /**
     * Creates a new BBQ Chicken Pizza
     * @return a new BBQ Chicken Pizza
     */
    Pizza createBBQChicken();

    /**
     * Creates a new Custom Pizza
     * @return a new Custom Pizza
     */
    Pizza createBuildYourOwn();
}
