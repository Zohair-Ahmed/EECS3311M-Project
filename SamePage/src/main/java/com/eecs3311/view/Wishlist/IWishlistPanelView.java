package com.eecs3311.view.Wishlist;

import com.eecs3311.presenter.Wishlist.IWishlistPreseter;
import javax.swing.JPanel;

/**
 * Interface for the WishlistPanelView
 */
public interface IWishlistPanelView {

    /**
     * Used to update the Wishlist status show on screen to show whether or not the wishlist registration was successful
     * or not
     *
     * @param status the status of registration update
     */
    void updateWishlistStatus(String status);

    /**
     * Returns the presenter for this wishlist panel
     *
     * @return the IWishlistPresenter for this wishlist panel
     */
    IWishlistPreseter getPresenter();

    /**
     * Sets the for this panel
     *
     * @param iwp IWishlistPresenter to be set to
     */
    void setPresenter(IWishlistPreseter iwp);

    /**
     * Returns the view of the wishlist panel
     *
     * @return JPanel of the Wishlist panel
     */
    JPanel getView();
}
