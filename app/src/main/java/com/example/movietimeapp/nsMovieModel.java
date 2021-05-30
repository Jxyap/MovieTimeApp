package com.example.movietimeapp;

public class nsMovieModel {

    String name, photo;

    public nsMovieModel() {
    }

    public nsMovieModel(String name, String info, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
