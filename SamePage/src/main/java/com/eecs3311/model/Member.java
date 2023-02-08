package com.eecs3311.model;

import com.eecs3311.model.enums.State;

public class Member implements Users{
    private String name;
    private String email;
    private String password;
    private State state = State.GUEST;
    public String getState() {
        return state.toString();
    }
}
