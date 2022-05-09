package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterCardDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText cardNumber, firstName, lastName;
    Spinner cardType, month, year;
    Button btnSave;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_card_details);

        Spinner month_spinner =findViewById(R.id.month);
        Spinner year_spinner =findViewById(R.id.year);
        Spinner card_type_spinner =findViewById(R.id.cardType);

        ArrayAdapter<CharSequence> month_adapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        month_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> year_adapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> card_type_adapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        card_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        month_spinner.setAdapter(month_adapter);
        month_spinner.setOnItemSelectedListener(this);

        year_spinner.setAdapter(year_adapter);
        year_spinner.setOnItemSelectedListener(this);

        card_type_spinner.setAdapter(card_type_adapter);
        card_type_spinner.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        cardNumber = findViewById(R.id.cardNumber);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        cardType = findViewById(R.id.cardType);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        btnSave = findViewById(R.id.cardSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference( "PaymentDetails");

                String number = cardNumber.getEditableText().toString();
                String fName = firstName.getEditableText().toString();
                String lName = lastName.getEditableText().toString();
                String cType = cardType.getSelectedItem().toString();
                String dateMonth = month.getSelectedItem().toString();
                String dateYear = year.getSelectedItem().toString();



                CardHelperClass helperClass = new CardHelperClass(number, fName, lName, cType, dateMonth, dateYear);

                reference.child(currentUser.getEmail()).setValue(helperClass);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
       // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}