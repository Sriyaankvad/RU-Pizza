package pizza;

/**
 * Stores the methods for creating a chicago pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a new Chicago Style Deluxe Pizza (Deep Dish crust)
     * @return a new Chicago Style Deluxe Pizza (Deep Dish crust)
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEP_DISH);
    }

    /**
     * Creates a new Chicago Style Meatzza Pizza (Stuffed crust)
     * @return a new Chicago Style Meatzza Pizza (Stuffed crust)
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * Creates a new Chicago Style BBQ Chicken Pizza (Pan crust)
     * @return a new Chicago Style BBQ Chicken Pizza (Pan crust)
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN);
    }

    /**
     * Creates a new Chicago Style Custom Pizza (Pan crust)
     * @return a new Chicago Style Custom Pizza (Pan crust)
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN);
    }
}
