package com.vincenzo.example.depeat.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Shop;
import com.vincenzo.example.depeat.ui.activities.ShopActivity;

import java.util.ArrayList;


public class Shop_adapters extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Shop> data;
    private OnQuantityChangedListener onQuantityChangedListener;



    public interface OnQuantityChangedListener{
        void onChange(float prezzo);
    }


    public Shop_adapters(Context context, ArrayList<Shop> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }




    public OnQuantityChangedListener getOnQuantityChangedListener() {
        return onQuantityChangedListener;
    }

    public void setOnQuantityChangedListener(OnQuantityChangedListener onQuantityChangedListener) {
        this.onQuantityChangedListener = onQuantityChangedListener;
    }


    public void setData(ArrayList<Shop> data){
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_shop, viewGroup, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ShopViewHolder sh = (ShopViewHolder) viewHolder;

        sh.cibo.setText(data.get(i).getCibo());
        sh.prezzo.setText("" +data.get(i).getPrezzo());
        sh.quantita.setText(String.valueOf(data.get(i).getQuantity()));
    }



    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView cibo;
        public TextView prezzo;
        public TextView quantita;

        public Button button3;
        public Button button4;


        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);

            cibo = itemView.findViewById(R.id.cibo);
            prezzo = itemView.findViewById(R.id.prezzo);
            quantita = itemView.findViewById(R.id.quantita);

            button3 = itemView.findViewById(R.id.addBtn);
            button4 = itemView.findViewById(R.id.removeBtn);

            button3.setOnClickListener(this);
            button4.setOnClickListener(this);


        }




        @Override
        public void onClick(View v) {

            Shop shop = data.get(getAdapterPosition());

            if(v.getId() == R.id.addBtn){

                shop.increaseQuantity();
                onQuantityChangedListener.onChange(shop.getPrezzo());


            }else if(v.getId() == R.id.removeBtn){
                if(shop.getQuantity() == 0 )return;

                shop.decreaseQuantity();
                onQuantityChangedListener.onChange(shop.getPrezzo()*-1);

            }

            notifyItemChanged(getAdapterPosition());

        }
    }
}