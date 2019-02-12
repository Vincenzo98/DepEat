package com.vincenzo.example.depeat.ui.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Shop;

import java.util.ArrayList;


public class OrderProduct_adapter extends RecyclerView.Adapter<OrderProduct_adapter.OrderProductViewHolder> {

    private ArrayList<Shop> data;
    private Context context;
    private LayoutInflater inflater;
    private float ordineMin;


    public OrderProduct_adapter(Context context, ArrayList<Shop> data, float ordineMin){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.ordineMin = ordineMin;
    }


    private onItemRemovedListener onItemRemovedListener;

    public interface onItemRemovedListener{
        void onItemRemoved(float subtotal, int quantity);
    }

    public OrderProduct_adapter.onItemRemovedListener getOnItemRemovedListener() {
        return onItemRemovedListener;
    }

    public void setOnItemRemovedListener(OrderProduct_adapter.onItemRemovedListener onItemRemovedListener) {
        this.onItemRemovedListener = onItemRemovedListener;
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
        orderProductViewHolder.prezzo.setText(String.valueOf(shop.getPrezzo()));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    private void removeItem(int index){
        data.remove(index);
        notifyItemRemoved(index);
    }

    public class OrderProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView quantity, product_name, prezzo;
        public ImageButton remove_btn;

        public OrderProductViewHolder(@NonNull View itemView) {
            super(itemView);

            quantity = itemView.findViewById(R.id.quantity_tv);
            product_name = itemView.findViewById(R.id.product_name_tv);
            prezzo = itemView.findViewById(R.id.subtotal_tv);
            remove_btn = itemView.findViewById(R.id.remove_btn);

            remove_btn.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            AlertDialog.Builder removeAlert = new AlertDialog.Builder(context);
            removeAlert.setTitle(R.string.be_careful)
                    .setMessage(R.string.remove_title)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onItemRemovedListener.onItemRemoved(data.get(getAdapterPosition()).getPrezzo(), data.get(getAdapterPosition()).getQuantity());
                            removeItem(getAdapterPosition());

                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .create()
                    .show();
        }
        }
    }

