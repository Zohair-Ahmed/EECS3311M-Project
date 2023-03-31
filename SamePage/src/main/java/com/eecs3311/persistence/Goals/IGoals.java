package com.eecs3311.persistence.Goals;

public interface IGoals {

    /**
     * Update the goal values based on userID
     * @param uid userID
     */
    void updateGoals(int uid);

    /**
     * @param uid userID
     * @return level the user is currently on
     */
    int getLevel(int uid);

    /**
     * @param uid userID
     * @return number of books user has read
     */
    int getNumberOfBooksRead(int uid);

}
