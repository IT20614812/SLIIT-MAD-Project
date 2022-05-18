package com.example.booknowapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String theater;
    private String movie;
    private String date;
    private String time;
    private String tickets;


    public User() {
        this.theater = theater;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.tickets = tickets;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getTheater() {
        return theater;
    }

    public String getMovie() {
        return movie;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTickets() {
        return tickets;
    }
}
