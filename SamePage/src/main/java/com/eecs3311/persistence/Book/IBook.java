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

}
