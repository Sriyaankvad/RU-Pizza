package pizza;

import java.io.Serializable;

/**
 * Stores the methods for creating a NY pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class NYPizza implements PizzaFactory, Serializable {

    /**
     * Creates a new NY Style Deluxe Pizza (Brooklyn crust)
     * @return a new NY Style Deluxe Pizza (Brooklyn crust)
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }

    /**
     * Creates a new NY Style Meatzza Pizza (Hand Tossed crust)
     * @return a new NY Style Meatzza Pizza (Hand Tossed crust)
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HAND_TOSSED);
    }

    /**
     * Creates a new NY Style BBQ Chicken Pizza (Thin crust)
     * @return a new NY Style BBQ Chicken Pizza (Thin crust)
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }

    /**
     * Creates a new NY Style Custom Pizza (Hand Tossed crust)
     * @return a new NY Style Custom Pizza (Hand Tossed crust)
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HAND_TOSSED);
    }
}
