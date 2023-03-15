package com.eecs3311.model.Book;

import com.eecs3311.presenter.Book.IBookPresenter;

// Interface for accessing data of a book object
public interface IBookModel {
    String getTitle();

    String getAuthor();

    String getISBN();

    String getGenre();

    void setPresenter(IBookPresenter bookPresenter);

    IBookPresenter getPresenter();

    String getDescription();

    String getImg();

    /**
     * Get likes from DB
     * @return int like number
     */
    int getLikes();

    /**
     * Set the likes in the db
     * @param num like amount
     */
    void setLikes(int num);

    /**
     * Add this book to the favorites table in the database to maintain updated favorite
     * list for the logged in user
     */
    void addFavoriteBook();

    /**
     * Remove this book from the favorites table in the database to maintain updated favorite
     * list for the logged-in user
    */
    void removeFavoriteBook();

    /**
     * Checks ArrayList of IBookModels in FavBooksDB to see if this books ISBN matches
     * an existing book in the current users favorite books list
     *
     * @return boolean value for book favorite status
     */
    boolean checkFavoriteBook();

    double getAverageReview();
}
