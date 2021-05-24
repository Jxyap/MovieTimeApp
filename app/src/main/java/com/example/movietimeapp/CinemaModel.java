package com.example.movietimeapp;

public class CinemaModel {

    String name, info, photo;

    public CinemaModel() {
    }

    public CinemaModel(String name, String info, String photo) {
        this.name = name;
        this.info = info;
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
