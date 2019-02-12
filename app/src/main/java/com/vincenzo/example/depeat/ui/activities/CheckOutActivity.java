package com.vincenzo.example.depeat.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Order;
import com.vincenzo.example.depeat.datamodels.Restaurant;
import com.vincenzo.example.depeat.datamodels.Shop;
import com.vincenzo.example.depeat.ui.adapters.OrderProduct_adapter;

import java.util.ArrayList;




public class CheckOutActivity extends AppCompatActivity implements View.OnClickListener, OrderProduct_adapter.onItemRemovedListener{


    private TextView nome3, indirizzo3, totale2;
    private Button pay;
    private OrderProduct_adapter adapter;
    private RecyclerView product_rv;
    private LinearLayoutManager layoutManager;

    private Order order;
    private float totale;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.checkout);

            nome3 = findViewById(R.id.nome3);
            indirizzo3 = findViewById(R.id.indirizzo3);
            totale2 = findViewById(R.id.totale2);
            pay = findViewById(R.id.pay);
            product_rv = findViewById(R.id.product_rv);

            order = getOrder();
            totale = order.getTotal();

            layoutManager = new LinearLayoutManager(this);
            product_rv.setLayoutManager(layoutManager);
            adapter = new OrderProduct_adapter(this, order.getProdotti(), order.getRestaurant().getPrezzo());
            adapter.setOnItemRemovedListener(this);
            product_rv.setAdapter(adapter);

            pay.setOnClickListener(this);

            bindData();
        }


    private Order getOrder(){

        Order mockOrder =  new Order();
        mockOrder.setProdotti(getProdotti());
        mockOrder.setRestaurant(getRestaurant());
        float prezzo = 0.0f;
        for(Shop i : mockOrder.getProdotti()){
            prezzo += (i.getQuantity() * i.getPrezzo());
        }

        mockOrder.setTotal(prezzo);

        return mockOrder;
    }

    private void bindData(){
        nome3.setText(order.getRestaurant().getNome());
        indirizzo3.setText(order.getRestaurant().getIndirizzo());
        totale2.setText(String.valueOf("Totale: " + order.getTotal()));

    }

    private Restaurant getRestaurant() {
        return new Restaurant("Nome ristorante", "via roma 303", 30f, " ");
    }


    public void onItemRemoved(float subtotal, int quantity) {
            updateTotal(subtotal, quantity);
    }

    private void updateTotal(float subtotal, int quantity) {
        if(totale == 0) return;
        totale -=(subtotal * quantity);
        totale2.setText(String.valueOf(totale));
    }

    private ArrayList<Shop> getProdotti() {
        ArrayList<Shop> data = new ArrayList<>();
        data.add(new Shop("Hamburger", 10f));
        data.add(new Shop("Patatine", 5f));
        data.add(new Shop("Coca Cola", 3f));
        data.add(new Shop("Acqua", 1.5f));
        data.add(new Shop("Ketchup", 1f));
        data.add(new Shop("Dolce", 2f));
        int cont = 1;
        for(Shop i : data){
            i.setQuantity(cont);
            cont ++;
        }

        return data;

    }

    @Override
    public void onClick(View v) {

    }
}



