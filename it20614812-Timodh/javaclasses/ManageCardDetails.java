package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class ManageCardDetails extends AppCompatActivity {

    EditText cardHolder;
    DatabaseReference Reference = FirebaseDatabase.getInstance().getReference("PaymentDetails");
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_card_details);

        cardHolder.findViewById(R.id.cardHolderInsert);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Query getPaymentDetail = Reference.orderByChild("FirstName").equalTo(currentUser.getEmail());

        getPaymentDetail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    String FirstNameFromDB = snapshot.child(currentUser.getEmail()).child("FirstName").getValue(String.class);
                    cardHolder.setText(FirstNameFromDB);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //onclicklistener to go to viewcarddetails


    }
}