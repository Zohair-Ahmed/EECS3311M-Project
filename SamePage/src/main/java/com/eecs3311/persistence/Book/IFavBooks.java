package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFavBooks {

    /**
     * Adds a new book to the favorite DB
     *
     * @param book
     */
    void addBook(IBookModel book);

    /**
     * Removes a new book from the favorite DB
     *
     * @param book
     */
    void removeFromFavorites(IBookModel book);

    /**
     * Retrieves all favorite books for the logged-in user and stores them in a IBookModel ArraylList
     */
    void getDBdata();

    /**
     * Returns the list of favorite books
     *
     * @return list of book models in the favorites list of the logged-in user
     */
    ArrayList<IBookModel> getFavBooks();

}
