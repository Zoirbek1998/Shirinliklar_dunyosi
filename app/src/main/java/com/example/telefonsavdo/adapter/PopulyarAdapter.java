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
import com.example.telefonsavdo.activity.ViewAllActivity;
import com.example.telefonsavdo.model.PopulyarModel;

import java.util.List;

public class PopulyarAdapter extends RecyclerView.Adapter<PopulyarAdapter.ViewHolder> {

    private Context context;
    private List<PopulyarModel> populyarModelList;

    public PopulyarAdapter(Context context, List<PopulyarModel> populyarModelList) {
        this.context = context;
        this.populyarModelList = populyarModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.populyar_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(populyarModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(populyarModelList.get(position).getName());
        holder.description.setText(populyarModelList.get(position).getDescription());
        holder.discount.setText(populyarModelList.get(position).getDiscount());
        holder.rating.setText(populyarModelList.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",populyarModelList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return populyarModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView popImg;
        TextView name,description,rating,discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            discount = itemView.findViewById(R.id.pop_discount);
            rating = itemView.findViewById(R.id.rating);

        }
    }
}
