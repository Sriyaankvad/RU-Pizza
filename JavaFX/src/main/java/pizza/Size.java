package pizza;

/**
 * Enum to Store Information about the Pizza Crust
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public enum Size {

    SMALL("Small"), MEDIUM("Medium"), LARGE("Large");

    private String size;

    /**
     * Constructor for size given as a string
     * @param size size of pizza as a string
     */
    Size(String size) {
        this.size = size;
    }

    /**
     * Returns the size of the pizza
     * @return the size of the pizza
     */
    public String toString() {
        return this.size;
    }

}
