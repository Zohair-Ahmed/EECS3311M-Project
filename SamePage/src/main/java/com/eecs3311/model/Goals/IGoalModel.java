package com.eecs3311.model.Goals;

import com.eecs3311.presenter.Goals.IGoalPresenter;

public interface IGoalModel {
   /**
    * Updates the user's goal level and the number of books they read
    */
   void updateGoal();

   /**
    * Sends the updated number of books read and user's goal level to the view through the presenter
    */
   void updatePresenter();

   /**
    * Retrieves the current goal level the user is on
    * @return level
    */
   int getLevel();

   /**
    * Retrieves the current number of books the user has read
    * @return number_of_books
    */
   int getNumOfBooksRead();

   /**
    * Returns the userID
    * @return userId
    */
   int getUID();

   /**
    * Sends formatted label of books read over goal level
    * @return goal info
    */
   String getGoalInfo();

   /**
    * Set presenter
    * @param igp presenter
    */
   void setPresenter(IGoalPresenter igp);

   /**
    * Get presenter
    * @return presenter
    */
   IGoalPresenter getPresenter();
}