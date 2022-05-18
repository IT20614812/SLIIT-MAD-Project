package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {


   EditText emailID, password;
   CheckBox privacyPolicy;
   Button btnSignUp, btnLoginScreen;
   FirebaseAuth mfirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mfirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        privacyPolicy = findViewById(R.id.privacyPolicy);
        btnSignUp = findViewById(R.id.signUp);
        btnLoginScreen = findViewById(R.id.loginScreen);
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()) {
                    emailID.setError("Please Provide an Email Address");
                    emailID.requestFocus();
                }
                else if(pwd.isEmpty()) {
                    password.setError("Please Provide a Password");
                    password.requestFocus();
                }
                else if(!privacyPolicy.isChecked()){
                    privacyPolicy.setError("You need to consent to our Privacy Policy before proceeding");
                    privacyPolicy.requestFocus();
                }
                else if (!(email.isEmpty()) || pwd.isEmpty() || !privacyPolicy.isChecked()){
                        mfirebaseAuth.createUserWithEmailAndPassword(email, pwd)
                                .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(signup.this, "Sign up Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                                        }  else {
                                            Intent intHomePage = new Intent(signup.this, tempHome.class);
                                            startActivity(intHomePage);
                                        }
                                    }
                                });

                }
                else {
                    Toast.makeText(signup.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intLogin = new Intent(signup.this, login.class);
                startActivity(intLogin);
            }
        });

    }
}