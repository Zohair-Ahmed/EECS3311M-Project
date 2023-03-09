package com.eecs3311.model;

import com.eecs3311.model.enums.State;

import java.util.ArrayList;

public class UserStub {
    private String username;
    private String email;
    private String password;
    private State loginState = State.GUEST;
    private ArrayList<UserStub> users = new ArrayList<>();
    private static UserStub instance = null;

    public UserStub(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    private UserStub(){
        users.add(new UserStub("test1@mail.com","test1", "pass1"));
        users.add(new UserStub("test2@mail.com","test2", "pass2"));
        users.add(new UserStub("test3@mail.com","test3", "pass3"));
    }

    public ArrayList<UserStub> userList(){
        return users;
    }

    public static UserStub getInstance(){
        if(instance == null){
            instance = new UserStub();
        }
        return instance;
    }

    public void addNewUser(String email, String username, String password){
        users.add(new UserStub(email, username,password));
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        if (loginState.equals(State.GUEST))
            return "Not Logged In";
        return "Current User = " + getEmail() + " - " + getPassword();
    }

}
