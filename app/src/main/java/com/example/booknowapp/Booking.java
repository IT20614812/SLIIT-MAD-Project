package com.example.booknowapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Booking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    TextView selectedTheater, selectedMovie, selectedTime, selectedTicket;
    private Spinner theaterSpinner, movieSpinner, timeSpinner, ticketSpinner;

    Button bookbtn;
    FirebaseDatabase database;
    DatabaseReference reference;
    User user;
    int maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_booking);

        bookbtn = findViewById(R.id.bookbtn);
        user = new User();
        reference = database.getInstance().getReference().child("movies");

        selectedTheater = findViewById(R.id.selectedTheater);
        selectedMovie = findViewById(R.id.selectedMovie);
        selectedTime = findViewById(R.id.selectedTime);
        selectedTicket = findViewById(R.id.selectedTicket);

        theaterSpinner = findViewById(R.id.theaterSpinner);
        movieSpinner = findViewById(R.id.movieSpinner);
        timeSpinner = findViewById(R.id.timeSpinner);
        ticketSpinner = findViewById(R.id.ticketSpinner);

        List<String> Categories = new ArrayList<>();
        Categories.add(0, "Choose Theater");
        Categories.add("Liberty By Scope Cinemas: Colombo");
        Categories.add("PVR Cinema: LUXE One Galle Face Mall");
        Categories.add("Scope Cinemas Dolby Atmos: CC");
        Categories.add("Scope Cinemas Gold Class: CCC");
        Categories.add("Savoy 3D Cinema: Wellawatta");
        Categories.add("Scope Cinemas: Colombo City Center");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        theaterSpinner.setAdapter(dataAdapter);

        theaterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Theater")) {

                } else {
                    selectedTheater.setText(adapterView.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                if(datasnapshot.exists()) {
                    maxid = (int) datasnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
            bookbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    user.setTheater(theaterSpinner.getSelectedItem().toString());
                    Toast.makeText(Booking.this,"Ticket Bokked", Toast.LENGTH_LONG).show();

                    reference.child(String.valueOf(maxid+1)).setValue(user);

                }
            });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}