package com.eecs3311.presenter.Goals;

import com.eecs3311.model.Goals.IGoalModel;
import com.eecs3311.view.Goals.IGoalView;

public class GoalPresenter implements IGoalPresenter{
    private IGoalView igv;
    private IGoalModel igm;

    @Override
    public void setModel(IGoalModel igm) {
        this.igm = igm;
    }

    @Override
    public void setView(IGoalView igv) {
        this.igv = igv;
    }

    @Override
    public IGoalView getGoalView() {
        return igv;
    }

    @Override
    public IGoalModel getGoalModel() {
        return igm;
    }

    @Override
    public void updateModelFromView() {
        getGoalModel().updateGoal();
        getGoalModel().updatePresenter();
    }

    @Override
    public void updateViewFromModel(String text, int level, int numOfBooksRead) {
        getGoalView().updateLabel(text);
        getGoalView().updateNumbers(level, numOfBooksRead);
    }
}