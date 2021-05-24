package com.example.movietimeapp;

public class cMovieModel {

    private String movie, poster;

    public cMovieModel() {
    }

    public cMovieModel(String movie, String poster) {
        this.movie = movie;
        this.poster = poster;
    }

    public String getMovie() {
        return movie;
    }

    public String getPoster() {
        return poster;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
