package com.vincenzo.example.depeat.datamodels;

public class Restaurant {
    String nome;
    String indirizzo;
    Float prezzo;

    public Restaurant(String nome, String indirizzo, Float prezzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.prezzo = prezzo;
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
