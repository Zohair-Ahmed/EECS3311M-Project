package com.eecs3311.persistence.Goals;

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

    }

    @Override
    public void updateGoals(int uid) {

    }

    @Override
    public int getLevel(int uid) {
        return 0;
    }

    @Override
    public int getNumberOfBooksRead(int uid) {
        return 0;
    }

    public ArrayList<int[]> getGoalStubDB(){
        return goalStubDB;
    }

    public void setGoalStubDB(ArrayList<int[]> db) {
        this.goalStubDB = db;
    }
}
