package com.eecs3311.model;

import com.eecs3311.model.enums.Genre;
// import com.sun.tools.javac.jvm.Gen;

import java.util.ArrayList;

public class Book implements Product{
    private String title;
    private String description;
    private ArrayList<Reviews> reviews;
    private int ISBN;
    private String author;

    private Genre genre;

    public Book(String title, String author, String description, ArrayList<Reviews> reviews, int ISBN, Genre genre){
        this.title = title;
        this.author = author;
        this.description = description;
        this.reviews = reviews;
        this.ISBN = ISBN;
        this.genre = genre;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Reviews> getReviews() {
        return reviews;
    }

    public int getISBN(){
        return ISBN;
    }

    public String getAuthor(){
        return author;
    }

    public String getGenre(){
        return genre.toString();
    }
}
