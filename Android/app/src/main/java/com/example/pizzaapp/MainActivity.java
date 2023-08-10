package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

import pizza.Order;
import pizza.StoreOrder;

/**
 * The class used for the Main Activity
 * @author Sriyaank Vadlamani, Paul Manayath
 */
public class MainActivity extends AppCompatActivity implements Serializable {

    public static Order currOrder = new Order();
    public static StoreOrder storeOrder = new StoreOrder();

    /**
     * Loads layout file for main and initializes the quit button listener
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    /**
     * Create an Intent object with the Order Activity class
     * to start when Current Order button is pressed
     * @param view
     */
    public void showOrder(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("order", currOrder);
        intent.putExtra("storeorder", storeOrder);
        startActivityForResult(intent, 1);
    }

    /**
     * Create an Intent object with the Pizza Activity
     * class to start when the Make Pizza button is pressed
     * @param view
     */
    public void makePizza(View view) {
        Intent intent = new Intent(this, PizzaActivity.class);
        startActivity(intent);
    }

    /**
     * Create an Intent object with the Store Order Activity
     * class to start when the Store Order button is pressed
     * @param view
     */
    public void showStoreOrders(View view) {
        Intent intent = new Intent(this, StoreOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Receive results from current order after current order activity is finished
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                if (data.hasExtra("returnOrder"))
                    currOrder = (Order) data.getExtras().get("returnOrder");
                if (data.hasExtra("returnStoreOrder"))
                    storeOrder = (StoreOrder) data.getExtras().get("returnStoreOrder");}
        }
    }
}