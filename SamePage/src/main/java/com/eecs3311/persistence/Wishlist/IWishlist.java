package com.eecs3311.persistence.Wishlist;

import com.eecs3311.model.Wishlist.IWishlistModel;

import java.util.ArrayList;

public interface IWishlist {
    /**
     * Submits a book to the wishlist
     *
     * @param username - username of the user sending the book request
     * @param bookTitle - title of the book that is being requested
     * @param author - author of the book that is being requested
     * @param additionalDetails - any notes that are important to add
     */
    void submitBook(String username, String bookTitle, String author, String additionalDetails);

    /**
     * Returns all the books submitted by this username
     *
     * @param username username of the user to get reviews for
     * @return a list of books submitted by this user
     */
    ArrayList<IWishlistModel> getBooksSubmitted(String username);
}
