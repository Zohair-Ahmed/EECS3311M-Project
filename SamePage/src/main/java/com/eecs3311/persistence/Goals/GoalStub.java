package com.eecs3311.persistence.Goals;

import com.eecs3311.model.Goals.GoalModel;
import com.eecs3311.model.Goals.IGoalModel;
import com.eecs3311.model.User.User;
import com.eecs3311.model.User.UserStub;
import com.eecs3311.persistence.Review.ReviewStub;

import java.util.ArrayList;
import java.util.Optional;

public class GoalStub implements IGoal{

    public ArrayList<IGoalModel> goalDB = new ArrayList<>();
    public static GoalStub instance = null;

    public ArrayList<IGoalModel> getGoalDB(){
        return goalDB;
    }

    public static GoalStub getInstance() {
        if (instance == null)
            instance = new GoalStub();
        return instance;
    }
    private GoalStub () {
        UserStub.getInstance().userList().forEach(user -> {
            goalDB.add(new GoalModel(user.getUserID()));
        });
    }

    @Override
    public void updateGoal(int uid) {
    }

    @Override
    public int getLevel(int uid) {
        return 0;
    }

    @Override
    public int getNumOfBooksRead(int uid) {
        return 0;
    }

    @Override
    public void setLevel(int level) {

    }

    @Override
    public void setNumOfBooksRead(int level) {

    }

    private IGoalModel findGoalModel(int uid) {
        IGoalModel goal = null;
        for (IGoalModel igm : UserStub.getInstance().getUserGoals()) {
            if (igm.getUID() == uid) {
                goal = igm;
                break;
            }
        }
        return goal;
    }
}
