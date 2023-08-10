package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pizza.*;

import java.util.ArrayList;

/**
 * Controller Class for ordering a chicago style pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class ChicagoStylePizzaOrderingController {

    private Pizza pizza;

    private ChicagoPizza style = new ChicagoPizza();

    private static StoreOrder storeOrder;

    private static Order currOrder;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField crust, price;

    @FXML
    private ListView availableToppings, selectedToppings;

    @FXML
    private ToggleGroup size;

    /**
     * Initializes the combo box and the values for a
     * medium Build Your Own Pizza with no toppings
     */
    @FXML
    public void initialize() {
        ObservableList<String> pizzaTypes = FXCollections.observableArrayList(
                "Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"
        );
        this.type.setItems(pizzaTypes);
        this.type.getSelectionModel().select(3);

        this.pizza = this.style.createBuildYourOwn();
        this.pizza.setSize(((RadioButton) this.size.getSelectedToggle()).getText());
        this.crust.setText("" + this.pizza.getCrust());
        this.price.setText("" + this.pizza.price());

        this.availableToppings.setItems(FXCollections.observableArrayList(Topping.getToppings()));
    }

    /**
     * Changes the pizza to the type selected in combo box
     * @param event
     */
    @FXML
    public void selectPizza(ActionEvent event) {
        switch (this.type.getValue()) {
            case "Deluxe" -> this.pizza = this.style.createDeluxe();
            case "BBQ Chicken" -> this.pizza = this.style.createBBQChicken();
            case "Meatzza" -> this.pizza = this.style.createMeatzza();
            case "Build Your Own" -> this.pizza = this.style.createBuildYourOwn();
        }
        this.pizza.setSize(((RadioButton) this.size.getSelectedToggle()).getText());
        this.crust.setText("" + this.pizza.getCrust());

        if (!(this.pizza instanceof BuildYourOwn)) {
            this.selectedToppings.setItems(FXCollections.observableArrayList(this.pizza.getToppings()));
            this.availableToppings.getItems().clear();
        } else {
            this.availableToppings.setItems(FXCollections.observableArrayList(Topping.getToppings()));
            this.selectedToppings.getItems().clear();
        }
        this.price.setText("" + this.pizza.price());
    }

    /**
     * Sets the size of the pizza when changed in radio button group
     */
    @FXML
    public void setSize() {
        this.pizza.setSize(((RadioButton) this.size.getSelectedToggle()).getText());
        this.price.setText("" + this.pizza.price());
    }

    /**
     * Adds toppings when requested
     * Only activates if pizza type is Build Your Own Pizza
     * @param event
     */
    @FXML
    public void addTopping(ActionEvent event) {
        if(this.pizza instanceof BuildYourOwn) {
            if(this.selectedToppings.getItems().size() >= 7) {
                this.alert("Too Many Toppings", "No More Toppings Allowed",
                        "Cannot have more than 7 toppings");
            } else if(!this.availableToppings.getSelectionModel().getSelectedItems().isEmpty()) {
                this.selectedToppings.getItems().addAll(this.availableToppings.getSelectionModel().getSelectedItems());
                this.availableToppings.getItems().removeAll(this.availableToppings.getSelectionModel().getSelectedItems());

                if (this.selectedToppings.getItems() == null || this.selectedToppings.getItems().size() == 0)
                    return;
                int index = this.selectedToppings.getItems().size() - 1;
                Object topping =  this.selectedToppings.getItems().get(index);
                this.pizza.add(new Topping("" + topping));
                this.price.setText("" + this.pizza.price());
            }
        }
    }

    /**
     * Removes toppings when requested
     * Only activates if pizza type is Build Your Own Pizza
     * @param event
     */
    @FXML
    public void removeTopping(ActionEvent event) {
        if(this.pizza instanceof BuildYourOwn) {
            if(!this.selectedToppings.getSelectionModel().getSelectedItems().isEmpty()) {
                this.availableToppings.getItems().addAll(this.selectedToppings.getSelectionModel().getSelectedItems());
                this.selectedToppings.getItems().removeAll(this.selectedToppings.getSelectionModel().getSelectedItems());

                if (this.availableToppings.getItems() == null || this.availableToppings.getItems().size() == 0)
                    return;
                int index = this.availableToppings.getItems().size() - 1;
                Object t = this.availableToppings.getItems().get(index);
                this.pizza.remove(new Topping((String) t));
                this.price.setText("" + this.pizza.price());
            }
        }
    }

    /**
     * Adds the pizza to the order
     */
    @FXML
    public void addToOrder() {
        currOrder.add(this.pizza);
        selectPizza(new ActionEvent());
        this.alert("Pizza Added", "Order #" + currOrder.getOrderNum(),"Pizza Added!");
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
     * Helper Method to put alert
     * @param title the title of the alert
     * @param header the header of the alert
     * @param content the content in the alert
     */
    private void alert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
