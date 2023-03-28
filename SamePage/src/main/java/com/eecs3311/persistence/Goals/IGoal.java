package com.eecs3311.persistence.Goals;

public interface IGoal {
    /**
     * Increments the number of books the user has read and updates their goal level
     */
    void updateGoal();

    /**
     * Retrieves the level the user is currently on for their goal
     * @return level
     */
    int getLevel();

    /**
     * Retrieves the number of books read from the user
     * @return number of books read
     */
    int getNumOfBooksRead();
}
