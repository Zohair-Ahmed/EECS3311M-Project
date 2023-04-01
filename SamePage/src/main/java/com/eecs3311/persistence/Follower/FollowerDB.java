package com.eecs3311.persistence.Follower;

import com.eecs3311.model.Follower.FollowerModel;
import com.eecs3311.model.Follower.IFollowerModel;
import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.presenter.User.FollowerPresenter;
import com.eecs3311.presenter.User.IFollowerPresenter;
import com.eecs3311.view.Follower.FollowerView;
import com.eecs3311.view.Follower.IFollowerView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FollowerDB extends AbstractDatabase implements IFollower {

    private ArrayList<IFollowerModel> following;

    ArrayList<String> users = new ArrayList<>();

    /**
     * Provide access and perform operations on Follower DB
     */
    public FollowerDB() {
        super();
        try {
            if (following == null) {
                getDBdata();
            }
            getDBdata();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<IFollowerModel> getFollowing() {
        getDBdata();
        return following;
    }

    public void getDBdata() {
        following = new ArrayList<>();
        try {
            if (getConnection() != null) {
                Set<IFollowerModel> userFollowing = new HashSet<>();
                System.out.println("Connection is successful");
                String query = "SELECT Followers.*, Users.Username " + "FROM Users " + "INNER JOIN Followers ON Users.Username = Followers.FollowedUser " + "WHERE Followers.UserID = " + UserModel.getInstance().getUserID();
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String followedUser = rs.getString("FollowedUser");
                    String userID = rs.getString("UserID");
                    String username = rs.getString("Username");
                    userFollowing.add(new FollowerModel(userID, followedUser, username));
                }
                addToList(userFollowing);
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToList(Set<IFollowerModel> userFollowing) {
        for (IFollowerModel ifm : userFollowing) {
            IFollowerPresenter ifp = new FollowerPresenter();
            IFollowerView ifv = new FollowerView(ifm.getUsername());
            ifp.setModel(ifm);
            ifm.setPresenter(ifp);
            ifp.setView(ifv);
            ifv.setPresenter(ifp);
            this.following.add(ifm);
        }
    }

    public ArrayList<String> getUserList(){
        if(users.isEmpty()){
            try {
                Statement temp = getConnection().createStatement();
                ResultSet rs = temp.executeQuery("SELECT * FROM Users");
                while (rs.next()) {
                    String dbUsername = rs.getString("Username");
                    if(!this.users.contains(dbUsername)){
                        this.users.add(dbUsername);
                    }
                }
                //removes the user currently logged in from the list
                this.users.remove(UserModel.getInstance().getUsername());
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
