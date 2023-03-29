package com.eecs3311.persistence.Goals;

import com.eecs3311.model.Goals.GoalModel;
import com.eecs3311.model.Goals.IGoalModel;
import com.eecs3311.model.User.User;
import com.eecs3311.model.User.UserStub;

import java.util.ArrayList;

public class GoalStub implements IGoal{

    ArrayList<int[]> userGoalsValues = new ArrayList<>();

    @Override
    public void updateGoal(int uid) {
        IGoalModel goal = UserStub.getInstance().getUserGoals().stream().filter(g -> g.getUID() == uid).findFirst().get();
        if (goal.getNumOfBooksReads() >= goal.getLevel()*10) {
            goal.setLevel(goal.getLevel()+1);
        }
        goal.setNumOfBooksRead(goal.getNumOfBooksReads()+1);
    }

    @Override
    public int getLevel(int uid) {
        return 0;
    }

    @Override
    public int getNumOfBooksRead(int uid) {
        return 0;
    }
}
