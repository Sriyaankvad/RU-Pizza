package com.example.pizzaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.DialogInterface;
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
import pizza.StoreOrder;
import pizza.Topping;

/** The class used for implementing the current order activity
 * @author Sriyaank Vadlamani, Paul Manayath
 */
public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final DecimalFormat df = new DecimalFormat( "#.00" );
    private ListView listview;
    private Button currOrderBackButton, clearOrderButton, placeOrderButton;
    private TextView subtotal, salesTax, orderTotal;
    private ArrayAdapter<String> adapter;
    private Order order;
    private StoreOrder storeOrder;
    private ArrayList<String> pizzas = new ArrayList<>();

    /**
     * Loads the layout file for current order
     * Retrieves store order and current order data from main activity
     * initializes listview and textview with data
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("order");
        storeOrder = (StoreOrder) intent.getSerializableExtra("storeorder");
        if (order == null || storeOrder == null)
            return;
        initialize();
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
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pizzas);
            listview = findViewById(R.id.listview);
            listview.setOnItemClickListener(this); //register the listener for an OnItemClick event.
            listview.setAdapter(adapter);
        }
        if (getSupportActionBar() != null) {getSupportActionBar().hide();}
    }

    /**
     * Listener to check if a pizza has been removed or not
     * @param adapterView
     * @param view
     * @param i the position of the item in the list view
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove Pizza?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                pizzas.remove(i);
                order.remove(order.getPizzas().get(i));
                adapter.notifyDataSetChanged();
                updateCost();
                Toast.makeText(getApplicationContext(), "REMOVED", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Create alert to alert user that their order is empty
     */
    private void emptyOrderAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Unable To Do That");
        alert.setMessage("Your Order is Empty");
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Listeners for the the buttons
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent data = new Intent();
        switch (view.getId()) {
            case R.id.currOrderBackButton:
                data.putExtra("returnOrder", order);
                data.putExtra("returnStoreOrder", storeOrder);
                setResult(Activity.RESULT_OK, data);
                finish();
                break;
            case R.id.placeOrderButton:
                if (order == null || order.getPizzas().isEmpty()) {
                    emptyOrderAlert();
                    return;
                }
                storeOrder.add(order);
                data.putExtra("returnOrder", new Order());
                data.putExtra("returnStoreOrder", storeOrder);
                setResult(Activity.RESULT_OK, data);
                Toast.makeText(getApplicationContext(), "Order Placed!", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.clearOrderButton:
                if (order == null || order.getPizzas().isEmpty()) {
                    emptyOrderAlert();
                    return;
                }
                pizzas.clear();
                order.clearOrder();
                adapter.notifyDataSetChanged();
                updateCost();
            default:
                break;
        }
    }

    /**
     * Set buttons and text views with correct data
     */
    private void initialize() {
        // set up buttons
        currOrderBackButton = findViewById(R.id.currOrderBackButton);
        currOrderBackButton.setOnClickListener(this);
        clearOrderButton = findViewById(R.id.clearOrderButton);
        clearOrderButton.setOnClickListener(this);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        placeOrderButton.setOnClickListener(this);
        // set up text views
        subtotal = findViewById(R.id.subtotaltvToEdit);
        salesTax = findViewById(R.id.salesTaxToEdittv);
        orderTotal = findViewById(R.id.ordertotaltoedit);
        updateCost();
    }

    /**
     * Update cost of order on text views if a pizza had been removed
     */
    private void updateCost() {
        subtotal.setText(df.format(order.getPizzaCost()));
        salesTax.setText(df.format(order.getSalesTaxCost()));
        orderTotal.setText(df.format(order.getTotalCost()));
    }

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



