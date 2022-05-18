package com.example.myapplication;

public class CardHelperClass {

    String  cardNumber, month, year,
            firstName, lastName, cardType;

    public CardHelperClass() {

    }

    public CardHelperClass(String cardNumber, String month, String year, String firstName, String lastName, String cardType) {
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardType = cardType;
    }

    public String getCardNumber() { return cardNumber; }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
