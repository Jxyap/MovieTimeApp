package com.example.movietimeapp;

public class ModelCinema {

    String name, info, photo;

    public ModelCinema() {
    }

    public ModelCinema(String name, String info, String photo) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
