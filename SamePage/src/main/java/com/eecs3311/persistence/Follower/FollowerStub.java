package com.eecs3311.persistence.Follower;

import com.eecs3311.model.Follower.IFollowerModel;
import java.util.ArrayList;

public class FollowerStub {

    private final static ArrayList<IFollowerModel> followerList = new ArrayList<>();

    private static FollowerStub instance = null;
    public void addFollower(IFollowerModel followedUser) {
        followerList.add(followedUser);
    }

    public ArrayList<IFollowerModel> getFollowerList() {
        return followerList;
    }
    public static FollowerStub getInstance(){
        if(instance == null){
            instance = new FollowerStub();
        }
        return instance;
    }

    public void removeFollower(IFollowerModel followedUser) {
        followerList.remove(followedUser);
    }
}
