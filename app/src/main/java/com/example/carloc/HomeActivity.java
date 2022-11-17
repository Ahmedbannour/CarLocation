package com.example.carloc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carloc.Adapters.RecycleViewAdapter;
import com.example.carloc.Adapters.RecycleViewInterface;
import com.example.carloc.Models.Car;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity  implements RecycleViewInterface{

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Car> carList;
    List<Car> loadData;
    RecycleViewAdapter adapter;
    DatabaseReference dataRef;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DataSnapshot snapshot;
    RelativeLayout item;
    List<String> imgs = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataRef = FirebaseDatabase.getInstance().getReference("Car");
        setContentView(R.layout.home_page);
        getWindow().setStatusBarColor(ContextCompat.getColor(HomeActivity.this , R.color.black));
        initData();
        initRecyclerView();
    }

    private void initData() {
        carList = new ArrayList<>();
        loadData = new ArrayList<>();
        /*
        carList.add(new Car(1,new String[]{"https://images.pexels.com/photos/116675/pexels-photo-116675.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","https://images.pexels.com/photos/1035108/pexels-photo-1035108.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"},"Ahmed","Car",80000));
        carList.add(new Car(2,new String[]{"https://images.pexels.com/photos/116675/pexels-photo-116675.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","https://images.pexels.com/photos/1035108/pexels-photo-1035108.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"},"BMW","Serie 2022",1700));
        carList.add(new Car(3,new String[]{"https://images.pexels.com/photos/116675/pexels-photo-116675.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","https://images.pexels.com/photos/1035108/pexels-photo-1035108.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"},"BENZ","R8",2500));
        carList.add(new Car(4,new String[]{"https://images.pexels.com/photos/116675/pexels-photo-116675.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","https://images.pexels.com/photos/1035108/pexels-photo-1035108.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"},"REUS","Model 2021",3500));
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Car");
        for(Car car : carList) {
            ArrayList <String> imgs = new ArrayList<>();
            db.child(String.valueOf(car.getId())).child("label").setValue(car.getLabel());
            db.child(String.valueOf(car.getId())).child("description").setValue(car.getDescription());
            db.child(String.valueOf(car.getId())).child("prix").setValue(car.getPrice());
            for(int i = 0 ; i < car.getImages().length ; i++){
                db.child(String.valueOf(car.getId())).child("images").child(String.valueOf(i)).setValue((String)car.getImages()[i]);
            }
        }
         */
    }

    private void initRecyclerView() {
        loadData = new ArrayList<>();
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Log.i("id", "id: "+dataSnapshot.getKey());
                    Log.i("label", "label: "+dataSnapshot.child("label").getValue());
                    Log.i("description", "description: "+dataSnapshot.child("description").getValue());
                    Log.i("prix", "prix: "+dataSnapshot.child("prix").getValue());
                    imgs.clear();
                    for(int i = 0 ; i < dataSnapshot.child("images").getChildrenCount(); i++){
                        imgs.add(dataSnapshot.child("images").child(String.valueOf(i)).getValue().toString());
                    }
                    String[] arr = new String[imgs.size()];

                    arr = imgs.toArray(arr);
                    loadData.add(new Car(Integer.valueOf(dataSnapshot.getKey()) , arr, dataSnapshot.child("label").getValue().toString() , dataSnapshot.child("description").getValue().toString(),Integer.valueOf(dataSnapshot.child("prix").getValue().toString())));

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = findViewById(R.id.carSlider);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(loadData , this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(int position, Car car) {
        Log.i("item "+car.getId(), "item "+car.getLabel() + "prix: "+car.getPrice()+" , image : " + car.getImages()[0]);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("car" , (Serializable) car);
        startActivity(intent);
    }
}