package com.example.pizzaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Activity for selecting a pizza type
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class PizzaActivity extends AppCompatActivity implements Serializable {

    private ArrayList<PizzaModel> pizzaModels = new ArrayList<>();

    private int[] images = {R.drawable.chicagodeluxe, R.drawable.nydeluxe, R.drawable.chicagobbq,
    R.drawable.nybbq, R.drawable.chicagomeatzza, R.drawable.nymeatzza, R.drawable.chicagopizza,
    R.drawable.nypizza};

    /**
     * Initializes the recycle view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        RecyclerView recyclerView = findViewById(R.id.pizzaRecyclerView);

        this.setUpPizzaModels();

        PizzaAdapter adapter = new PizzaAdapter(this, this.pizzaModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Adds all possible pizza models to this.pizzaModels
     */
    private void setUpPizzaModels() {
        String[] pizzaTypes = getResources().getStringArray(R.array.pizza_types);
        String[] pizzaStyles = getResources().getStringArray(R.array.pizza_styles);

        int cnt = 0;
        for (String pizzaType : pizzaTypes) {
            for (String pizzaStyle : pizzaStyles) {
                this.pizzaModels.add(new PizzaModel(pizzaType, pizzaStyle, images[cnt]));
                cnt++;
            }
        }
    }

}
