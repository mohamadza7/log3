package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllPet extends AppCompatActivity {
    private RecyclerView rvAllRest;
    AdapterPet adapter;
    FireBaseServices fbs;
    ArrayList<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pet);

        fbs =  FireBaseServices.getInstance();
       pets = new ArrayList<Pet>();
        readData();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvpetsALLPET);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterPet(this, pets);
        recyclerView.setAdapter(adapter);
    }

    private void readData() {
        fbs.getFire().collection("restaurants")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                pets.add(document.toObject(Pet.class));
                            }
                        } else {
                            Log.e("AllPetActivity: readData()", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}