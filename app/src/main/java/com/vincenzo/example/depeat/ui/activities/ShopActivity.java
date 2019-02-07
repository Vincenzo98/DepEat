package com.vincenzo.example.depeat.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Shop;
import com.vincenzo.example.depeat.ui.adapters.Shop_adapters;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements Shop_adapters.OnQuantityChangedListener {

    RecyclerView shopRv;

    Shop_adapters adapters;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<Shop> arrayList;

    private float total=0;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shopRv = findViewById(R.id.prodotti);
        layoutManager = new LinearLayoutManager(this);
        adapters = new Shop_adapters(this, getData());
        shopRv.setLayoutManager(layoutManager);
        shopRv.setAdapter(adapters);
    }

    private ArrayList<Shop> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Shop("Hamburger", 10f));
        arrayList.add(new Shop("Patatine", 5f));
        arrayList.add(new Shop("Coca Cola", 3f));
        arrayList.add(new Shop("Acqua", 1.5f));
        arrayList.add(new Shop("Ketchup", 1f));
        arrayList.add(new Shop("Dolce", 2f));
        return arrayList;
    }

    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    private void updateTotal(float item){
        total+=item;
    }

    @Override
    public void onChange(float prezzo) {
        updateTotal(prezzo);
        updateProgress((int)(total * 100));
    }
}
