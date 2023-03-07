package com.eecs3311.model;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.enums.State;
import com.eecs3311.persistence.Book.FavBooksDB;

import java.awt.print.Book;
import java.util.ArrayList;

// Singleton pattern
public class User {

    private String username;

    private String email;
    private String password;
    private int userID;

    private State loginState = State.GUEST;

    private ArrayList<IBookModel> favBooks;

    private static User user;

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User getInstance() {
        if (user == null)
            user = new User("x", "x");
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.loginState = State.MEMBER;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void addFavBook(IBookModel book) {
        favBooks.add(book);
    }

    public ArrayList<IBookModel> getFavBooks() {
        return favBooks;
    }

    public State getLoginState() {
        return loginState;
    }

    @Override
    public String toString() {
        if (loginState.equals(State.GUEST))
            return "Not Logged In";
        return "Current User = " + getEmail() + " - " + getPassword();
    }

}
