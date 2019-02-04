package com.vincenzo.example.depeat.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Restaurant;

import java.util.ArrayList;

public class Restaurant_adapter extends RecyclerView.Adapter{

    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;

    public Restaurant_adapter(Context context, ArrayList<Restaurant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {  //quando c'è una singola riga
        View view = inflater.inflate(R.layout.item_restaurant, viewGroup, false);
        return new RestaurantViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {      //quando sta per essere visualizzata una lista
        RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder; //cast a restaurantviewholder
        vh.restaurantName.setText(data.get(i).getNome());
        vh.indirizzo_restaurant.setText(data.get(i).getIndirizzo());
        vh.prezzoMin.setText("Prezzo Minimo " + data.get(i).getPrezzo() + " Euro");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{

        public TextView restaurantName;
        public TextView indirizzo_restaurant;
        public TextView prezzoMin;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.name_tv);
            indirizzo_restaurant = itemView.findViewById(R.id.indirizzo);
            prezzoMin = itemView.findViewById(R.id.prezzo);
        }
    }
}
