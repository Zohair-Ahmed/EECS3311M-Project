package com.eecs3311.presenter.User;

import com.eecs3311.model.Follower.IFollowerModel;
import com.eecs3311.view.Follower.IFollowerView;

public interface IFollowerPresenter {
    IFollowerModel getModel();

    /**
     * Sets the model
     *
     * @param ium model
     */
    void setModel(IFollowerModel ium);

    /**
     * Returns the LoginPanelView
     *
     * @return ILoginPanelView
     */
    IFollowerView getUserView();

    /**
     * Sets the view
     *
     * @param iuv view
     */
    void setView(IFollowerView iuv);

    /**
     * To be used when a UI component is checking if this user is in the followed list of the logged-in user.
     *
     * @return boolean value for following status
     */
    boolean checkModelFollowing();

    /**
     * To be used when a UI component is adding this user to the following list of the logged-in user.
     */
    void updateModelFollowers();

    /**
     * To be used when a UI component is removing this user from the following list of the logged-in user.
     */
    void removeFollower();

}
