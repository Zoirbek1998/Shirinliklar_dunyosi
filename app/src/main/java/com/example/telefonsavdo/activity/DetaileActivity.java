package com.example.telefonsavdo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.telefonsavdo.R;
import com.example.telefonsavdo.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetaileActivity extends AppCompatActivity {

    ImageView detaileImg,addItem,removeItem;
    TextView price,rading,description,quantity;
    MaterialButton addToCart;
    ProgressBar progressBar;
    ImageView cardView;
    int totalQuantity = 1;
    int totalPrice = 0;

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile);

        initViews();
        initVars();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
            progressBar.setVisibility(View.GONE);
        }
        if (viewAllModel != null){
            progressBar.setVisibility(View.GONE);
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detaileImg);
            rading.setText(viewAllModel.getRating());
            price.setText("Price :$"+ viewAllModel.getPrice()+" ");
            description.setText(viewAllModel.getDescription());

            totalPrice = viewAllModel.getPrice()* totalQuantity;

        }
    }

    private void initVars() {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice()* totalQuantity;
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice()* totalQuantity;
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });
    }

    private void addedToCart() {

        String saveCurrentData,saveCurrentTime;
        Calendar calForData  = Calendar.getInstance();

        SimpleDateFormat currentData = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentData = currentData.format(calForData.getTime());

        SimpleDateFormat currentTime  = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForData.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentData",saveCurrentData);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetaileActivity.this, "Added To A Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void initViews() {

        detaileImg = findViewById(R.id.detal_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        rading = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.description);
        addToCart = findViewById(R.id.add_to_cart);
        quantity =findViewById(R.id.qeantity);

        progressBar = findViewById(R.id.detail_progressbar);
        progressBar.setVisibility(View.VISIBLE);
        cardView = findViewById(R.id.detail_back);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


    }
}