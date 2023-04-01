package com.eecs3311.model.Follower;

import com.eecs3311.presenter.User.IFollowerPresenter;

public interface IFollowerModel {
    /**
     * Return the presenter
     *
     * @return IUserPresenter
     */
    IFollowerPresenter getPresenter();

    /**
     * Sets the presenter
     *
     * @param ifp presenter
     */
    void setPresenter(IFollowerPresenter ifp);

    /**
     * Gets userID of follower
     * @return String userID
     */
    String getUserID();

    /**
     * Gets the followed user's username
     * @return String username
     */
    String getFollowedUser();

    /**
     * Gets the username of follower
     * @return String username
     */
    String getUsername();

    /**
     * Updates the model from interactions from the view
     *
     * @param review review
     * @param rating rating
     * @param isbn isbn
     */
    void updateModelFromView();

    /**
     * Checks if username exists in users following
     *
     * @return boolean value for user following status
     */
    boolean checkFollowing();

    /**
     * Add this user to the logged in user's following list in the database
     */
    void addFollower();

    /**
     * Remove this user from the logged in user's following list in the database
     */
    void removeFollower();
}
