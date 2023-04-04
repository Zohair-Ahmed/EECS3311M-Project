package com.eecs3311.persistence.Follower;

import com.eecs3311.model.Login.Follower.FollowerModel;
import com.eecs3311.model.Login.Follower.IFollowerModel;
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

    private ArrayList<IFollowerModel> followedUsers;
    private ArrayList<IFollowerModel> followers;
    private ArrayList<IFollowerModel> users = new ArrayList<>();
    private String user;

    /**
     * Provide access and perform operations on Follower DB
     */
    public FollowerDB() {
        super();
    }

    @Override
    public ArrayList<IFollowerModel> getAllUsers() {
        return users;
    }

    @Override
    public ArrayList<IFollowerModel> getFollowing() {
        getDBFollowedUsers();
        return followedUsers;
    }

    @Override
    public ArrayList<IFollowerModel> getFollowers() {
        getDBFollowers();
        return followers;
    }

    public void getDBFollowedUsers() {
        try {
            if (getConnection() != null) {
                Set<String> userFollowing = new HashSet<>();
                String query = "SELECT FollowedUser " + "FROM Followers " + "WHERE CurrentUser = \'" + UserModel.getInstance().getUsername()+"\'";
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    user = rs.getString("FollowedUser");
                    userFollowing.add(user);
                }
                followedUsers = addExistingFollowedUsers(userFollowing);
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<IFollowerModel> addExistingFollowedUsers(Set<String> info) {
        ArrayList<IFollowerModel> newFollow = new ArrayList<>();
        users.parallelStream().forEach(user -> {
            if (info.contains(user.getCurrentUser()))
                newFollow.add(user);
        });
        return newFollow;
    }

    public ArrayList<IFollowerModel> getUserList(){
        if(users == null || users.isEmpty()){
            users = new ArrayList<>();
            try {
                Statement temp = getConnection().createStatement();
                ResultSet rs = temp.executeQuery("SELECT u.Username, COUNT(f.FollowedUser) AS num_followers\n" +
                        "FROM Users u\n" +
                        "LEFT JOIN Followers f ON u.Username = f.FollowedUser\n" +
                        "GROUP BY u.Username;");
                while (rs.next()) {
                    String dbUsername = rs.getString("Username");
                    String followerCount = rs.getString("num_followers");
                    if (dbUsername.equals(UserModel.getInstance().getUsername())) {
                        continue;
                    }
                    IFollowerModel ifm = new FollowerModel(dbUsername, "");
                    IFollowerPresenter ifp = new FollowerPresenter();
                    IFollowerView ifv = new FollowerView(dbUsername, followerCount);
                    ifm.setPresenter(ifp);
                    ifp.setModel(ifm);
                    ifp.setView(ifv);
                    ifv.setPresenter(ifp);
                    ifv.initComponents();
                    this.users.add(ifm);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void getDBFollowers() {
        try {
            if (getConnection() != null) {
                Set<String> userFollowers = new HashSet<>();
                System.out.println("Connection is successful");
                String query = "SELECT CurrentUser " + "FROM Followers " + "WHERE FollowedUser = \'" + UserModel.getInstance().getUsername()+"\'";
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    user = rs.getString("CurrentUser");
                    userFollowers.add(user);
                }
                followers = addExistingFollowedUsers(userFollowers);
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFollower(IFollowerModel followedUser) {
        try {
            if (getConnection() != null) {
                String sql = "DELETE FROM Followers WHERE FollowedUser='" + followedUser.getCurrentUser() + "' AND CurrentUser= \'" + UserModel.getInstance().getUsername() + "\'";
                Statement st = getConnection().createStatement();
                st.executeUpdate(sql);
                st.close();
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFollower(IFollowerModel followedUser) {
        try {
            Statement temp = getConnection().createStatement();
            temp.executeUpdate("INSERT INTO Followers (CurrentUser, FollowedUser) values ('" + UserModel.getInstance().getUsername() + "', '" + followedUser.getCurrentUser() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}