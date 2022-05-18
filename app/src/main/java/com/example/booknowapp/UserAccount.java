package com.example.booknowapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class UserAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_account);

        ListView listView = findViewById(R.id.listView);
        List<String> list = new ArrayList<>();
        list.add("Booked Movies");
        list.add("Booked Events");
        list.add("Manage Payments");
        list.add("Settings");
        list.add("Privacy Policy");
        list.add("Customer Support");
        list.add("Log Out");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position==0){
                   //clicked

                   startActivity(new Intent(UserAccount.this,BookedMovies.class));
               }else if(position==1){
                   //clicked
               }else {}
            }
        });

        //Initialize and assign varible
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //set Home
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        return true;

                    case R.id.movies:
                        startActivity(new Intent(getApplicationContext()
                                ,SelectedMovie.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}