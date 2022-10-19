package com.example.carloc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {

    ViewPager slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        slider = (ViewPager) findViewById(R.id.slider);
        getWindow().setStatusBarColor(ContextCompat.getColor(HomeActivity.this , R.color.black));

        SlideAdapter slideAdapter = new SlideAdapter(this);

        slider.setAdapter(slideAdapter);
    }

}