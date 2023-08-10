package com.example.pizzaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pizza.*;

/**
 * The activity for creating a pizza
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class PizzaSelectedActivity extends AppCompatActivity {

    private Pizza pizza;

    private RadioGroup size;

    private ArrayList<CheckBox> checkBoxes;
    private TextView crust, pizzaName, price;

    private Button add;

    /**
     * Initializes all views upon creation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_selected);

        this.pizza = this.makePizza();

        this.size = (RadioGroup) findViewById(R.id.size);
        this.pizza.setSize("Medium");

        this.crust = findViewById(R.id.crust);
        this.crust.setText("Crust: " + this.pizza.getCrust());

        this.price = findViewById(R.id.price);
        this.updatePrice();

        this.makeCheckBoxArray();
        this.generateDefaultToppings();

        this.add = findViewById(R.id.addToOrder);
        setAddButtonOnClick(this.add);
    }

    /**
     * Adds or removes a topping when a topping check box is toggled
     * Also disables the unselected check boxes if adding the topping makes the total reach 7
     * and reenables the check boxes if removing a topping brings total back to below 7
     * @param view the Check Box view for the topping to be added or removed
     */
    public void changeTopping(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        Topping topping = new Topping(((CheckBox) view).getText().toString());
        if (checked) {
            this.pizza.add(topping);
            if(this.pizza.getToppings().size() == 7) {
                this.enableAllCheckboxes(false, true);
                Toast.makeText(view.getContext(), "Maximum Toppings Reached", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (this.pizza.getToppings().size() == 7)
                this.enableAllCheckboxes(true, true);
            this.pizza.remove(topping);
        }
        this.updatePrice();
    }

    /**
     * Changes the size of the pizza when radio button group changes value
     * @param view the view for the radio button selected
     */
    public void changeSize(View view) {
        RadioButton button = (RadioButton) view;
        this.pizza.setSize(button.getText().toString());
        this.updatePrice();
    }

    /**
     * Set the onClickListener for the add to order.
     * Clicking on the button will create an AlertDialog with the options of YES/NO.
     * @param view the button view for adding to order
     */
    private void setAddButtonOnClick(@NonNull View view) {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Add to order");
                alert.setMessage("Add Pizza to Order");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),"Pizza added.", Toast.LENGTH_LONG).show();
                        MainActivity.currOrder.add(pizza);
                        finish();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),"Pizza not added.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Creates the pizza and initalizes the textviews at the top
     * @return The correct pizza type
     */
    private Pizza makePizza() {
        Bundle extras = getIntent().getExtras();

        this.pizzaName = findViewById(R.id.pizzaName);
        this.pizzaName.setText(extras.getString("PIZZA_STYLE") + " " + extras.getString("PIZZA_TYPE"));

        PizzaFactory pizzaFactory = (extras.getString("PIZZA_STYLE").equalsIgnoreCase("Chicago Style"))
                ? new ChicagoPizza() : new NYPizza();

        switch(extras.getString("PIZZA_TYPE")) {
            case "Deluxe":
                return pizzaFactory.createDeluxe();
            case "BBQ Chicken":
                return pizzaFactory.createBBQChicken();
            case "Meatzza":
                return pizzaFactory.createMeatzza();
            case "Build Your Own":
                return pizzaFactory.createBuildYourOwn();
        }
        return null;
    }

    /**
     * If the pizza is not of type Build Your Own, this method checks the check boxes for the
     * required toppings and disables all check boxes
     */
    private void generateDefaultToppings() {
        if(!(this.pizza instanceof BuildYourOwn)) {
            for (CheckBox checkBox : checkBoxes) {
                if(this.pizza.getToppings().contains(new Topping(checkBox.getText().toString())))
                    checkBox.setChecked(true);
                checkBox.setEnabled(false);
            }
        }
    }

    /**
     * Enables or disables all check boxes
     * @param enabled enables all check boxes if value if true, disables if false
     * @param ignoreIfChecked doesn't enable/disable checked check boxes if value is true, enables/disables if false
     */
    private void enableAllCheckboxes(boolean enabled, boolean ignoreIfChecked) {
        for(CheckBox checkBox : checkBoxes) {
            if(!(ignoreIfChecked && checkBox.isChecked()))
                checkBox.setEnabled(enabled);
        }
    }

    /**
     * Creates an array with all the check boxes
     */
    private void makeCheckBoxArray() {
        this.checkBoxes = new ArrayList<>();
        int[] checkBoxIDs = {R.id.hasSausage, R.id.hasPepperoni, R.id.hasGreenPepper, R.id.hasOnion, R.id.hasMushroom,
        R.id.hasBBQChicken, R.id.hasProvolone, R.id.hasCheddar, R.id.hasBeef, R.id.hasHam, R.id.hasJalapeno,
        R.id.hasOlive, R.id.hasSpinach};
        for(int id : checkBoxIDs) {
            this.checkBoxes.add(findViewById(id));
        }
    }

    /**
     * Updates the price of the pizza
     */
    private void updatePrice() {
        this.price.setText("Price: " + this.pizza.price());
    }
}
