package pizza;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Class used to keep track of all orders in a store
 * @author Sriyaank Vadlamani, Paul Manayath
 */
public class StoreOrder implements Customizable, Serializable {

    private static final DecimalFormat df = new DecimalFormat( "#.00" );
    private static int totalOrders;
    private ArrayList<Order> orders;

    /**
     * Constructor for the store order object
     */
    public StoreOrder(){
        this.orders = new ArrayList<Order>();
    }

    /**
     * Attempts to add an object into the list of orders
     * @param obj the object to be added into the order arraylist
     * @return true if the order was successfully added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order) {
            return this.orders.add((Order) obj);
        }
        return false;
    }

    /**
     * Attempts to remove an object into the list of orders
     * @param obj the object to be removed from the list of order
     * @return true if the remove operation was successful, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            return this.orders.remove((Order) obj);
        }
        return false;
    }

    /**
     * Generates a very unique number for an order
     * @return a very unique integer
     */
    public static int generateOrderNum() {return totalOrders++;}

    /**
     * Returns an arraylist of all the orders
     * @return arraylist of all the orders
     */
    public ArrayList<Order> getOrders() {return this.orders;}

}
