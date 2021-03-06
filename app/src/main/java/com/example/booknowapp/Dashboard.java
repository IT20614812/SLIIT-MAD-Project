package com.example.booknowapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);

        //Initialize and assign varible
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //set Home
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                        ,UserAccount.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
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

        imageSlider = findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://www.youthjournalism.org/cms/assets/uploads/2022/03/Movie-FB-cover-The-Batman.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.fortressofsolitude.co.za/wp-content/uploads/2022/03/MRBS-1920x1080-1-750x375.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://in.bmscdn.com/iedb/movies/images/mobile/listing/xxlarge/ambulance-et00322252-18-02-2022-03-51-54.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.showcasecinemas.co.uk/media/5369/278176839_955907231674705_1948836435515095432_n.jpg", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Dashboard.this,SelectedMovie.class);
                startActivity(intent);
            }
        });


    }
}