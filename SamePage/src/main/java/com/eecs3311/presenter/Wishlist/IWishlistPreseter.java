package com.eecs3311.presenter.Wishlist;

import com.eecs3311.model.Wishlist.IWishlistModel;
import com.eecs3311.view.Wishlist.IWishlistPanelView;

public interface IWishlistPreseter {
    /**
     * Returns the wishlist model
     * @return the wishlist model
     */
    IWishlistModel getModel();

    /**
     * Set the wishlist model
     * @param iwm the wishlist model
     */
    void setModel(IWishlistModel iwm);

    /**
     * Return the wishlist panel view
     * @return the wishlist panel view
     */
    IWishlistPanelView getWishlistPanelView();

    /**
     * Set the wishlist panel view
     * @param iwv the wishlist panel view
     */
    void setView(IWishlistPanelView iwv);

    /**
     * Update model from view interaction
     *
     * @param email email of the user adding to the wishlist
     * @param bookTitle title of book to be added to wishlist
     * @param author author of book to be added to wishlist
     * @param additional additional notes regarding book to be added to wishlist
     */
    void updateModelFromView(String email, String bookTitle, String author, String additional);

    /**
     * Updates the view based on the current valid changes made to the model
     * @param wishlistMessage message to be displayed based off user actions
     */
    void updateViewFromModel(String wishlistMessage);

    /**
     *
     * @return
     */
    IWishlistModel getUpdateViewFromModel();
}
