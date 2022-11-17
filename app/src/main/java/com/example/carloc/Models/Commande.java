package com.example.carloc.Models;

import java.util.Date;

public class Commande {

    private int id ;
    private int id_car;
    private  int id_user;
    private Date date_debut;
    private Date date_fin;

    public Commande(int id, int id_car, int id_user, Date date_debut, Date date_fin) {
        this.id = id;
        this.id_car = id_car;
        this.id_user = id_user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
}
