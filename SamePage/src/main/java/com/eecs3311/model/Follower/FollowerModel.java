package com.eecs3311.model.Follower;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Follower.IFollowerPresenter;

import java.util.ArrayList;

public class FollowerModel implements IFollowerModel {

    private IFollowerPresenter UserPresenter;

    private String currentUser;

    private String followedUser;

    public FollowerModel(String CurrentUser, String FollowedUser) {
        this.currentUser = CurrentUser;
        this.followedUser = FollowedUser;
    }
    @Override
    public IFollowerPresenter getPresenter() { return UserPresenter; }

    @Override
    public void setPresenter(IFollowerPresenter iup) { this.UserPresenter = iup; }

    @Override
    public String getCurrentUser() { return currentUser; }

    @Override
    public String getFollowedUser() { return followedUser; }

    @Override
    public void setCurrentUser(String username) {
        currentUser = username;
    }

    @Override
    public void setFollowedUser(String username) {
        followedUser = username;
    }

    @Override
    public void updateModelFromView() {

    }

    @Override
    public boolean checkFollowing() {
        boolean checkFollow = false;
        ArrayList<IFollowerModel> userFollowers = Database.getFollowerInstance().getFollowing(UserModel.getInstance().getUsername());
        if (userFollowers != null) {
            for (IFollowerModel ifm : userFollowers) {
                if (this.getCurrentUser().equals(ifm.getCurrentUser())) {
                    checkFollow = true;
                }
            }
        }

        return checkFollow;
    }

    @Override
    public void addFollower() {
        Database.getFollowerInstance().addFollower(this);
    }

    @Override
    public void removeFollower() {
        Database.getFollowerInstance().removeFollower(this);
    }
}
