package com.example.jesse.mitchell.builditbigger.jokesapi;

import java.util.List;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private List<String> myData;

    MyBean(){
    }

    public List<String> getData() {
        return myData;
    }

    public void setData(List<String> data) {
        myData = data;
    }
}