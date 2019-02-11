package com.vincenzo.example.depeat.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Restaurant;
import com.vincenzo.example.depeat.datamodels.Shop;
import com.vincenzo.example.depeat.ui.adapters.Restaurant_adapter;
import com.vincenzo.example.depeat.ui.adapters.Shop_adapters;

import java.util.ArrayList;



public class ShopActivity extends AppCompatActivity implements Shop_adapters.OnQuantityChangedListener{

    private RecyclerView shopRv;

    private Shop_adapters adapters;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<Shop> arrayList;


    private float total=0;

    private int val;

    private TextView nome2, indirizzo2, totale;
    private Button checkout;
    private ProgressBar progressBar;
    private ImageView immagine3;


    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        nome2 = findViewById(R.id.nome2);
        indirizzo2 = findViewById(R.id.indirizzo2);
        totale = findViewById(R.id.totale);
        immagine3 =findViewById(R.id.immagine3);


        checkout = findViewById(R.id.checkout);
        progressBar = findViewById(R.id.progressBar);
        shopRv = findViewById(R.id.prodotti);


        restaurant = new Restaurant("Pizzeria", "Via roma 303", 30f, "https://static.seekingalpha.com/uploads/2018/7/24/saupload_3000px-McDonald_27s_SVG_logo.svg.png");


        nome2.setText(restaurant.getNome());
        indirizzo2.setText(restaurant.getIndirizzo());
        Glide.with(this).load(restaurant.getUrlImm()).into(immagine3);


        val = Math.round(restaurant.getPrezzo());
        progressBar.setMax(val * 100);


        layoutManager = new LinearLayoutManager(this);
        adapters = new Shop_adapters(this, getData());
        adapters.setOnQuantityChangedListener(this);


        shopRv.setLayoutManager(layoutManager);
        shopRv.setAdapter(adapters);


        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopActivity.this, CheckOutActivity.class));
            }
        });

    }

    private ArrayList<Shop> getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new Shop("Hamburger", 10f));
        arrayList.add(new Shop("Patatine", 5f));
        arrayList.add(new Shop("Coca Cola", 3f));
        arrayList.add(new Shop("Acqua", 1.5f));
        arrayList.add(new Shop("Ketchup", 1f));
        arrayList.add(new Shop("Dolce", 2f));
        return arrayList;
    }

    private void updateTotal(float item) {
        total += item;
        totale.setText("Totale: ".concat(String.valueOf(total)));
    }

    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    @Override
    public void onChange(float prezzo) {

        updateTotal(prezzo);
        updateProgress((int)(total * 100));
        btnCheck(checkout);

    }



    private void btnCheck(Button button){
        if(total >= val){
            button.setEnabled(true);
        }else{
            button.setEnabled(false);
        }
    }


}
