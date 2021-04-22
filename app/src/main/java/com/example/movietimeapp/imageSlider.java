package com.example.movietimeapp;

public class imageSlider {
    //Here you can use String variable to store url
    //if you want to load image form internet
    private int image;

    imageSlider(int image){
        this.image =image;
    }

    public int getImage(){
        return image;
    }
}