package com.vincenzo.example.depeat.datamodels;

public class Shop {
    String cibo;
    Float prezzo;
    int quantity = 0;


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

    public String getPrezzo() {
        return prezzo + " €";
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
