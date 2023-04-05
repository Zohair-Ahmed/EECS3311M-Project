package com.eecs3311.persistence.Follower;

import com.eecs3311.model.Follower.IFollowerModel;

import java.util.*;

public interface IFollower {

    /**
     * Returns the list of users followed users
     * @param username of following we need
     * @return list of follower models in the following list of logged-in user
     */
    ArrayList<IFollowerModel> getFollowing(String username);

    /**
     * Returns the list of logged-in users followers
     * @param username of followers we need
     * @return list of follower models in the followers list of the logged-in user
     */
    ArrayList<IFollowerModel> getFollowers(String username);

    /**
     * Gets the logged-in user followers
     */
    void getDBFollowers(String username);

    /**
     * Gets all accounts current logged-in user's is following and stores them in IFollowerModel ArrayList
     */
    void getDBFollowedUsers(String username);

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
