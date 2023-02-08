package com.eecs3311.model.enums;

public enum Genre {
    // implementation from Lec1-2 pg.50
    ADVENTURE("Adventure"), CONTEMPORARY("Contemporary"),
    FANTASY("Fantasy"), HORROR("Horror"), MYSTERY("Mystery"),
    ROMANCE("Romance"), THRILLER("Thriller"), UNSPECIFIED("Unspecified");
    String value;
    private Genre(String value){
        this.value = value;
    }

    public String toString() {
        return value;
    }
}


