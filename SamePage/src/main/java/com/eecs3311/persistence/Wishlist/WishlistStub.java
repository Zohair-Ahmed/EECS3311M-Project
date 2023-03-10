package com.eecs3311.persistence.Wishlist;

import com.eecs3311.model.Wishlist.IWishlistModel;
import com.eecs3311.util.log.console.ConsoleLogs;

import java.util.ArrayList;

public class WishlistStub implements IWishlist{

    public WishlistStub() {
        System.out.println(ConsoleLogs.DATABASE("This is the Wishlist Stub DB"));
    }

    @Override
    public void submitBook(String username, String bookTitle, String author, String additionalDetails) {

    }

    @Override
    public ArrayList<IWishlistModel> getBooksSubmitted(String username) {
        return null;
    }
}
