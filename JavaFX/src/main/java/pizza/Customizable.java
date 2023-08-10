package pizza;

/**
 * Interface for adding and removing objects
 * Used in Pizza, Order, and StoreOrder
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public interface Customizable {

    /**
     * Adds an object
     * @param obj the object to be added
     * @return true if object is added, false otherwise
     */
    boolean add(Object obj);

    /**
     * Removes an object
     * @param obj the object to be removes
     * @return true if object is removed, false otherwise
     */
    boolean remove(Object obj);
}
