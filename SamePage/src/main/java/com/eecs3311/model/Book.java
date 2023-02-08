package com.eecs3311.model;

import java.util.ArrayList;

public class Book implements Product{
    private String title;
    private String description;
    private ArrayList<String> reviews;
    private int ISBN;
    private String author;

    //genre is enum

    // TODO initialize constructor
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public int getISBN(){
        return ISBN;
    }

    public String getAuthor(){
        return author;
    }
}
