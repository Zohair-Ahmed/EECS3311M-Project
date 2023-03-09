package com.eecs3311.model;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.enums.State;
import com.eecs3311.persistence.Book.FavBooksDB;
import com.eecs3311.view.layout.Main;
import com.eecs3311.view.layout.ProfilePanel;

import java.awt.print.Book;
import java.util.ArrayList;

// Singleton pattern
public class User {

    private String username;
    private String email;
    private String password;
    private int userID;

    private State loginState = State.GUEST;
    private static User user;
    private Main main;
    
    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Manages one class creation
     * @return class instance
     */
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



    @Override
    public String toString() {
        if (loginState.equals(State.GUEST))
            return "Not Logged In";
        return "Current User = " + getEmail() + " - " + getPassword();
    }

    public Main getMainInit() {
        return this.main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public State getLoginState() {
        return loginState;
    }


}
