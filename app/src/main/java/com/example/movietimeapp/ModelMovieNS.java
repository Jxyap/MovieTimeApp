package com.example.movietimeapp;

public class ModelMovieNS {

    String name, nsDescription, nsInfo, photo;

    public ModelMovieNS() {
    }

    public ModelMovieNS(String name, String nsDescription, String nsInfo, String photo) {
        this.name = name;
        this.nsDescription = nsDescription;
        this.nsInfo = nsInfo;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNsDescription() {
        return nsDescription;
    }

    public void setNsDescription(String nsDescription) {
        this.nsDescription = nsDescription;
    }

    public String getNsInfo() {
        return nsInfo;
    }

    public void setNsInfo(String nsInfo) {
        this.nsInfo = nsInfo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
