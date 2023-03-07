package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFavBooks {

    /*
    Adds a new book to the favorite DB
     */
    void addBook(IBookModel book);

    void removeFromFavorites(IBookModel book);

    /*
    Retrieves all favorite books for the logged in user and stores them in a BookModel list
     */
    void getDBdata();

    /*
    Returns the list of favorite books
     */
    ArrayList<IBookModel> getFavBooks();

}
