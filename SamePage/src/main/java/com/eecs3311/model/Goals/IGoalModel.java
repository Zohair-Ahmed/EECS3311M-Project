package com.eecs3311.model.Goals;

import com.eecs3311.presenter.Goals.IGoalPresenter;

public interface IGoalModel {
   void updateGoal();
   void updatePresenter();
   int getLevel();
   int getNumOfBooksRead();
   int getUID();
   String getGoalInfo();
   void setPresenter(IGoalPresenter igp);
   IGoalPresenter getPresenter();
}