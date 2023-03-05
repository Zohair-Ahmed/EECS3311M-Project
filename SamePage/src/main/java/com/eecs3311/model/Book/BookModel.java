package com.eecs3311.model.Book;

import com.eecs3311.model.Reviews;
import com.eecs3311.presenter.Book.IBookPresenter;

import java.util.ArrayList;

public class BookModel implements IBookModel {
    private String title;
    private String description;
    private ArrayList<Reviews> reviews;
    private String ISBN;
    private String author;
    private String img;
    private String genre;

    // Each Model class needs ONE Presenter class Interface
    private IBookPresenter bookPresenter;

    public BookModel(String title, String author, String description, ArrayList<Reviews> reviews, String ISBN,
                     String genre, String img) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.reviews = reviews;
        this.ISBN = ISBN;
        this.genre = genre;
        this.img = img;
    }

    @Override
    public void setPresenter(IBookPresenter bp) {
        this.bookPresenter = bp;
    }

    // Getters and Setters
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Reviews> getReviews() {
        return reviews;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReviews(ArrayList<Reviews> reviews) {
        this.reviews = reviews;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return this.getTitle() + "   " + this.getAuthor() + "   " + this.getISBN() + "   " + this.getGenre();
    }

    public String getImg() {
        return img;
    }

    @Override
    public IBookPresenter getPresenter() {
        return this.bookPresenter;
    }
}