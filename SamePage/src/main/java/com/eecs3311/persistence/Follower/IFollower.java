package com.eecs3311.persistence.Follower;

import com.eecs3311.model.Follower.IFollowerModel;

import java.util.ArrayList;
import java.util.Set;

public interface IFollower {

    /**
     * Returns the list of users followed users
     *
     * @return list of book models in the favorites list of the logged-in user
     */
    ArrayList<IFollowerModel> getFollowing();

    /**
     * To add the usernames from the db to a list
     * @param info info
     */
    void addToList(Set<IFollowerModel> info);

    /**
     * Gets all the usernames registered and returns arraylist of string
     * @return Arraylist<String> of all usernames
     */
    ArrayList<String> getUserList();
}
