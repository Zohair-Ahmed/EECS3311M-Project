package com.eecs3311.persistence.Goals;

import com.eecs3311.model.User.UserStub;

import java.util.ArrayList;

public class GoalStub implements IGoals{

    private ArrayList<int[]> goalStubDB = new ArrayList<>();
    private static GoalStub instance = null;

    public static GoalStub getInstance() {
        if (instance == null)
            instance = new GoalStub();
        return instance;
    }

    private GoalStub(){
        UserStub.getInstance().userList().forEach(u -> {
            goalStubDB.add(new int [] {u.getUserID(), 1, 0}); // UID, LEVEL, NUM_OF_BOOKS
        });
    }

    @Override
    public void updateGoals(int uid) {
        for (int[] goal : goalStubDB) {
            if (goal[0] == uid) {
                if (goal[2] + 1 >= goal[1]*10)
                    goal[1]++;
                goal[2]++;
                break;
            }
        }
    }

    @Override
    public int getLevel(int uid) {
        for (int[] goal : goalStubDB) {
            if (goal[0] == uid)
                return goal[1];
        }
        return -1;
    }

    @Override
    public int getNumberOfBooksRead(int uid) {
        for (int[] goal : goalStubDB) {
            if (goal[0] == uid)
                return goal[2];
        }
        return -1;
    }

    public ArrayList<int[]> getGoalStubDB(){
        return goalStubDB;
    }

    public void setGoalStubDB(ArrayList<int[]> db) {
        this.goalStubDB = db;
    }
}
