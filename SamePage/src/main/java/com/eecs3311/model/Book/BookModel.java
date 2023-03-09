package com.eecs3311.model.Book;

import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Book.IBookPresenter;

public class BookModel implements IBookModel {
    private final String title;
    private final String description;
    private final String ISBN;
    private final String author;
    private final String img;
    private final String genre;
    private IBookPresenter bookPresenter;

    public BookModel(String title, String author, String description, String ISBN,
                     String genre, String img) {
        this.title = title;
        this.author = author;
        this.description = description;
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

    public String getDescription() {
        return description;
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