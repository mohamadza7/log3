package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllPet extends AppCompatActivity {
    private RecyclerView rvAllpet;
    AdapterPet adapter;
    FireBaseServices fbs;
    MyCallBack myCallback;
    ArrayList<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pet);

        fbs = FireBaseServices.getInstance();
        pets = new ArrayList<Pet>();
        readData();
        myCallback = new MyCallBack() {
            @Override
            public void onCallback(List<Pet> restsList) {
                RecyclerView recyclerView = findViewById(R.id.rvRestsAllRest);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new AdapterPet(getApplicationContext(), pets);
                recyclerView.setAdapter(adapter);
            }
        };


        // set up the RecyclerView
        /*
        RecyclerView recyclerView = findViewById(R.id.rvRestsAllRest);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterRestaurant(this, rests);
        recyclerView.setAdapter(adapter);*/
    }

    private void readData() {
        try {

            fbs.getFire().collection("restaurants")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    pets.add(document.toObject(Pet.class));
                                }

                                myCallback.onCallback(pets);
                            } else {
                                Log.e("AllRestActivity: readData()", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}