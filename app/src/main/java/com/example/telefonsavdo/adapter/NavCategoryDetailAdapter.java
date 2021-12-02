package com.example.telefonsavdo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telefonsavdo.R;
import com.example.telefonsavdo.activity.DetaileActivity;
import com.example.telefonsavdo.model.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class NavCategoryDetailAdapter extends RecyclerView.Adapter<NavCategoryDetailAdapter.ViewHolder> {

    Context context;
    List<NavCategoryDetailedModel> detailedModelList;
    FirebaseDatabase database;
    FirebaseAuth auth;
    int totalQuantity = 1;
    int totalPrice = 0;
    FirebaseFirestore firestore;

//    NavCategoryDetailedModel detailedModel = null;

    public NavCategoryDetailAdapter(Context context, List<NavCategoryDetailedModel> detailedModelList) {
        this.context = context;
        this.detailedModelList = detailedModelList;
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detaile_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(detailedModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(detailedModelList.get(position).getName());
        holder.price.setText("Price :"+detailedModelList.get(position).getPrice()+"$");



        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10){
                    totalQuantity++;
                    holder.quentetiy.setText(String.valueOf(totalQuantity));
                    totalPrice = detailedModelList.get(position).getPrice()* totalQuantity;
                }
            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1){
                    totalQuantity--;
                    holder.quentetiy.setText(String.valueOf(totalQuantity));
                    totalPrice = detailedModelList.get(position).getPrice()* totalQuantity;
                }
            }
        });

        holder.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveCurrentData,saveCurrentTime;
                Calendar calForData  = Calendar.getInstance();

                SimpleDateFormat currentData = new SimpleDateFormat("MM dd, yyyy");
                saveCurrentData = currentData.format(calForData.getTime());

                SimpleDateFormat currentTime  = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calForData.getTime());

                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("productName", detailedModelList.get(position).getName());
                cartMap.put("productPrice",holder.price.getText().toString());
                cartMap.put("currentData",saveCurrentData);
                cartMap.put("currentTime",saveCurrentTime);
                cartMap.put("totalQuantity",holder.quentetiy.getText().toString());
                cartMap.put("totalPrice",totalPrice);

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(context, "Added To A Cart", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return detailedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,addItem,removeItem;
        TextView name,price,quentetiy;
        MaterialButton buyNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.nav_detail_img);
            name = itemView.findViewById(R.id.nav_detail_name);
            price = itemView.findViewById(R.id.nav_detail_price);
            addItem = itemView.findViewById(R.id.add_item);
            removeItem = itemView.findViewById(R.id.remove_item);
            quentetiy = itemView.findViewById(R.id.qeantity);
            buyNow = itemView.findViewById(R.id.baynow);

        }
    }
}
