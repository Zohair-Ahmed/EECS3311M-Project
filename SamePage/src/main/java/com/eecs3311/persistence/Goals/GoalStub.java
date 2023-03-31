package com.eecs3311.persistence.Goals;

import com.eecs3311.model.User.UserStub;

import java.util.ArrayList;

public class GoalStub implements IGoals{

    // Holds user goal information
    private ArrayList<int[]> goalStubDB = new ArrayList<>();

    private static GoalStub instance = null;

    public static GoalStub getInstance() {
        if (instance == null)
            instance = new GoalStub();
        return instance;
    }

    private GoalStub(){
        generateDBs(goalStubDB);
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

    /**
     * Fills in the stub wth goal information based on userID
     * @param db Stub Database
     */
    private void generateDBs(ArrayList<int[]> db){
        UserStub.getInstance().userList().forEach(u -> {
            db.add(new int [] {u.getUserID(), 1, 0}); // UID, LEVEL, NUM_OF_BOOKS
        });
    }

    public ArrayList<int[]> getGoalStubDB(){
        return goalStubDB;
    }

    /**
     * Resets the Stub Database to the original
     */
    public void setBackToOriginal() {
        goalStubDB = new ArrayList<>();
        UserStub.getInstance().userList().forEach(u -> {
            goalStubDB .add(new int [] {u.getUserID(), 1, 0}); // UID, LEVEL, NUM_OF_BOOKS
        });
    }
}
