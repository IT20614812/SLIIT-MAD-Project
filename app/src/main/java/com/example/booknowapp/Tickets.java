package com.example.booknowapp;

public class Tickets {

    private String theaterSpinner, timeSpinner, ticketsSpinner, movieSpinner;

    public Tickets(String movie, String theater, String time, String amount){

        this.movieSpinner = movie;
        this.theaterSpinner = theater;
        this.timeSpinner = time;
        this.ticketsSpinner = amount;
    }

    public String getTheaterSpinner() {
        return theaterSpinner;
    }

    public void setTheaterSpinner(String theaterSpinner) {
        this.theaterSpinner = theaterSpinner;
    }

    public String getTimeSpinner() {
        return timeSpinner;
    }

    public void setTimeSpinner(String timeSpinner) {
        this.timeSpinner = timeSpinner;
    }

    public String getTicketsSpinner() {
        return ticketsSpinner;
    }

    public void setTicketsSpinner(String ticketsSpinner) {
        this.ticketsSpinner = ticketsSpinner;
    }

    public String getMovieSpinner() {
        return movieSpinner;
    }

    public void setMovieSpinner(String movieSpinner) {
        this.movieSpinner = movieSpinner;
    }
}
