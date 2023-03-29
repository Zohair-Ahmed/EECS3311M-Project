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
        Database.getGoalInstance().updateGoal(uid);
        getPresenter().updateViewFromModel(getGoalInfo(), getLevel(), getNumOfBooksReads());
    }

    @Override
    public int getLevel() {
        level = Database.getGoalInstance().getLevel(uid);
        return level;
    }

    @Override
    public int getNumOfBooksReads() {
        numOfBooksRead = Database.getGoalInstance().getNumOfBooksRead(uid);
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
