package com.example.movietimeapp;

public class ModelNSTime {

    private String cinema, photo, date1, date2, date3, time1, time2,time3, time4, time5, time6, time7, time8, time9;
    boolean expand;

    public ModelNSTime() {
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public ModelNSTime(String cinema, String photo, String date1, String date2, String date3,
                       String time1, String time2, String time3, String time4, String time5,
                       String time6, String time7, String time8, String time9) {
        this.cinema = cinema;
        this.photo = photo;
        this.date1 = date1;
        this.date2 = date2;
        this.date3 = date3;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        this.time5 = time5;
        this.time6 = time6;
        this.time7 = time7;
        this.time8 = time8;
        this.time9 = time9;
        this.expand = false;
    }

    public ModelNSTime(String cinema, String photo, String date1, String date2, String time1, String time2) {
        this.cinema = cinema;
        this.photo = photo;
        this.date1 = date1;
        this.date2 = date2;
        this.time1 = time1;
        this.time2 = time2;
        this.expand = false;
    }

    public String getCinema() {
        return cinema;
    }

    public String getPhoto() {
        return photo;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getDate3() {
        return date3;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4;
    }

    public String getTime5() {
        return time5;
    }

    public void setTime5(String time5) {
        this.time5 = time5;
    }

    public String getTime6() {
        return time6;
    }

    public void setTime6(String time6) {
        this.time6 = time6;
    }

    public String getTime7() {
        return time7;
    }

    public void setTime7(String time7) {
        this.time7 = time7;
    }

    public String getTime8() {
        return time8;
    }

    public void setTime8(String time8) {
        this.time8 = time8;
    }

    public String getTime9() {
        return time9;
    }

    public void setTime9(String time9) {
        this.time9 = time9;
    }
}
