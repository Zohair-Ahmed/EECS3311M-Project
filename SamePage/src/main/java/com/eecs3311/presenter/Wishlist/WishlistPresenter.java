package com.eecs3311.presenter.Wishlist;

import com.eecs3311.model.Wishlist.IWishlistModel;
import com.eecs3311.view.Wishlist.IWishlistPanelView;

public class WishlistPresenter implements IWishlistPreseter {

    private IWishlistModel wishlistModel;
    private IWishlistPanelView wishlistPanelView;

    /**
     * Returns the wishlist model
     *
     * @return the wishlist model
     */
    @Override
    public IWishlistModel getModel() {
        return this.wishlistModel;
    }

    /**
     * Set the wishlist model
     *
     * @param iwm the wishlist model
     */
    @Override
    public void setModel(IWishlistModel iwm) {
        this.wishlistModel = iwm;
    }

    /**
     * Return the wishlist panel view
     *
     * @return the wishlist panel view
     */
    @Override
    public IWishlistPanelView getWishlistPanelView() {
        return this.wishlistPanelView;
    }

    /**
     * Set the wishlist panel view
     *
     * @param iwv the wishlist panel view
     */
    @Override
    public void setView(IWishlistPanelView iwv) {
        this.wishlistPanelView = iwv;
    }

    /**
     * Update model from view interaction
     *
     * @param bookTitle  title of book to be added to wishlist
     * @param author     author of book to be added to wishlist
     * @param additional additional notes regarding book to be added to wishlist
     */
    @Override
    public void updateModelFromView(String email, String bookTitle, String author, String additional) {
        this.wishlistModel.updateModelFromView(email, bookTitle, author, additional);
    }

    /**
     * Updates the view based on the current valid changes made to the model
     *
     * @param wishlistMessage message to be displayed based off user actions
     */
    @Override
    public void updateViewFromModel(String wishlistMessage) {
        this.wishlistPanelView.updateWishlistStatus(wishlistMessage);
    }

    /**
     * Return the updated view from the model
     *
     * @return the updated view from the model
     */
    @Override
    public IWishlistModel getUpdateViewFromModel() {
        return this.wishlistModel;
    }
}
