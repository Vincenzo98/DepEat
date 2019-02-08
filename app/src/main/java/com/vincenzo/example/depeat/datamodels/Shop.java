package com.vincenzo.example.depeat.datamodels;

public class Shop {
    String cibo;
    Float prezzo;
    int quantity = 0;
    String totale;


    public String getTotale() {
        return totale;
    }

    public void setTotale(String totale) {
        this.totale = totale;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Shop(String cibo, Float prezzo) {
        this.cibo = cibo;
        this.prezzo = prezzo;
    }


    public String getCibo() {
        return cibo;
    }

    public void setCibo(String cibo) {
        this.cibo = cibo;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }


    public void increaseQuantity(){
        this.quantity++;
    }

    public  void  decreaseQuantity(){
        if(quantity == 0) return;
        this.quantity--;
    }
}
