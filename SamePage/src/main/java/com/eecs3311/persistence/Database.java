package com.eecs3311.persistence;

import com.eecs3311.persistence.Book.*;
import com.eecs3311.persistence.Goals.GoalDB;
import com.eecs3311.persistence.Goals.GoalStub;
import com.eecs3311.persistence.Goals.IGoal;
import com.eecs3311.persistence.Login.*;
import com.eecs3311.persistence.Register.*;
import com.eecs3311.persistence.Review.*;


// Single Pattern
public class Database {

    private static ILogin login;
    private static IRegister register;
    private static IBook book;
    private static IFavBooks favBooks;
    private static IReview review;
    private static IGoal goal;
    private static Database database;
    // isUsingStub To use stub = True | To use real db = False
    private static boolean isUsingStubDB = false;

    private Database(){
        if (!isUsingStubDB) {
            login = new LoginDB();
            register = new RegisterDB();
            book = new BookDB();
            favBooks = new FavBooksDB();
            review = new ReviewDB();
            goal = new GoalDB();
        } else {
            login = LoginStub.getInstance();
            register = RegisterStub.getInstance();
            book = BookStub.getInstance();
            review = ReviewStub.getInstance();
            goal = new GoalStub();
        }
    }

    public static void setIsUsingStubDB(boolean val) {
        isUsingStubDB = val;
        database = new Database();
    }

    /**
     * Retrieves the class instance. Uses dependency injection to switch between stub and real-time database
     */
    public static Database getInstance() {
        if (database == null)
            database = new Database();
        return database;
    }

    public static ILogin getLoginInstance() {
        database = Database.getInstance();
        return login;
    }

    public static IRegister getRegisterInstance() {
        database = Database.getInstance();
        return register;
    }

    public static IBook getBookInstance() {
        database = Database.getInstance();
        return book;
    }

    public static IFavBooks getFavBooksInstance() {
        return favBooks;
    }

    public static IReview getReviewInstance() {
        database = Database.getInstance();
        return review;
    }

    public static IGoal getGoalInstance() {
        database = Database.getInstance();
        return goal;
    }
}
