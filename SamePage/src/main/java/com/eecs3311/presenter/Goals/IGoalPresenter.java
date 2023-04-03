package com.eecs3311.presenter.Goals;

import com.eecs3311.model.Goals.IGoalModel;
import com.eecs3311.view.Goals.IGoalView;

public interface IGoalPresenter {

    /**
     * Sets the goal model
     * @param igm
     */
    void setModel(IGoalModel igm);

    /**
     * Sets the goal view
     * @param igv
     */
    void setView(IGoalView igv);

    /**
     * Returns the goal view
     * @return
     */
    IGoalView getGoalView();

    /**
     * Returns the goal model
     * @return
     */
    IGoalModel getGoalModel();

    /**
     * Updates the model object from view inputs
     */
    void updateModelFromView();

    /**
     * Updates the UI from model changes
     * @param text - goal formatted text
     * @param level - reading level
     * @param numOfBooksRead - number of books read by user
     */
    void updateViewFromModel(String text, int level, int numOfBooksRead);
}