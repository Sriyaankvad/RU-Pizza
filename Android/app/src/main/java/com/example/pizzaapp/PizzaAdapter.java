package com.example.pizzaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Adapter for handling the recycle view when selecting pizza type
 * @author Paul Manayath, Sriyaank Vadlamani
 */
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {
    private Context context;
    private ArrayList<PizzaModel> pizzaModels;

    public PizzaAdapter(Context context, ArrayList<PizzaModel> pizzaModels) {
        this.context = context;
        this.pizzaModels = pizzaModels;
    }

    /**
     * Inflates the layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public PizzaAdapter.PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pizza_row, parent, false);
        return new PizzaAdapter.PizzaViewHolder(view);
    }

    /**
     * Assigns the values to the views in pizza_row based on position of recycler view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PizzaAdapter.PizzaViewHolder holder, int position) {
        holder.pizzaType.setText(pizzaModels.get(position).getPizzaType());
        holder.pizzaStyle.setText(pizzaModels.get(position).getPizzaStyle());
        holder.image.setImageResource(pizzaModels.get(position).getImage());
    }

    /**
     * The number of items to display in recycler view
     * @return
     */
    @Override
    public int getItemCount() {
        return this.pizzaModels.size();
    }

    /**
     * Grabbing the views from the pizza_row layout
     */
    public static class PizzaViewHolder extends RecyclerView.ViewHolder {

        private TextView pizzaType;
        private TextView pizzaStyle;
        private ImageView image;
        private ConstraintLayout parentLayout;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);

            this.pizzaType = itemView.findViewById(R.id.pizzaType);
            this.pizzaStyle = itemView.findViewById(R.id.pizzaStyle);
            this.parentLayout = itemView.findViewById(R.id.rowLayout);
            this.image = itemView.findViewById(R.id.pizzaImage);

            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), PizzaSelectedActivity.class);
                    intent.putExtra("PIZZA_TYPE", pizzaType.getText());
                    intent.putExtra("PIZZA_STYLE", pizzaStyle.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
