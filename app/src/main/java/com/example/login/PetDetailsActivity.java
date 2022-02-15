package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PetDetailsActivity extends AppCompatActivity {
    private TextView detailsprice, detailslocation,detailsdescription,detailsage,detailsgender,detailscontactnum,detailscategory;
    private ImageView ivPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        connectComponents();
        Intent i = this.getIntent();
        Pet pet= (Pet) i.getSerializableExtra("pet");
        detailsprice.setText(pet.getPrice());
        detailsdescription.setText(pet.getDescription());
       detailslocation.setText(pet.getLocation());
        detailscategory.setText(pet.getCategory());
        detailscontactnum.setText(pet.getContactnum());
        Picasso.get().load(pet.getPhoto()).into(ivPhoto);
    }

    private void connectComponents() {
        detailsprice = findViewById(R.id.tvNameRestDetails);
        detailsdescription = findViewById(R.id.tvDescriptionRestDetails);
        detailslocation = findViewById(R.id.tvAddressRestDetails);
        detailscategory = findViewById(R.id.tvCategoryRestDetails);
        detailscontactnum = findViewById(R.id.tvPhoneRestDetails);
        ivPhoto = findViewById(R.id.ivPhotoRestDetails);
    }
}