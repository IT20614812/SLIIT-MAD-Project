package com.example.booknowapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Booking extends AppCompatActivity {

    private String selectTheater, selectTime, selectTickets, selectMovie;
    private TextView tvTheater, tvTime, tvTickets, tvMovie;

    private Spinner theaterSpinner, timeSpinner, ticketsSpinner, movieSpinner;
    private ArrayAdapter<CharSequence> theaterAdapter, timeAdapter, ticketsAdapter, movieAdapter;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    private Button btnsave;
    DatabaseReference databaseReference;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        iniDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodayDate());

        //State spinner initialisation
        theaterSpinner = findViewById(R.id.spinner_theater);
        timeSpinner = findViewById(R.id.spinner_time);
        ticketsSpinner = findViewById(R.id.spinner_ticket);
        movieSpinner = findViewById(R.id.spinner_movie);

        //populate
        theaterAdapter = ArrayAdapter.createFromResource(this, R.array.array_theater, R.layout.spinner_layout);
        timeAdapter = ArrayAdapter.createFromResource(this, R.array.array_time, R.layout.spinner_layout);
        ticketsAdapter = ArrayAdapter.createFromResource(this, R.array.array_ticket, R.layout.spinner_layout);
        movieAdapter = ArrayAdapter.createFromResource(this, R.array.array_movie, R.layout.spinner_layout);

        //specify
        theaterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ticketsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set adapter
        theaterSpinner.setAdapter(theaterAdapter);
        timeSpinner.setAdapter(timeAdapter);
        ticketsSpinner.setAdapter(ticketsAdapter);
        movieSpinner.setAdapter(movieAdapter);

        btnsave = (Button) findViewById(R.id.button3);
        databaseReference = FirebaseDatabase.getInstance().getReference("MovieTicket");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String movie = movieSpinner.getSelectedItem().toString();
                String theater = theaterSpinner.getSelectedItem().toString();
                String time = timeSpinner.getSelectedItem().toString();
                String amount = ticketsSpinner.getSelectedItem().toString();


                Tickets helperClass = new Tickets(movie, theater, time, amount);

                databaseReference.setValue(helperClass);
            }
        });

    }


    private String getTodayDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void iniDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {

        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "PCT";
        if (month == 11)
            return "NOC";
        if (month == 12)
            return "DEC";

        return "JAN";
    }

    public void openDatePicker(View view) {

        datePickerDialog.show();

        }
    }