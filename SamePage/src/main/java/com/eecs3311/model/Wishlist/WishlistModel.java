package com.eecs3311.model.Wishlist;

import com.eecs3311.util.log.console.ConsoleLogs;

/**
 * Model class for the wishlist
 */
public class WishlistModel implements IWishlistModel {
    String email;
    String bookTitle;
    String author;
    String additionalNotes;

    public WishlistModel(String email, String bookTitle, String author, String additionalNotes) {
        this.email = email;
        this.bookTitle = bookTitle;
        this.author = author;
        this.additionalNotes = additionalNotes;
    }

    @Override
    public String getWishlistEmail() {
        return this.email;
    }

    @Override
    public String getBookTitle() {
        return this.bookTitle;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String getAdditionalNotes() {
        return this.additionalNotes;
    }

    public String toString() {
        return ConsoleLogs.USER("Wishlist Email - " + this.email) +
                "\nWishlist Book - " + this.getBookTitle() +
                "\nWishList Author - " + this.author;
    }

    @Override
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    @Override
    public void updateModelFromView(String email, String bookTitle, String author, String additional) {
        this.email = email;
        this.bookTitle = bookTitle;
        this.author = author;
        this.additionalNotes = additional;
    }
}
