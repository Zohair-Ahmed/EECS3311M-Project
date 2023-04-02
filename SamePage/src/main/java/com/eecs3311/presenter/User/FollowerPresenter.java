package com.eecs3311.presenter.User;

import com.eecs3311.model.Follower.IFollowerModel;
import com.eecs3311.view.Follower.IFollowerView;

public class FollowerPresenter implements IFollowerPresenter {

    private IFollowerModel userModel;
    private IFollowerView userView;
    @Override
    public IFollowerModel getModel() { return userModel; }

    @Override
    public void setModel(IFollowerModel ium) { this.userModel = ium; }

    @Override
    public IFollowerView getUserView() { return userView; }

    @Override
    public void setView(IFollowerView iuv) { this.userView = iuv; }

    @Override
    public boolean checkModelFollowing() {
        boolean check = getModel().checkFollowing();
        return check;
    }

    @Override
    public void updateModelFollowers() {
        getModel().addFollower();
    }

    @Override
    public void removeFollower() {
        getModel().removeFollower();
    }
}
