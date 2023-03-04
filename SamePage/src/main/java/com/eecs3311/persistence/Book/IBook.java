package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBook {

    ArrayList<IBookModel> getLatestReleases();
    void getDBdata();
    void addToList(ArrayList<IBookModel> info);
    boolean dataExists() throws SQLException;
    void prepopulateData();
}
