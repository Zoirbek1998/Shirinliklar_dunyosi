package com.example.telefonsavdo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telefonsavdo.R;
import com.example.telefonsavdo.adapter.NawCategoryAdapter;
import com.example.telefonsavdo.adapter.PopulyarAdapter;
import com.example.telefonsavdo.model.NewCategoryModel;
import com.example.telefonsavdo.model.PopulyarModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    RecyclerView newRecycler;
    FirebaseFirestore db;
    NawCategoryAdapter nawCategoryAdapter;
    List<NewCategoryModel> newCategoryModelList;

    ProgressBar progressBar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);

        db = FirebaseFirestore.getInstance();
        newRecycler = root.findViewById(R.id.cat_rec);
        newRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        newCategoryModelList = new ArrayList<>();
        nawCategoryAdapter = new NawCategoryAdapter(getActivity(),newCategoryModelList);
        newRecycler.setAdapter(nawCategoryAdapter);
        progressBar = root.findViewById(R.id.progressbar_cat);
        progressBar.setVisibility(View.VISIBLE);
        newRecycler.setVisibility(View.GONE);


        db.collection("NewCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewCategoryModel newCategoryModel = document.toObject(NewCategoryModel.class);
                                newCategoryModelList.add(newCategoryModel);
                                nawCategoryAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                newRecycler.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            newRecycler.setVisibility(View.VISIBLE);
                        }
                    }
                });





        return root;
    }


}