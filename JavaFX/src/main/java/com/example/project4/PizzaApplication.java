package com.example.project4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The class used to launch the Pizza Application
 * @author Sriyaank Vadlamani, Paul Manayath
 */
public class PizzaApplication extends Application {

    /**
     * Creates gui for RU Pizza
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.setTitle("RU Pizza");
        stage.setScene(scene);
        stage.show();
        //close all windows when main view window is closed
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    /**
     * Runs RU Pizza
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}