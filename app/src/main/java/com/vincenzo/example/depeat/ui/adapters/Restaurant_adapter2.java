package com.vincenzo.example.depeat.ui.adapters;


        import android.content.Context;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.vincenzo.example.depeat.R;
        import com.vincenzo.example.depeat.datamodels.Restaurant;
        import com.vincenzo.example.depeat.services.RestController;
        import com.vincenzo.example.depeat.ui.activities.ShopActivity;

        import java.util.ArrayList;



public class Restaurant_adapter2 extends RecyclerView.Adapter{
    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    public final Context context;


    public Restaurant_adapter2(Context context, ArrayList<Restaurant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public Restaurant_adapter2(Context context){
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {  //quando c'è una singola riga
        View view = inflater.inflate(R.layout.item_restaurant2, viewGroup, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {      //quando sta per essere visualizzata una lista
        RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder; //cast a restaurantviewholder
        vh.restaurantName.setText(data.get(i).getNome());
        vh.indirizzo_restaurant.setText(data.get(i).getIndirizzo());
        vh.prezzoMin.setText("Prezzo Min: " + data.get(i).getPrezzo() + " €");
        Glide.with(context).load(data.get(i).getUrlImm()).into(vh.immagine2);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<Restaurant> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView restaurantName;
        public TextView indirizzo_restaurant;
        public TextView prezzoMin;
        public ImageView immagine2;

        public Button button;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.name_tv);
            indirizzo_restaurant = itemView.findViewById(R.id.indirizzo);
            prezzoMin = itemView.findViewById(R.id.prezzo);
            immagine2 = itemView.findViewById(R.id.immagine2);
            button = itemView.findViewById(R.id.button1);

            button.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button1){

                Restaurant rest = data.get(getAdapterPosition());

                Intent intent = new Intent(context, ShopActivity.class);
                intent.putExtra("name", rest.getNome());
                intent.putExtra("address", rest.getIndirizzo());
                intent.putExtra("minprice", rest.getPrezzo());
                intent.putExtra("id", rest.getId());
                intent.putExtra("urlimm", rest.getUrlImm());

                context.startActivity(intent);


            }

        }

    }
}

