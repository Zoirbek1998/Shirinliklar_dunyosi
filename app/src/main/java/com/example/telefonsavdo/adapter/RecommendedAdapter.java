package com.example.telefonsavdo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telefonsavdo.R;
import com.example.telefonsavdo.model.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {

    private Context context;
    private List<RecommendedModel> recommendedModels;

    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModels) {
        this.context = context;
        this.recommendedModels = recommendedModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(recommendedModels.get(position).getImg_url()).into(holder.recImg);
        holder.recName.setText(recommendedModels.get(position).getName());
        holder.recDec.setText(recommendedModels.get(position).getDescription());
        holder.recRating.setText(recommendedModels.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return recommendedModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recImg;
        TextView recName,recDec,recRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recImg = itemView.findViewById(R.id.rec_img);
            recName = itemView.findViewById(R.id.rec_name);
            recDec = itemView.findViewById(R.id.rec_dec);
            recRating = itemView.findViewById(R.id.rec_rating);

        }
    }
}
