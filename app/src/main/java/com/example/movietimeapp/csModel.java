package com.example.movietimeapp;

public class csModel {

    String name, csDescription, csInfo, photo;

    public csModel() {
    }

    public csModel(String name, String csDescription, String csInfo, String photo) {
        this.name = name;
        this.csDescription = csDescription;
        this.csInfo = csInfo;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCsDescription() {
        return csDescription;
    }

    public void setCsDescription(String csDescription) {
        this.csDescription = csDescription;
    }

    public String getCsInfo() {
        return csInfo;
    }

    public void setCsInfo(String csInfo) {
        this.csInfo = csInfo;
    }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
