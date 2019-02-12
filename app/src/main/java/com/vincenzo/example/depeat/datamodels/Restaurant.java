package com.vincenzo.example.depeat.datamodels;


import org.json.JSONException;
import org.json.JSONObject;

public class Restaurant {
    String nome;
    String indirizzo;
    Float prezzo;
    String UrlImm;

    public Restaurant(String nome, String indirizzo, Float prezzo, String UrlImm) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.prezzo = prezzo;
        this.UrlImm = UrlImm;
    }

    public Restaurant(JSONObject jsonRestaurant) throws JSONException {
        nome = jsonRestaurant.getString("name");
        indirizzo = jsonRestaurant.getString("address");
        prezzo = Float.valueOf(jsonRestaurant.getString("min_order"));
        UrlImm = jsonRestaurant.getString("image_Url");
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
