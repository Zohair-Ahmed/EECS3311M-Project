package com.eecs3311.model.Goals;

import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Goals.IGoalPresenter;

public class GoalModel implements  IGoalModel{
    private IGoalPresenter igp;
    private int uid;
    private int level = 1;
    private int numOfBooksRead = 0;

    public GoalModel(int uid){
        this.uid = uid;
    }

    @Override
    public void updateGoal() {
        if (numOfBooksRead + 1 >= level*10)
            level++;
        numOfBooksRead++;
        Database.getGoalInstance().updateGoal(uid); // Called to sore values into database
        getPresenter().updateViewFromModel(getGoalInfo(), getLevel(), getNumOfBooksReads());
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getNumOfBooksReads() {
        return numOfBooksRead;
    }

    @Override
    public String getGoalInfo() {
        return getNumOfBooksReads() + " / " + (getLevel()*10);
    }

    @Override
    public int getUID() {
        return uid;
    }

    @Override
    public void setPresenter(IGoalPresenter igp) {
        this.igp = igp;
    }

    @Override
    public IGoalPresenter getPresenter() {
        return igp;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setNumOfBooksRead(int numOfBooksRead) {
        this.numOfBooksRead = numOfBooksRead;
    }
}
