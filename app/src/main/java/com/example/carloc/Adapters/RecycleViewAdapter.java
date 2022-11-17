package com.example.carloc.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.carloc.Models.Car;
import com.example.carloc.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter <RecycleViewAdapter.ViewHolder> {

    private List<Car> carList;
    private final RecycleViewInterface recycleViewInterface;

    public RecycleViewAdapter(List<Car> carList , RecycleViewInterface recycleViewInterface){
        this.carList = carList;
        this.recycleViewInterface = recycleViewInterface;
    }
    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view , recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        String[] rsc = carList.get(position).getImages();
        String label = carList.get(position).getLabel();
        String description = carList.get(position).getDescription();
        int price = carList.get(position).getPrice();
        holder.setData(rsc , label , description , price);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageSlider img;
        private TextView lab;
        private TextView prx;


        public ViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

            img = itemView.findViewById(R.id.slider);
            lab = itemView.findViewById(R.id.carModel);
            prx = itemView.findViewById(R.id.carPrice);

            itemView.findViewById(R.id.item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(RecycleViewAdapter.this.recycleViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            RecycleViewAdapter.this.recycleViewInterface.onItemClick(position,new Car(carList.get(position).getId(),carList.get(position).getImages(),carList.get(position).getLabel(),carList.get(position).getDescription(),carList.get(position).getPrice()));
                        }
                    }
                }
            });


        }

        public void setData(String[] rsc, String label, String description, int price) {
            ArrayList<SlideModel> slideModels = new ArrayList<>() ;
            for(int i=0 ; i<rsc.length ; i++){
                Log.i("ggggg", "setData: "+i);
                Log.i("TAG", "image : " + rsc[i]);
                slideModels.add(new SlideModel(rsc[i],ScaleTypes.CENTER_CROP));
            }
            img.setImageList(slideModels, ScaleTypes.CENTER_CROP);
            lab.setText(label);
            prx.setText(String.valueOf(price) + " DT");
        }
    }
}
