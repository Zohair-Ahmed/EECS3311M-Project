package com.eecs3311.model.Goals;

import com.eecs3311.presenter.Goals.IGoalPresenter;

public interface IGoalModel {

    /**
     * Updates the value for level and number of books read in the Gaol database. Sends update to UI for new values
     */
    void updateGoal();

    /**
     * Returns the goal level of user reading goals
     * @return level
     */
    int getLevel();

    /**
     * Returns the number of books read by the reader
     * @return books read
     */
    int getNumOfBooksReads();

    /**
     * Returns the reformatted ratio of books read and goal limit
     * @return info
     */
    String getGoalInfo();

    /**
     * Sets the presenter
     * @param igp
     */
    void setPresenter(IGoalPresenter igp);

    /**
     * Gets the presenter
     * @return
     */
    IGoalPresenter getPresenter();
}
