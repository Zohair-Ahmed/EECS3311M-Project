package com.eecs3311.model;

import com.eecs3311.model.enums.Genre;

import java.util.ArrayList;

public class Book implements Product{
    private String title = "Harry Potter and the Philosopher's Stone";
    private String description = "Harry Potter has never even heard of Hogwarts when the letters start dropping on " +
            "the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with " +
            "a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harry's " +
            "eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with " +
            "some astonishing news: Harry Potter is a wizard, and he has a place at Hogwarts School of " +
            "Witchcraft and Wizardry. An incredible adventure is about to begin!";
    private ArrayList<Reviews> reviews;
    private int ISBN = 1408855895;
    private String author = "J.K. Rowling";

    private Genre genre = Genre.FANTASY;

    // TODO initialize constructor
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
