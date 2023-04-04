package com.eecs3311.persistence.Follower;

import com.eecs3311.model.Login.Follower.IFollowerModel;

import java.util.*;

public interface IFollower {

    /**
     * Returns the list of users followed users
     * @return list of follower models in the following list of logged-in user
     */
    ArrayList<IFollowerModel> getFollowing();

    /**
     * Returns the list of logged-in users followers
     * @return list of follower models in the followers list of the logged-in user
     */
    ArrayList<IFollowerModel> getFollowers();

    /**
     * Gets the logged-in user followers
     */
    void getDBFollowers();

    /**
     * Gets all accounts current logged-in user's is following and stores them in IFollowerModel ArrayList
     */
    void getDBFollowedUsers();

    /**
     * Gets all the usernames registered and returns arraylist of string
     * @return Arraylist<String> of all usernames
     */
    ArrayList<IFollowerModel> getUserList();

    /**
     * Gets list of usernames and returns the corresponding list of IFollowerModel objects
     * @return ArrayList<IFollowerModel>
     */
    ArrayList<IFollowerModel> addExistingFollowedUsers(Set<String> info);

    /**
     * Gets all the existing users follower models
     * @return arraylist of follower models
     */
    ArrayList<IFollowerModel> getAllUsers();

    /**
     * Removes a specified user from logged-in user's following list
     * @param followedUser
     */
    void removeFollower(IFollowerModel followedUser);

    /**
     * Adds follower to DB
     */
    void addFollower(IFollowerModel followedUser);

}
