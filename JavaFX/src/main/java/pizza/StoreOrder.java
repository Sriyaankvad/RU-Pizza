package pizza;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Class used to keep track of all orders in a store
 * @author Sriyaank Vadlamani, Paul Manayath
 */
public class StoreOrder implements Customizable {

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
     * Export all store orders onto a text file
     */
    public void export()  {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("store_orders.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        for (Order o: this.orders) {
            writer.println("ORDER: " + o.getOrderNum());
            ArrayList<Pizza> pizzas = o.getPizzas();
            for (Pizza p: pizzas) {
                String details = this.getPizzaType(p) + " " +  p.getSize() + ", ";
                details += p.getCrust().toString() + ", Toppings: ";
                for (Topping tp: p.getToppings())
                    details += tp.toString() + ", ";
                details += "$" + p.price();
                writer.println(details);
                writer.println();
            }
            writer.println("Cost of all Pizzas: " + df.format(o.getPizzaCost()));
            writer.println("Sales Tax: " + df.format(o.getSalesTaxCost()));
            writer.println("Total: " + df.format(o.getTotalCost()));
            writer.println("\n");
        }
        writer.close();

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

    /**
     * Gets Type of Pizza given pizza object
     * @param p the pizza in question
     * @return the type of pizza it is
     */
    private String getPizzaType(Pizza p) {
        if (p instanceof BBQChicken)
            return ((BBQChicken) p).getPizzaType();
        if (p instanceof BuildYourOwn)
            return ((BuildYourOwn) p).getPizzaType();
        if (p instanceof Deluxe)
            return ((Deluxe) p).getPizzaType();
        if (p instanceof Meatzza)
            return ((Meatzza) p).getPizzaType();
        return "";
    }

}
