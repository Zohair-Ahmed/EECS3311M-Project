package com.eecs3311.model.Goals;

import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Goals.IGoalPresenter;

public class GoalModel implements  IGoalModel{
    private IGoalPresenter igp;

    @Override
    public void updateGoal() {
        Database.getGoalInstance().updateGoal();
        getPresenter().updateViewFromModel(getGoalInfo(), getLevel(), getNumOfBooksReads());
    }

    @Override
    public int getLevel() {
        return Database.getGoalInstance().getLevel();
    }

    @Override
    public int getNumOfBooksReads() {
        return Database.getGoalInstance().getNumOfBooksRead();
    }

    @Override
    public String getGoalInfo() {
        return getNumOfBooksReads() + " / " + (getLevel()*10);
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
