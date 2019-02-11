package com.vincenzo.example.depeat.ui.adapters;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Shop;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class OrderProduct_adapter extends RecyclerView.Adapter<OrderProduct_adapter.OrderProductViewHolder> {

    private ArrayList<Shop> data;
    private Context context;
    private LayoutInflater inflater;


    public OrderProduct_adapter(Context context, ArrayList<Shop> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderProductViewHolder(inflater.inflate(R.layout.item_order, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductViewHolder orderProductViewHolder, int i) {
        Shop shop = data.get(i);
        orderProductViewHolder.product_name.setText(shop.getCibo());
        orderProductViewHolder.quantity.setText(String.valueOf(shop.getQuantity()));
        orderProductViewHolder.totale.setText(String.valueOf(shop.getTotale()));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class OrderProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView quantity, product_name, totale;
        public ImageButton remove_btn;

        public OrderProductViewHolder(@NonNull View itemView) {
            super(itemView);

            quantity = itemView.findViewById(R.id.quantity_tv);
            product_name = itemView.findViewById(R.id.product_name_tv);
            totale = itemView.findViewById(R.id.subtotal_tv);
            remove_btn = itemView.findViewById(R.id.remove_btn);

            remove_btn.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }

}
