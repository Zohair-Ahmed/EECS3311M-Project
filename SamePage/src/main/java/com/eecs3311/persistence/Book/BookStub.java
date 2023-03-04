package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookStub implements IBook{
    @Override
    public ArrayList<IBookModel> getLatestReleases() {
        return null;
    }

    @Override
    public void getDBdata() {

    }

    @Override
    public void addToList(ArrayList<IBookModel> info) {

    }

    @Override
    public boolean dataExists() {
        return false;
    }

    @Override
    public void prepopulateData() {

    }
}
