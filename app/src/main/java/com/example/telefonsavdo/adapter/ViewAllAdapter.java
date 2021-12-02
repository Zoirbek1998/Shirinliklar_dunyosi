package com.example.telefonsavdo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telefonsavdo.R;
import com.example.telefonsavdo.activity.DetaileActivity;
import com.example.telefonsavdo.model.ViewAllModel;

import java.util.List;


public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> productsList;

    public ViewAllAdapter(Context context, List<ViewAllModel> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(productsList.get(position).getImg_url()).into(holder.allImg);
        holder.description.setText(productsList.get(position).getDescription());
        holder.name.setText(productsList.get(position).getName());
        holder.price.setText(productsList.get(position).getPrice()+" ");
        holder.rating.setText(productsList.get(position).getRating());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetaileActivity.class);
                intent.putExtra("detail",productsList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView allImg;
        TextView name,description,price,rating;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            allImg = itemView.findViewById(R.id.all_img);
            name = itemView.findViewById(R.id.all_name);
            description = itemView.findViewById(R.id.all_descrip);
            price = itemView.findViewById(R.id.all_price);
            rating = itemView.findViewById(R.id.all_rating);

        }
    }
}
