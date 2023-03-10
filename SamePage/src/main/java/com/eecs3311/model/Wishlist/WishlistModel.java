package com.eecs3311.model.Wishlist;

import com.eecs3311.model.User;
import com.eecs3311.model.enums.State;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Wishlist.IWishlistPreseter;
import com.eecs3311.util.log.console.ConsoleLogs;

/**
 * Model class for the wishlist
 */
public class WishlistModel implements IWishlistModel {

    private IWishlistPreseter iwp;
    private String username;
    private String bookTitle;
    private String author;
    private String additionalNotes;

    @Override
    public String getWishlistUsername() {
        return this.username;
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
        return ConsoleLogs.USER("Wishlist Username - " + this.username) +
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
    public void updateModelFromView(String username, String bookTitle, String author, String additionalNotes) {

        if (!isFieldValid(username)) {
            //this.getPresenter().updateViewFromModel("Please login to add this book to your wishlist!");
            System.out.println(ConsoleLogs.ERROR("INVALID USERNAME FOR WISHLIST"));
            return;
        }  else if (!isFieldValid(bookTitle)) {
            //this.getPresenter().updateViewFromModel("Please provide a book title!");
            System.out.println(ConsoleLogs.ERROR("INVALID BOOK TITLE FOR WISHLIST"));
            return;
        } else if (!isFieldValid(author)) {
            //this.getPresenter().updateViewFromModel("Please enter an author!");
            System.out.println(ConsoleLogs.ERROR("INVALID AUTHOR FOR WISHLIST"));
            return;
        }

        this.username = username;
        this.bookTitle = bookTitle;
        this.author = author;
        this.additionalNotes = additionalNotes;

        addBookToWishlist(username, bookTitle, author, additionalNotes);
    }

    @Override
    public IWishlistPreseter getPresenter() {
        return this.iwp;
    }

    @Override
    public void setPresenter(IWishlistPreseter iwp) {
        this.iwp = iwp;
    }

    private void addBookToWishlist(String username, String bookTitle, String author, String additionalNotes) {
        if (User.getInstance().getLoginState().equals(State.MEMBER)) {
            Database.getWishlistInstance().submitBook(username, bookTitle, author, additionalNotes);
            System.out.println(ConsoleLogs.SUCCESSFUL("Added book! We will review it shortly..."));
            //this.getPresenter().updateViewFromModel("Book added! We'll review it shortly!");
        }
    }

    private boolean isFieldValid(String field) {
        return field != null && !field.isEmpty();
    }
}
