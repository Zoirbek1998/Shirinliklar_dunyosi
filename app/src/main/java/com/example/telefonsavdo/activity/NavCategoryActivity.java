package com.example.telefonsavdo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.telefonsavdo.R;
import com.example.telefonsavdo.adapter.NavCategoryDetailAdapter;
import com.example.telefonsavdo.model.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> detailedModelList;
    NavCategoryDetailAdapter adapter;
    FirebaseFirestore db;
    ProgressBar progressBar;
    ImageView detailBak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();

        progressBar = findViewById(R.id.progressbar_detail);
        progressBar.setVisibility(View.VISIBLE);
        detailBak = findViewById(R.id.detail_back);

        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.nav_cat_dest_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedModelList = new ArrayList<>();
        adapter = new NavCategoryDetailAdapter(this,detailedModelList);
        recyclerView.setAdapter(adapter);

        detailBak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (type != null && type.equalsIgnoreCase("biscuits")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "biscuits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel detailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        detailedModelList.add(detailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("chocolate")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "chocolate").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel detailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        detailedModelList.add(detailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("ice-cream")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "ice-cream").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel detailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        detailedModelList.add(detailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("waffles")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "waffles").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel detailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        detailedModelList.add(detailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("cake")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "cake").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel detailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        detailedModelList.add(detailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("sweets")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "sweets").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel detailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        detailedModelList.add(detailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }
}