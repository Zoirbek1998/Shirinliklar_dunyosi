package com.example.telefonsavdo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telefonsavdo.R;
import com.example.telefonsavdo.activity.PlacedOrderActivity;
import com.example.telefonsavdo.adapter.MyCartAdapter;
import com.example.telefonsavdo.model.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyCartsFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    MaterialButton buyNow;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> myCartModelList;
    TextView overTotalAmount;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_my_carts, container, false);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        progressBar = root.findViewById(R.id.progressbar_my_cart);
        progressBar.setVisibility(View.VISIBLE);
        constraintLayout = root.findViewById(R.id.constraint1);
        constraintLayout.setVisibility(View.VISIBLE);


        buyNow = root.findViewById(R.id.buy_now);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setVisibility(View.GONE);
        overTotalAmount = root.findViewById(R.id.overTotalAmount);


        myCartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getActivity(),myCartModelList);
        recyclerView.setAdapter(myCartAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (DocumentSnapshot snapshot : task.getResult().getDocuments()){

                        String documebtId = snapshot.getId();

                        MyCartModel myCartModel = snapshot.toObject(MyCartModel.class);

                        myCartModel.setDocumentId(documebtId);

                        myCartModelList.add(myCartModel);
                        myCartAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        constraintLayout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                    calculateTotalAmount(myCartModelList);

                }

            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                intent.putExtra("itemList",(Serializable) myCartModelList);
                startActivity(intent);
            }
        });

        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> myCartModelList) {

        double totalAmount = 0.0;
        for (MyCartModel myCartModel : myCartModelList){
            totalAmount += myCartModel.getTotalPrice();
        }
            overTotalAmount.setText("Total Amount :"+totalAmount);
    }

}