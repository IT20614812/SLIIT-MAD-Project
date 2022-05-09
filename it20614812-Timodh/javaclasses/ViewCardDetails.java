package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewCardDetails extends AppCompatActivity {

    TextView cardNumber, firstName, lastName, cardType;
    Button btnEdit, btnRemove;
    DatabaseReference Reference = FirebaseDatabase.getInstance().getReference("PaymentDetails");
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_card_details);

        cardNumber = findViewById(R.id.viewCardNumber);
        firstName = findViewById(R.id.viewFirstName);
        lastName = findViewById(R.id.viewLastName);
        cardType = findViewById(R.id.viewCardType);
        btnEdit = findViewById(R.id.viewCardEdit);
        btnRemove = findViewById(R.id.viewCardRemove);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Query getPaymentDetails = Reference.equalTo(currentUser.getEmail());

        getPaymentDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    String CardNumberFromDB = snapshot.child(currentUser.getEmail()).child("CArdNumber").getValue(String.class);
                    String FirstNameFromDB = snapshot.child(currentUser.getEmail()).child("FirstName").getValue(String.class);
                    String LastNameFromDB = snapshot.child(currentUser.getEmail()).child("LastName").getValue(String.class);
                    String CardTypeFromDB = snapshot.child(currentUser.getEmail()).child("CardType").getValue(String.class);

                    cardNumber.setText(CardNumberFromDB);
                    firstName.setText(FirstNameFromDB);
                    lastName.setText(LastNameFromDB);
                    cardType.setText(CardTypeFromDB);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}