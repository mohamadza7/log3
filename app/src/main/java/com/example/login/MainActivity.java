package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private FirebaseAuth auth;
    private Utilities utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername=findViewById(R.id.username1);
        etPassword=findViewById(R.id.password1);
        auth=FirebaseAuth.getInstance();
        utils = Utilities.getInstance();
    }
    public void StartSignUp(View view){
        Intent i = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(i);
    }


    public void login(View view) {
        String username= etUsername.getText().toString();
        String password= etPassword.getText().toString();

        if (username.trim().isEmpty() || password.trim().isEmpty())
        {
            Toast.makeText(this, "Username or password is missing!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!utils.emailIsTrue(this, username) || !utils.validatePassword(this,password)) {
            Toast.makeText(this, R.string.err_incorrect_user_password, Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(MainActivity.this, AllPet.class);
                            startActivity(i);

                        }
                        else {


                            Toast.makeText(MainActivity.this, "Username or password is empty!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

    }
    public void gotoSignup(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }

    public void gotoAddRest(View view) {
        Intent i = new Intent(this, PetAdd.class);
        startActivity(i);
    }

    public void gotoAllRests(View view) {
        Intent i = new Intent(this, AllPet.class);
        startActivity(i);
    }
}