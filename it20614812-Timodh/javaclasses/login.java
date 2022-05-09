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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText emailID, password;
    Button btnSignUpScreen, btnLogin;
    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mfirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword2);
        btnSignUpScreen = findViewById(R.id.signup2);
        btnLogin = findViewById(R.id.login2);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mfirebaseAuth.getCurrentUser();

                if(mFirebaseUser != null){
                    Toast.makeText(login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, tempHome.class);
                    startActivity(i);
                } else {
                    Toast.makeText(login.this, "Please Login", Toast.LENGTH_SHORT).show();
                }

            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
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

                else if (!(email.isEmpty()) || pwd.isEmpty()){
                    mfirebaseAuth.signInWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(login.this, "Login up Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                                    }  else {
                                        Intent intHomePage = new Intent(login.this, tempHome.class);
                                        startActivity(intHomePage);
                                    }
                                }
                            });

                }
                else {
                    Toast.makeText(login.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnSignUpScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intSignUp = new Intent(login.this, signup.class);
                startActivity(intSignUp);
            }
        });
    }

    protected void onStart(){
        super.onStart();
        mfirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}