package com.eecs3311.model.enums;

// State to depict whether user is a guest or a member of the SamePage library
public enum State {
    GUEST("Guest"), MEMBER("Member");

    String value;

    private State(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
