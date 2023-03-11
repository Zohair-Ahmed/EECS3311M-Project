package com.eecs3311.model.Wishlist;

import com.eecs3311.presenter.Wishlist.IWishlistPreseter;

/**
 * Interface for requesting a new book to be added to the database
 */
public interface IWishlistModel {

    /**
     * Return username of the person sending the request
     *
     * @return username of the person sending the request
     */
    String getWishlistUsername();

    /**
     * Return the title of the book the user is requesting to add
     *
     * @return the title of the book the user is requesting to add
     */
    String getBookTitle();

    /**
     * Return the author of the book the user is requesting to add
     *
     * @return the author of the book the user is requesting to add
     */
    String getAuthor();

    /**
     * Return any additional notes th user mau have regarding the addition of this book
     *
     * @return any additional notes th user mau have regarding the addition of this book
     */
    String getAdditionalNotes();

    /**
     * Set the title of the book the user wishes to add
     *
     * @param bookTitle title of book
     */
    void setBookTitle(String bookTitle);

    /**
     * Set the author of the book the user wishes to add
     *
     * @param author author of the book
     */
    void setAuthor(String author);

    /**
     * Set any additional information regarding the addition of this book
     *
     * @param additionalNotes additional notes relating to the book the user wishes to add
     */
    void setAdditionalNotes(String additionalNotes);

    /**
     * Update the wishlist model
     *
     * @param email email of the user requesting new book
     * @param bookTitle title of book to be added
     * @param author author of book to be added
     * @param additional additonal notes from user regarding book to be added to wishlist
     */
    void updateModelFromView(String email, String bookTitle, String author, String additional);

    /**
     * Return the wishlist presenter
     *
     * @return the wishlist presenter
     */
    IWishlistPreseter getPresenter();

    /**
     * Set the wishlist presenter
     *
     * @param iwp - set the wishlist presenter
     */
    void setPresenter(IWishlistPreseter iwp);

}
