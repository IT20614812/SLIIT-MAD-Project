package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class tempHome extends AppCompatActivity {

    Button btnLogout, btnEnterDetail;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_home);

        btnLogout = findViewById(R.id.logout);
        btnEnterDetail = findViewById(R.id.enterCard);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intToLogin = new Intent(tempHome.this, login.class);
                startActivity(intToLogin);
            }
        });
        btnEnterDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intToEnterCardDetails = new Intent(tempHome.this, EnterCardDetails.class);
                startActivity(intToEnterCardDetails);
            }
        });


    }
}