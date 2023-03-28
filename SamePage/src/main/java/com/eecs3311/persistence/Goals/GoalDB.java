package com.eecs3311.persistence.Goals;

import com.eecs3311.model.User.User;
import com.eecs3311.persistence.AbstractDatabase;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GoalDB extends AbstractDatabase implements IGoal{

    /**
     * MySQL procedure "UpdateGoals" invoked
     */
    @Override
    public void updateGoal() {
        try {
            String query = "{CALL UpdateGoals(?)}";
            CallableStatement updateGoalsProcedure =  getConnection().prepareCall(query);
            updateGoalsProcedure.setInt("UID", User.getInstance().getUserID());
            ResultSet rs = updateGoalsProcedure.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLevel() {
        try {
            String query = "SELECT Level FROM Goals WHERE UserID = "+ User.getInstance().getUserID()+";";
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                return rs.getInt("Level");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int getNumOfBooksRead() {
        try {
            String query = "SELECT NumOfBooksRead FROM Goals WHERE UserID = "+ User.getInstance().getUserID()+";";
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                return rs.getInt("NumOfBooksRead");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
