package com.eecs3311.UnitTesting.model.Follower;

import com.eecs3311.model.Follower.FollowerModel;
import com.eecs3311.model.Follower.IFollowerModel;
import com.eecs3311.persistence.Follower.FollowerStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IFollowerModelTest {

    @Test
    void checkFollowing() {
    }

    @Test
    void addFollower() {
        IFollowerModel followedUser = new FollowerModel("current user", "followed user");
        FollowerStub.getInstance().addFollower(followedUser);
        assertEquals(FollowerStub.getInstance().getFollowerList().get(0).getFollowedUser(),followedUser.getFollowedUser());
    }


    @Test
    void removeFollower() {
        IFollowerModel followedUser = new FollowerModel("current user", "followed user");
        FollowerStub.getInstance().addFollower(followedUser);
        FollowerStub.getInstance().removeFollower(followedUser);
        assertEquals(FollowerStub.getInstance().getFollowerList().size(),0);
    }

    @Test
    void getCurrentUser() {
        IFollowerModel followedUser = new FollowerModel("current user", "followed user");
        assertEquals(followedUser.getCurrentUser(), "current user");
    }

    @Test
    void getFollowedUser() {
        IFollowerModel followedUser = new FollowerModel("current user", "followed user");
        assertEquals(followedUser.getFollowedUser(), "followed user");
    }

    @Test
    void setCurrentUser() {
        IFollowerModel followedUser = new FollowerModel("current user", "followed user");
        followedUser.setCurrentUser("a new user");
        assertEquals(followedUser.getCurrentUser(), "a new user");
    }

    @Test
    void setFollowedUser() {
        IFollowerModel followedUser = new FollowerModel("current user", "followed user");
        followedUser.setFollowedUser("a new followed user");
        assertEquals(followedUser.getFollowedUser(), "a new followed user");
    }
}