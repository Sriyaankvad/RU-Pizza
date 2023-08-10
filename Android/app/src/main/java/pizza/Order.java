package pizza;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class used to keep track of a customer's order
 * @author Sriyaank Vadlamani, Paul Manayath
 */
public class Order implements Customizable, Serializable {

    private int orderNum;
    private double pizzaCost;
    private ArrayList<Pizza> pizzas;

    /**
     * Constructor for the order object
     */
    public Order() {
        this.orderNum = StoreOrder.generateOrderNum();
        this.pizzas = new ArrayList<Pizza>();
    }

    /**
     * Attempts to add an object into the list of pizzas.
     * Updates cost of the order
     * @param obj the object to be added into the pizza arraylist
     * @return true if the pizza was successfully added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza) {
            if (this.pizzas.add((Pizza) obj)) {
                this.pizzaCost += ((Pizza) obj).price();
                return true;
            }
        }
        return false;
    }

    /**
     * Attempts to remove an object into the list of pizzas
     * Updates cost of the order
     * @param obj the object to be removed from the list of pizzas
     * @return true if the remove operation was successful, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza) {
            if (this.pizzas.remove((Pizza) obj)) {
                this.pizzaCost -= ((Pizza) obj).price();
                return true;
            }
        }
        return false;
    }

    /**
     * Return the order number of the order
     * @return order number of the order
     */
    public int getOrderNum() {return this.orderNum;}

    /**
     * Returns cost of all the pizzas in the order
     * @return cost of all the pizzas in the order
     */
    public double getPizzaCost() {return this.pizzaCost;}

    /**
     * Return arraylist of pizzas in the order
     * @return arraylist of pizzas in the order
     */
    public ArrayList<Pizza> getPizzas() {return this.pizzas;}

    /**
     * Returns sales tax for the order
     * @return sales tax for the order
     */
    public double getSalesTaxCost() {return (this.pizzaCost * 0.0625);}

    /**
     * Returns total cost of order with sales tax
     * @return total cost of order with sales tax
     */
    public double getTotalCost() {return this.pizzaCost + this.getSalesTaxCost();}

    /**
     * Clears the entire order
     */
    public void clearOrder() {
        this.pizzas.clear();
        this.pizzaCost = 0;
    }
}
