package com.eecs3311.model.Goals;

import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Goals.IGoalPresenter;

public class GoalModel implements  IGoalModel{

    private IGoalPresenter igp;
    private int uid;

    public GoalModel(int uid){
        this.uid = uid;
    }

    @Override
    public void updateGoal() {
        Database.getGoalInstance().updateGoals(getUID());
    }

    @Override
    public void updatePresenter() {
        getPresenter().updateViewFromModel(getGoalInfo(), getLevel(), getNumOfBooksRead());
    }

    @Override
    public int getLevel() {
        return Database.getGoalInstance().getLevel(getUID());
    }

    @Override
    public int getNumOfBooksRead() {
        return Database.getGoalInstance().getNumberOfBooksRead(getUID());
    }

    @Override
    public int getUID(){
        return uid;
    }

    @Override
    public String getGoalInfo() {
        return getNumOfBooksRead() + " / " + getLevel()*10;
    }

    @Override
    public void setPresenter(IGoalPresenter igp) {
        this.igp = igp;
    }

    @Override
    public IGoalPresenter getPresenter() {
        return igp;
    }
}
