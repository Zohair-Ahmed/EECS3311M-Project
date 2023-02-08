package com.eecs3311.model.enums;

public enum State {
    GUEST("Guest"), MEMBER("Member");
    String value;
    private State(String value){
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
