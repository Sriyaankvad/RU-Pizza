package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pizza.*;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Controller Class for the current order
 * @author Sriyaank Vadlamani, Paul Manayath
 */
public class CurrentOrderController {

    private static final DecimalFormat df = new DecimalFormat( "#.00" );

    private static StoreOrder storeOrder;

    private static Order currOrder;

    @FXML
    private TextField orderNumber;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField orderTotal;

    @FXML
    private ListView listView;

    /**
     * initialize current order scene
     */
    @FXML
    public void initialize() {
        this.disableAll();
        if (currOrder == null || currOrder.getPizzas().size() == 0)
            return;
        this.updateDetails();
        this.showPizzas();
    }

    /**
     * Clears order by removing all pizzas
     * Updates scene to reflect this
     */
    @FXML
    public void clearOrder() {
        currOrder.clearOrder();
        this.updateDetails();
        this.listView.getItems().clear();
    }

    /**
     * Removes selected pizza from the order
     * Updates scene to reflect this
     */
    @FXML
    public void removePizza() {
        if (currOrder == null || currOrder.getPizzas().size() == 0)
            return;
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index < 0)
            return;
        this.listView.getItems().remove(index);
        if (currOrder.getPizzas().size() <= index)
            return;
        Pizza toRemove = currOrder.getPizzas().get(index);
        currOrder.remove(toRemove);
        this.updateDetails();
    }

    /**
     * Used to update the details of the textfields/areas in this scene
     */
    private void updateDetails() {
        this.orderNumber.setText(String.valueOf(currOrder.getOrderNum()));
        this.subtotal.setText(df.format(currOrder.getPizzaCost()));
        this.salesTax.setText(df.format(currOrder.getSalesTaxCost()));
        this.orderTotal.setText(df.format(currOrder.getTotalCost()));
    }

    /**
     * Displays all pizzas in list view
     */
    private void showPizzas() {
        if (currOrder == null || currOrder.getPizzas().size() == 0)
            return;
        ObservableList<String> obl = FXCollections.observableArrayList();
        for (Pizza p: currOrder.getPizzas()) {
            String details = this.getPizzaType(p) + " " + p.getSize() + ", ";
            details += p.getCrust().toString() + ", Toppings: ";
            for (Topping tp: p.getToppings())
                details += tp.toString() + ", ";
            details += "$" + p.price();
            obl.add(details);
        }
        listView.setItems(obl);

    }

    /**
     * Adds the current order into the store order
     * Clears out listview and text
     * Creates new current order and "syncs" it with ny and chicago controllers
     */
    @FXML
    public void placeOrder() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Status");
        if (currOrder == null || currOrder.getPizzas().size() == 0) {
            alert.setHeaderText("Error");
            alert.setContentText("No pizzas in order");
            alert.showAndWait();
            return;
        }
        int num = currOrder.getOrderNum();
        storeOrder.add(currOrder);
        currOrder = new Order();

        //update current order in other views
        FXMLLoader loader = new FXMLLoader(PizzaApplication.class.getResource("ChicagoStylePizzaOrderingView.fxml"));
        ChicagoStylePizzaOrderingController cspoc = loader.getController();
        cspoc.setCurrentOrder(currOrder);

        loader = new FXMLLoader(PizzaApplication.class.getResource("NewYorkStylePizzaOrderingView.fxml"));
        NewYorkStylePizzaOrderingController nyspoc = loader.getController();
        nyspoc.setCurrentOrder(currOrder);

        alert.setHeaderText("Order: " + num);
        alert.setContentText("Order Placed!");
        clearAll();
        alert.showAndWait();
    }

    /**
     * Clears all data from the current order view
     */
    private void clearAll() {
        orderNumber.clear();
        subtotal.clear();
        salesTax.clear();
        orderTotal.clear();
        listView.getItems().clear();
    }

    /**
     * Disables editing of the text fields
     */
    private void disableAll() {
        orderNumber.setDisable(true);
        subtotal.setDisable(true);
        salesTax.setDisable(true);
        orderTotal.setDisable(true);
    }

    /**
     * Sets the storeOrder
     * @param so the storeOrder that this store order will be set to.
     */
    public static void setStoreOrder(StoreOrder so) {storeOrder = so;}

    /**
     * Sets the order
     * @param o the order that the current order will be set to.
     */
    public static void setCurrentOrder(Order o) {currOrder = o;}

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
