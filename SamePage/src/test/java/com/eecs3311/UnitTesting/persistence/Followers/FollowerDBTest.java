package com.eecs3311.UnitTesting.persistence.Followers;

import com.eecs3311.model.Follower.FollowerModel;
import com.eecs3311.model.Follower.IFollowerModel;
import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class FollowerDBTest {

    ArrayList<String[]> emails = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Database.setIsUsingStubDB(false);

        for (int i=0; i<3; i++) {
            int rand = Math.abs(new Random().nextInt());
            Database.getRegisterInstance().registerUser("testnewuser"+rand, "testnewuser"+rand+"@mail.com", "1");
            emails.add(new String[] {"testnewuser"+rand, "testnewuser"+rand+"@mail.com", "1"});
        }
        Database.getLoginInstance().isLoginValid(emails.get(0)[1], "1");
    }

    @Test
    void AddFollower() {
        IFollowerModel userToBeFollowed = new FollowerModel(emails.get(2)[0], UserModel.getInstance().getUsername());
        Database.getFollowerInstance().addFollower(userToBeFollowed);
        ArrayList<IFollowerModel> followers = Database.getFollowerInstance().getFollowers(UserModel.getInstance().getUsername());
        assertTrue(followers.size() == 0);
        ArrayList<IFollowerModel> following = Database.getFollowerInstance().getFollowing(emails.get(2)[0]);
        assertTrue(following.size() == 0);
    }


}
