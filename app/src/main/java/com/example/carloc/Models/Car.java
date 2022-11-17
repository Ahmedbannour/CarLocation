package com.example.carloc.Models;

import java.io.Serializable;
import java.util.Date;

public class Car implements Serializable {
    private int id ;
    private String [] images;
    private  String label;
    private  String description;
    private  int price ;

    private Boolean recu;

    public Boolean getRecu() {
        return recu;
    }

    public void setRecu(Boolean recu) {
        this.recu = recu;
    }



    public Car(int id, String [] images, String label, String description, int price) {
        this.id = id;
        this.images = images;
        this.label = label;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String [] getImages() {
        return images;
    }

    public void setImages(String [] images) {
        this.images = images;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
