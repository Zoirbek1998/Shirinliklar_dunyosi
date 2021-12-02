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
import com.example.telefonsavdo.activity.NavCategoryActivity;
import com.example.telefonsavdo.model.NewCategoryModel;

import java.util.List;

public class NawCategoryAdapter extends RecyclerView.Adapter<NawCategoryAdapter.ViewHolder> {

    private Context context;
    private List<NewCategoryModel> newCategoryModels;

    public NawCategoryAdapter(Context context, List<NewCategoryModel> newCategoryModels) {
        this.context = context;
        this.newCategoryModels = newCategoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(newCategoryModels.get(position).getImg_url()).into(holder.navImg);
        holder.name.setText(newCategoryModels.get(position).getName());
        holder.description.setText(newCategoryModels.get(position).getDiscription());
        holder.discount.setText(newCategoryModels.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("type",newCategoryModels.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newCategoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView navImg;
        TextView name,description,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            navImg = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.nav_cat_name);
            description = itemView.findViewById(R.id.nav_cat_descrip);
            discount = itemView.findViewById(R.id.nav_cat_discount);
        }
    }
}
