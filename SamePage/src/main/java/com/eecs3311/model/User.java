package com.eecs3311.model;

import com.eecs3311.model.enums.State;

// Singleton pattern
public class User {

    private String username;
    private String email;
    private String password;
    private State loginState = State.GUEST;
    private static User user;

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

    @Override
    public String toString() {
        if (loginState.equals(State.GUEST))
            return "Not Logged In";
        return "Current User = " + getEmail() + " - " + getPassword();
    }

    public State getLoginState() {
        return loginState;
    }


}
