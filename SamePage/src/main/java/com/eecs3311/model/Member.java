package com.eecs3311.model;

import com.eecs3311.model.enums.State;

public class Member implements Users{
    private String name;
    private String email;
    private String password;

    // Constructor
    public Member() {
        this.name = "Team 1";
        this.email = "temp@mail.ca";
        this.password = "Test1234";
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

    public boolean validLogin() {
        return email.equals("temp@mail.ca") && password.equals("Test1234");
    }


}
