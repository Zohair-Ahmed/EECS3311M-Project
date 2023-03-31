package com.eecs3311.view.Goals;

import com.eecs3311.presenter.Goals.IGoalPresenter;

import javax.swing.*;

public interface IGoalView {
    /**
     * Returns the goal presenter
     * @return presenter
     */
    IGoalPresenter getPresenter();

    /**
     * Sets the presenter
     * @param igp presenter
     */
    void setPresenter(IGoalPresenter igp);

    /**
     * Retrieves the updated the gaol text
     * @param text reformatted ratio
     */
    void updateLabel(String text);

    /**
     * Retrieves the updated goal values for the UI
     * @param level goal level
     * @param numOfBooksRead total books read
     */
    void updateNumbers(int level, int numOfBooksRead);

    /**
     * Retrieves root view of Goal Panel
     * @return Goal View
     */
    JPanel getView();

    /**
     * Initialize UI components
     */
    void initComponents();
}