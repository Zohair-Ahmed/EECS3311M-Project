package com.eecs3311.persistence;

import com.eecs3311.persistence.Book.BookDB;
import com.eecs3311.persistence.Book.BookStub;
import com.eecs3311.persistence.Book.IBook;
import com.eecs3311.persistence.Login.*;
import com.eecs3311.persistence.Register.*;
import com.eecs3311.persistence.Review.*;



public class Database {

    private static ILogin login;
    private static IRegister register;
    private static IBook book;
    private static IReview review;

    private static Database database;
    private static boolean isUsingStubDB;

    private Database(boolean isUsingStub){
        isUsingStubDB = isUsingStub;

        if (!isUsingStubDB) {
            login = new LoginDB();
            register = new RegisterDB();
            book = new BookDB();
            review = new ReviewDB();
        } else {
            login = new LoginStub();
            register = new RegisterStub();
            book = new BookStub();
            review = new ReviewStub();
        }
    }

    public static Database getInstance(boolean isUsingStub) {
        isUsingStubDB = isUsingStub;
        if (database == null)
            database = new Database(isUsingStubDB);
        return database;
    }

    public static ILogin getLoginInstance() {
        database = Database.getInstance(isUsingStubDB);
        return login;
    }

    public static IRegister getRegisterInstance() {
        database = Database.getInstance(isUsingStubDB);
        return register;
    }

    public static IBook getBookInstance() {
        database = Database.getInstance(isUsingStubDB);
        return book;
    }

    public static IReview getReviewInstance() {
        database = database.getInstance(isUsingStubDB);
        return review;
    }
}
