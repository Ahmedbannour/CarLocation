package com.example.carloc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.carloc.Models.Car;

public class DetailsActivity  extends AppCompatActivity {

    TextView owner , price , power , model , availability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        getWindow().setStatusBarColor(ContextCompat.getColor(DetailsActivity.this , R.color.black));
        Car car =(Car) getIntent().getSerializableExtra("car");
        owner = findViewById(R.id.owner);
        price = findViewById(R.id.price_detail);
        power = findViewById(R.id.power);
        model = findViewById(R.id.model_detail);
        availability = findViewById(R.id.availibility);

        owner.setText("AutoRental");
        model.setText(car.getLabel());
        price.setText(car.getPrice()+" DT");
        power.setText(car.getDescription());
        availability.setText("available");



    }
}
