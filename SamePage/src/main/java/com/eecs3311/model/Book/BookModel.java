package com.eecs3311.model.Book;

import com.eecs3311.model.Review.ReviewModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Book.IBookPresenter;

import java.util.ArrayList;

public class BookModel implements IBookModel {
    private String title;
    private String description;
    private ArrayList<ReviewModel> reviews;
    private String ISBN;
    private String author;
    private String img;
    private String genre;
    private double averageReview = 0;
    private IBookPresenter bookPresenter;

    public BookModel(String title, String author, String description, ArrayList<ReviewModel> reviews, String ISBN,
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

    public ArrayList<ReviewModel> getReviews() {
        return reviews;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReviews(ArrayList<ReviewModel> reviews) {
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

    @Override
    public double getAverageReview() {
        return Database.getReviewInstance().getAverageRating(getISBN());
    }

}