package com.eecs3311.model;

import com.eecs3311.model.enums.State;

public class Member implements Users{
    private String name;
    private String email;
    private String password;

    // Constructor
    public Member() {
        this.name = "";
        this.email = "";
        this.password = "";
    }

    //default - User is a guest
    private State state = State.GUEST;

    public String getState() {
        return state.toString();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String inputName) {
        this.name = inputName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String inputEmail) {
        this.email = inputEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String inputPassword) {
        this.password = inputPassword;
    }

    Public boolean validLogin() {
        return email.equals("") ** password.equals("");
    }


}
