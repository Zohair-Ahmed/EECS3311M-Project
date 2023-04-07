package com.eecs3311.model.User;

import com.eecs3311.model.enums.State;
import com.eecs3311.view.layout.Main;
import com.eecs3311.util.log.console.ConsoleLogs;

// Singleton pattern
public class UserModel {

    private String username;
    private String email;
    private String password;
    private int userID;

    private State loginState = State.GUEST;
    private static UserModel userModel;
    private Main main;
    
    private UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Manages one class creation
     * @return class instance
     */
    public static UserModel getInstance() {
        if (userModel == null)
            userModel = new UserModel("x", "x");
        return userModel;
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
            return ConsoleLogs.USER( "Not Logged In");
        return "Current User = " + ConsoleLogs.USER(getEmail() + " - " + getPassword());
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
