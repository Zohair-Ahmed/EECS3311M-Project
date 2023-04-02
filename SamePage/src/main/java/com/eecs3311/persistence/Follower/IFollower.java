package com.eecs3311.persistence.Follower;

import com.eecs3311.model.Follower.IFollowerModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public interface IFollower {

    /**
     * Returns the list of users followed users
     *
     * @return list of book models in the favorites list of the logged-in user
     */
    ArrayList<IFollowerModel> getFollowing();

//    /**
//     * To add the usernames from the db to a list
//     * @param info info
//     */
//    void addToList(ArrayList<IFollowerModel> info);

    /**
     * Gets all accounts current logged-in user's is following and stores them in IFollowerModel ArrayList
     */
    void getDBdata();

    /**
     * Gets all the usernames registered and returns arraylist of string
     * @return Arraylist<String> of all usernames
     */
    ArrayList<IFollowerModel> getUserList();

    /**
     * Removes a specified user from logged-in user's following list
     * @param followedUser
     */
    void removeFollower(IFollowerModel followedUser);

    /**
     * Adds follower to DB
     */
    void addFollower(IFollowerModel followedUser);

//    /**
//     * Gets all the followers from follower table in DB and creates IFollowerModel objects
//     */
//    void getAllFollowers();
}
