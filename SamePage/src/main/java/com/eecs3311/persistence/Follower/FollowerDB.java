package com.eecs3311.persistence.Follower;

import com.eecs3311.model.Book.BookModel;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Follower.FollowerModel;
import com.eecs3311.model.Follower.IFollowerModel;
import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.persistence.Database;
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
    private ArrayList<IFollowerModel> users  = new ArrayList<>();
    private String user;

    /**
     * Provide access and perform operations on Follower DB
     */
    public FollowerDB() {
        super();
//        try {
//            if (following == null) {
//                getAllFollowers();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public ArrayList<IFollowerModel> getFollowing() {
        getDBdata();
        return followedUsers;
    }

    public void getDBdata() {
        try {
            if (getConnection() != null) {
                Set<String> userFollowing = new HashSet<>();
                System.out.println("Connection is successful");
                String query = "SELECT FollowedUser " + "FROM Followers " + "WHERE CurrentUser = " + UserModel.getInstance().getUserID();
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    user = rs.getString("FollowedUser");
                    userFollowing.add(user);
                }
                addExistingFollowedUsers(userFollowing);
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void addToList(ArrayList<IFollowerModel> userFollowing) {
//        following = new ArrayList<>();
//        for (IFollowerModel ifm : userFollowing) {
//            IFollowerPresenter ifp = new FollowerPresenter();
//            IFollowerView ifv = new FollowerView(ifm.getCurrentUser());
//            ifp.setModel(ifm);
//            ifm.setPresenter(ifp);
//            ifp.setView(ifv);
//            ifv.setPresenter(ifp);
//            following.add(ifm);
//        }
//    }

    public void addExistingFollowedUsers(Set<String> info) {
        followedUsers = new ArrayList<>();
        users.parallelStream().forEach(user -> {
            if (info.contains(user.getCurrentUser()))
                this.followedUsers.add(user);
        });
    }

    public ArrayList<IFollowerModel> getUserList(){
        if(users.isEmpty()){
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
                    this.users.add(ifm);
                }
                //removes the user currently logged in from the list
//                this.users.remove(UserModel.getInstance().getUsername());
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

//    public void getAllFollowers() {
//        try {
//            if (getConnection() != null) {
//                ArrayList<IFollowerModel> info = new ArrayList<>();
//                System.out.println("Connection is successful");
//                String query = "SELECT * FROM Followers";
//                Statement st = getConnection().createStatement();
//                // execute the query, and get a java resultset
//                ResultSet rs = st.executeQuery(query);
//                // iterate through the java resultset
//                while (rs.next()) {
//                    currentUser = rs.getString("CurrentUser");
//                    followedUser = rs.getString("FollowedUser");
//                    info.add(new FollowerModel(currentUser, followedUser));
//                }
//                addToList(info);
//            } else {
//                System.out.println("Failed to connect");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void removeFollower(IFollowerModel followedUser) {
        try {
            if (getConnection() != null) {
                String sql = "DELETE FROM Followers WHERE FollowedUser='" + followedUser.getCurrentUser() + "' AND CurrentUser=" + UserModel.getInstance().getUsername() + "";
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

//    public void updateFollowerCount(IFollowerModel followedUser) {
//        try {
//            if (getConnection() != null) {
//                String sql = "SELECT COUNT(*) AS FollowerCount\n" +
//                        "FROM Followers\n" +
//                        "WHERE FollowedUser ='" + followedUser.getCurrentUser() + "'";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
