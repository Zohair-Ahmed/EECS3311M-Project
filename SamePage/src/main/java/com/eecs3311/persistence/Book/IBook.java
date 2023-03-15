package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;
import java.util.ArrayList;

public interface IBook {

    /**
     * Returns the bookList with all latest books in DB
     * @return list of book models
     */
    ArrayList<IBookModel> getLatestReleases();

    /**
     * To add the books from the db to a list
     * @param info info
     */
    void addToList(ArrayList<IBookModel> info);

    /**
     * Increment the like button on every click
     * @param ISBN13 of the book to be updated
     */
    void addLike(String ISBN13);

    /**
     * get the amount of likes from the db
     * @param ISBN13
     * @return int number of likes
     */
    int getLikes(String ISBN13);

}
