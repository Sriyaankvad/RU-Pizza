package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pizza.*;

import java.io.IOException;
import java.util.Random;

/**
 * Main Controller Class that calls all other controller classes
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class MainController {

    @FXML
    private AnchorPane anchorPane;

    private static StoreOrder storeOrder = new StoreOrder();

    private static Order currOrder = new Order();

    /**
     * initializes by sharing the store order and current order object
     * with the all controllers
     */
    @FXML
    public void initialize() {
        FXMLLoader loader = new FXMLLoader(PizzaApplication.class.getResource("StoreOrderView.fxml"));
        StoreOrderController soc = loader.getController();
        soc.setStoreOrder(storeOrder);

        loader = new FXMLLoader(PizzaApplication.class.getResource("ChicagoStylePizzaOrderingView.fxml"));
        ChicagoStylePizzaOrderingController cspoc = loader.getController();
        cspoc.setStoreOrder(storeOrder);
        cspoc.setCurrentOrder(currOrder);

        loader = new FXMLLoader(PizzaApplication.class.getResource("NewYorkStylePizzaOrderingView.fxml"));
        NewYorkStylePizzaOrderingController nyspoc = loader.getController();
        nyspoc.setStoreOrder(storeOrder);
        nyspoc.setCurrentOrder(currOrder);

        loader = new FXMLLoader(PizzaApplication.class.getResource("CurrentOrderView.fxml"));
        CurrentOrderController coc = loader.getController();
        coc.setStoreOrder(storeOrder);
        coc.setCurrentOrder(currOrder);
    }

    /**
     * Opens chicago style ordering scene
     * @param event
     * @throws IOException
     */
    @FXML
    void chicagoStyle(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(PizzaApplication.class.getResource("ChicagoStylePizzaOrderingView.fxml"));
        anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 530, 500);
        stage.setTitle("Chicago Style Pizza Ordering");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens ny style ordering scene
     * @param event
     * @throws IOException
     */
    @FXML
    void NYStyle(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(PizzaApplication.class.getResource("NewYorkStylePizzaOrderingView.fxml"));
        anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 530, 500);
        stage.setTitle("New York Style Pizza Ordering");
        stage.setScene(scene);
        stage.show();
    }

    //v = width
    //v1 = height
    /**
     * Opens store order scene
     * @param event
     * @throws IOException
     */
    @FXML
    void storeOrders(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(PizzaApplication.class.getResource("StoreOrderView.fxml"));
        anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 530, 400);
        stage.setTitle("Store Orders");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens current order scene
     * @param event
     */
    @FXML
    void currentOrder(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(PizzaApplication.class.getResource("CurrentOrderView.fxml"));
        anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 530, 400);
        stage.setTitle("Current Order");
        stage.setScene(scene);
        stage.show();
    }

}