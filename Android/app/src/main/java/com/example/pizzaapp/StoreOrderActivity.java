package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import pizza.BBQChicken;
import pizza.BuildYourOwn;
import pizza.Deluxe;
import pizza.Meatzza;
import pizza.Order;
import pizza.Pizza;
import pizza.Topping;

/** The class used for implementing the store order activity
 * @author Sriyaank Vadlamani, Paul Manayath
 */
public class StoreOrderActivity extends AppCompatActivity implements OnItemSelectedListener{

    private static final DecimalFormat df = new DecimalFormat( "#.00" );
    private Button cancelOrder;
    private Spinner spinner;
    private TextView total;
    private ArrayAdapter<String> dataAdapter;
    private ArrayAdapter<String> adapter;

    /**
     * Loads layout file for store orders, sets spinner and text view
     * attaches a listener to the spinner
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        total = (TextView) findViewById(R.id.storeOrderTotalTextView);
        ArrayList<String> orders = new ArrayList<String>();
        for (Order order: MainActivity.storeOrder.getOrders()) {
            orders.add(String.valueOf(order.getOrderNum()));
        }

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, orders);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    /**
     * Displays all pizzas in selected order with list view
     * Attaches listener to the cancel order button
     * @param parent
     * @param view
     * @param position position of item in spinner
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ListView lv = (ListView) findViewById(R.id.StoreOrderListView);
        Order order = MainActivity.storeOrder.getOrders().get(position);
        ArrayList<String> pizzas = new ArrayList<>();

        if (!order.getPizzas().isEmpty()){
            ArrayList<Pizza> pizzaList = order.getPizzas();
            for (int i = 0; i < pizzaList.size(); i++) {
                Pizza p = pizzaList.get(i);
                StringBuilder details = new StringBuilder(this.getPizzaType(p) + ": " + p.getSize() + ", ");
                details.append(p.getCrust().toString()).append(", Toppings: ");
                for (Topping tp: p.getToppings())
                    details.append(tp.toString()).append(", ");
                details.append("$").append(p.price());
                pizzas.add(details.toString());
            }
        }
        total.setText("Order Total: $ " + df.format(order.getTotalCost()));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pizzas);
        lv.setAdapter(adapter);

        cancelOrder = findViewById(R.id.cancelOrderButton);
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if (MainActivity.storeOrder.getOrders().isEmpty()) {
//                    return;
//                }
               MainActivity.storeOrder.getOrders().remove(position);
               finish();
               startActivity(getIntent());
            }
        });
    }

    /**
     * This is method is require due to class implementing OnItemSelectedListener
     * Method does nothing though
     * @param arg0
     */
    public void onNothingSelected(AdapterView<?> arg0) {}

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
