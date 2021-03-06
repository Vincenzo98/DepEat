package com.vincenzo.example.depeat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Restaurant;
import com.vincenzo.example.depeat.services.RestController;
import com.vincenzo.example.depeat.ui.adapters.Restaurant_adapter;
import com.vincenzo.example.depeat.ui.adapters.Restaurant_adapter2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener{

    private static final String TAG = MainActivity.class.getName();

    boolean view = false;

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManagerGrid;
    Restaurant_adapter adapter;
    Restaurant_adapter2 adapter2;
    ArrayList<Restaurant> arrayList = new ArrayList<>();

    private RestController restController;

    SharedPreferences.Editor editor;
    SharedPreferences share;

    private static final String SharedPrefs = "org.elis.depeat.general_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editor = getSharedPreferences("DepEat", MODE_PRIVATE).edit();
        share = getSharedPreferences("DepEat", MODE_PRIVATE);

        restController = new RestController(this);
        restController.getRequest(Restaurant.ENDPOINT, this, this );

        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        layoutManagerGrid = new GridLayoutManager(this, 2);
        adapter = new Restaurant_adapter(this);
        adapter2 = new Restaurant_adapter2(this);
        view = share.getBoolean("gridmode", false);


    }



    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("McDonald's", "Contrada Cuore di Gesu 909", 30f, "https://static.seekingalpha.com/uploads/2018/7/24/saupload_3000px-McDonald_27s_SVG_logo.svg.png"));
        arrayList.add(new Restaurant("Burger king", "Via Roma 399", 20f, "https://upload.wikimedia.org/wikipedia/it/thumb/3/3a/Burger_King_Logo.svg/1013px-Burger_King_Logo.svg.png"));
        arrayList.add(new Restaurant("KFC", "Via Mazzini 544", 15f, "https://upload.wikimedia.org/wikipedia/it/thumb/5/57/300px-KFC_logo_svg.png/200px-300px-KFC_logo_svg.png"));
        arrayList.add(new Restaurant("Old Wild West", "Piazza della Repubblica 20", 40f, "https://www.datocms-assets.com/1988/1493821522-o-jpg"));
        arrayList.add(new Restaurant("Pizza", "Contrada Strasatti  300", 35f, "https://www.costacrociere.it/content/dam/costa/inventory-assets/activity-types/casual/pizzeria-pummidoro/pizzeria-pummid-oro2.jpg"));

        return arrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        setMode();

       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.login_menu){
            startActivity(new Intent(this,LoginActivity.class));
            return true;
        }else if(item.getItemId() == R.id.checkout_menu){
            startActivity(new Intent(this, CheckOutActivity.class));
        } else{
            setMode();
        }
        return super.onOptionsItemSelected(item);
    }


    private void setMode(){
        if(!view){
             restaurantRV.setLayoutManager(layoutManagerGrid);
             restaurantRV.setAdapter(adapter2);
             editor.putBoolean("gridmode", false).commit();
             editor.apply();
             view = true;
        }else {
            restaurantRV.setLayoutManager(layoutManager);
            restaurantRV.setAdapter(adapter);
            editor.putBoolean("gridmode", true).commit();
            editor.apply();
            view = false;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i = 0; i<jsonArray.length(); i++){
                arrayList.add(new Restaurant(jsonArray.getJSONObject(i)));
            }
            Log.i("simone", "sono qui");
            adapter.setData(arrayList);
            adapter2.setData(arrayList);
        }catch (JSONException e){
            Log.e(TAG, e.getMessage());
        }
    }
}
