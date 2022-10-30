package com.example.carloc;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carloc.Adapters.RecycleViewAdapter;
import com.example.carloc.Models.Car;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Car> carList;
    RecycleViewAdapter adapter;
    int [] imgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        getWindow().setStatusBarColor(ContextCompat.getColor(HomeActivity.this , R.color.black));

        initData();
        initRecyclerView();


    }

    private void initData() {
        carList = new ArrayList<>();
        imgs = new int[]{R.drawable.slide1,R.drawable.slide2};
        carList.add(new Car(1,imgs,"Ahmed","Car",80000));
        carList.add(new Car(2,imgs,"BMW","Serie 2022",1700));
        carList.add(new Car(3,imgs,"BENZ","R8",2500));
        carList.add(new Car(4,imgs,"REUS","Model 2021",3500));

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.carSlider);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(carList);
        recyclerView.setAdapter(adapter);
    }

}