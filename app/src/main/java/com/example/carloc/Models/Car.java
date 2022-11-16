package com.example.carloc.Models;

import java.util.Date;

public class Car {
    private int id ;
    private int [] images;
    private  String label;
    private  String description;
    private  int price ;
    private Date dateDepart;
    private Date dateRetour;
    private Boolean recu;

    public Boolean getRecu() {
        return recu;
    }

    public void setRecu(Boolean recu) {
        this.recu = recu;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Car(int id, int [] images, String label, String description, int price) {
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

    public int [] getImages() {
        return images;
    }

    public void setImages(int [] images) {
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
