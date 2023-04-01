package com.eecs3311.model.Follower;

import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.User.IFollowerPresenter;

import java.util.ArrayList;

public class FollowerModel implements IFollowerModel {

    private IFollowerPresenter UserPresenter;

    private String userID;

    private String followedUser;

    private String username;

    public FollowerModel(String UserID, String FollowedUser, String Username) {
        this.userID = UserID;
        this.followedUser = FollowedUser;
        this.username = Username;
    }
    @Override
    public IFollowerPresenter getPresenter() { return UserPresenter; }

    @Override
    public void setPresenter(IFollowerPresenter iup) { this.UserPresenter = iup; }

    @Override
    public String getUserID() { return userID; }

    @Override
    public String getFollowedUser() { return followedUser; }

    @Override
    public String getUsername() { return username; }

    @Override
    public void updateModelFromView() {

    }

    @Override
    public boolean checkFollowing() {
        boolean checkFollow = false;
        ArrayList<IFollowerModel> userFollowers = Database.getFollowerInstance().getFollowing();

        if (userFollowers != null) {
            for (IFollowerModel ifm : userFollowers) {
                if (this.getUsername().equals(ifm.getUsername())) {
                    checkFollow = true;
                }
            }
        }

        return checkFollow;
    }

    @Override
    public void addFollower() {

    }

    @Override
    public void removeFollower() {

    }
}
