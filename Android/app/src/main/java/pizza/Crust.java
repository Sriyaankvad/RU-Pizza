package pizza;

import java.io.Serializable;

/**
 * Enum to Store Information about the Pizza Crust
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public enum Crust implements Serializable {

    DEEP_DISH("Deep Dish"), PAN("Pan"), STUFFED("Stuffed"),
    BROOKLYN("Brooklyn"), THIN("Thin"), HAND_TOSSED("Hand Tossed");

    private String crust;

    /**
     * Constructor for crust
     * @param crust the crust that will be used
     */
    Crust(String crust) {
        this.crust = crust;
    }

    /**
     * Returns the crust used
     * @return the crust used
     */
    public String toString() {
        return this.crust;
    }

}
