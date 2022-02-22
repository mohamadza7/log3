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

public class SignupActivity extends AppCompatActivity {
    private EditText etUsernamesignup, etpasswordsignup, etconfirmpass;
    private FirebaseAuth authsignup;
    private Utilities utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUsernamesignup = findViewById(R.id.username1);
        etpasswordsignup = findViewById(R.id.passwordsignup);
        etconfirmpass = findViewById(R.id.confirmpassword);
        authsignup = FirebaseAuth.getInstance();
        utils = Utilities.getInstance();

    }

    public void signup(View view) {
        String username = etUsernamesignup.getText().toString();
        String confirmpass = etconfirmpass.getText().toString();

        String password = etpasswordsignup.getText().toString();


        if (username.trim().isEmpty() || password.trim().isEmpty() || confirmpass.trim().isEmpty()) {

            Toast.makeText(this, "Username or password is missing!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!utils.emailIsTrue(this, username) || !utils.validatePassword(this, password)) {
            Toast.makeText(this, "Username or password is not valid!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmpass)) {
            Toast.makeText(this, "passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }



            authsignup.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(SignupActivity.this, AllPet.class);
                                startActivity(i);
                            } else {


                                Toast.makeText(SignupActivity.this, "Username or password is empty!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });

        }
    }

