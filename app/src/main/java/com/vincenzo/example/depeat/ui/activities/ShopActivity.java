package com.vincenzo.example.depeat.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Order;
import com.vincenzo.example.depeat.datamodels.Restaurant;
import com.vincenzo.example.depeat.datamodels.Shop;
import com.vincenzo.example.depeat.services.RestController;
import com.vincenzo.example.depeat.ui.adapters.Restaurant_adapter;
import com.vincenzo.example.depeat.ui.adapters.Shop_adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class ShopActivity extends AppCompatActivity implements Shop_adapters.OnQuantityChangedListener,Response.Listener<String>, Response.ErrorListener{

    private RecyclerView shopRv;

    private Shop_adapters adapters;
    ArrayList<Shop> shops = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
  //  ArrayList<Shop>  = new ArrayList<>();

    float price;

    private float total=0;

    private int val;

    private TextView nome2, indirizzo2, totale;
    private Button checkout;
    private ProgressBar progressBar;
    private ImageView immagine3;

    private Restaurant restaurant;

    private RestController restController;

    private static final String TAG = ShopActivity.class.getSimpleName();
    private static final int LOGIN_REQUEST_CODE = 2001;


    private Menu menu;

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




        Intent intent = getIntent();

        String name = intent.getExtras().getString("name");
        String address = intent.getExtras().getString("address");
        price = intent.getExtras().getFloat("minprice");
        String id = intent.getExtras().getString("id");

        restController = new RestController(this);
        restController.getRequest(Restaurant.ENDPOINT.concat(id), this, this );

        layoutManager = new LinearLayoutManager(this);
        adapters = new Shop_adapters(this, shops);
        adapters.setOnQuantityChangedListener(this);


        shopRv.setLayoutManager(layoutManager);
        ((SimpleItemAnimator) shopRv.getItemAnimator()).setSupportsChangeAnimations(false);
        shopRv.setAdapter(adapters);



        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopActivity.this, CheckOutActivity.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        this.menu = menu;
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.login_menu) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, LOGIN_REQUEST_CODE);

        }else if(item.getItemId() == R.id.checkout_menu){

        }
        return super.onOptionsItemSelected(item);
    }


    private void updateTotal(float item) {
        total += item;
        totale.setText("Totale: ".concat(String.valueOf(total)));
    }

    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }



    private void btnCheck(Button button){
        if(total >= val){
            button.setEnabled(true);
        }else{
            button.setEnabled(false);
        }
    }

    private void bindData(){
        nome2.setText(restaurant.getNome());
        indirizzo2.setText(restaurant.getIndirizzo());
        Glide.with(this).load(restaurant.getUrlImm()).into(immagine3);
        val = Math.round(restaurant.getPrezzo());
        progressBar.setMax(val * 100);

        adapters.setData(restaurant.getProducts());

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG,"requestCode " + requestCode);
        Log.d(TAG,"resultCode " + resultCode);

        if(requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            // TODO login is successful manage result

            Log.d(TAG,data.getStringExtra("response"));
            menu.findItem(R.id.login_menu).setTitle(R.string.profile)
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            startActivity(new Intent(ShopActivity.this,ProfileActivity.class));
                            return true;
                        }
                    });


        }
    }

    @Override
    public void onChange(float prezzo) {

        updateTotal(prezzo);
        updateProgress((int)(total * 100));
        btnCheck(checkout);

    }



    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            restaurant = new Restaurant(jsonObject);
            JSONArray jsonArray = jsonObject.getJSONArray("products");
            ArrayList<Shop> shops = new ArrayList<>();

            for(int i = 0; i<jsonArray.length(); i++){
                shops.add(new Shop(jsonArray.getJSONObject(i)));
            }

            bindData();
            adapters.setData(shops);


        }catch (JSONException e){
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
