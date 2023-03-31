package com.eecs3311.persistence.Goals;
import com.eecs3311.persistence.AbstractDatabase;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class GoalDB extends AbstractDatabase implements IGoals{

    /**
     * MySQL procedure "UpdateGoals" invoked - Procedure used instead to avoid checking at this level
     */
    @Override
    public void updateGoals(int uid) {
        try {
            String query = "{CALL UpdateGoals(?)}";
            CallableStatement updateGoalsProcedure =  getConnection().prepareCall(query);
            updateGoalsProcedure.setInt("UID", uid);
            ResultSet rs = updateGoalsProcedure.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLevel(int uid) {
        try {
            String query = "SELECT Level FROM Goals WHERE UserID = "+ uid+";";
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
    public int getNumberOfBooksRead(int uid) {
        try {
            String query = "SELECT NumOfBooksRead FROM Goals WHERE UserID = "+ uid+";";
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                return rs.getInt("NumOfBooksRead");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
