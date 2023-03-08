package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBook {

    /**
     * Returns the bookList with all latest books in DB
     * @return list of book models
     */
    ArrayList<IBookModel> getLatestReleases();

    /**
     * Get the existing book data from database and pass it to Book Model
     */
    void getDBdata();

    /**
     * To add the books from the db to a list
     * @param info info
     */
    void addToList(ArrayList<IBookModel> info);

    /**
     * Checks if book data has already been pre-populated and exists in database
     */
    boolean dataExists() throws SQLException;

    /**
     * Parse JSON to prepopulate data in book database.
     */
    void prepopulateData();
}
