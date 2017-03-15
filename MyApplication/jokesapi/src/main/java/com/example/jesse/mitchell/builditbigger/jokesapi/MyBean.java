package com.example.jesse.mitchell.builditbigger.jokesapi;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    MyBean(){
    }

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}