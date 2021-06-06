package com.example.movietimeapp;

public class ModelTicket {
    String movie, date, time, poster, cinemaName;

    public ModelTicket() {
    }

    public ModelTicket(String movie, String date, String time, String poster, String cinemaName) {
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.poster = poster;
        this.cinemaName = cinemaName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
