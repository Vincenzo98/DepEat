package com.vincenzo.example.depeat.datamodels;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Restaurant {
    String nome;
    String indirizzo;
    Float prezzo;
    String UrlImm;
    String id;
    public static final String ENDPOINT = "restaurants/";




    private ArrayList<Shop> products;

    public Restaurant(String nome, String indirizzo, Float prezzo, String UrlImm) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.prezzo = prezzo;
        this.UrlImm = UrlImm;

        products = new ArrayList<>();

    }

    public ArrayList<Shop> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Shop> products) {
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }







    public Restaurant(JSONObject jsonRestaurant) throws JSONException {
        nome = jsonRestaurant.getString("name");
        indirizzo = jsonRestaurant.getString("address");
        prezzo = (float)(jsonRestaurant.getDouble("min_order"));
        UrlImm = jsonRestaurant.getString("image_url");
        id = jsonRestaurant.getString("id");

    }


    public String getUrlImm() {
        return UrlImm;
    }

    public void setUrlImm(String urlImm) {
        this.UrlImm = urlImm;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }


}
