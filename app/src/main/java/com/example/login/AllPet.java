package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
                RecyclerView recyclerView = findViewById(R.id.rvpetsALLPET);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new AdapterPet(getApplicationContext(), pets);
                recyclerView.setAdapter(adapter);
            }
        };

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("  PetApp");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
       }

        @Override
        public boolean onCreateOptionsMenu ( Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.toolbar, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item)
        {
            switch (item.getItemId()) {
                //case R.id.miSearch:
                // User chose the "Settings" item, show the app settings UI...
                //return true;

                case R.id.miProfile:
                    // User chose the "Favorite" action, mark the current item
                    // as a favorite...
                    return true;

                case R.id.miSettings:

                    return true;
                case R.id.misignout:
                    fbs.getAuth().signOut();

                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    return super.onOptionsItemSelected(item);

            }
        }

        public void readData() {
            try {
                fbs.getFire().collection("pets")
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
                                    Log.e("AllPet: readData()", "Error getting documents.", task.getException());
                                }
                            }
                        });
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    public void gotoAddPet(View view) {
        Intent i = new Intent(this, PetAdd.class);
        startActivity(i);
    }
}
