package com.eecs3311.persistence;

import com.eecs3311.persistence.Book.*;
import com.eecs3311.persistence.Follower.FollowerDB;
import com.eecs3311.persistence.Follower.IFollower;
import com.eecs3311.persistence.Goals.GoalDB;
import com.eecs3311.persistence.Goals.GoalStub;
import com.eecs3311.persistence.Goals.IGoals;
import com.eecs3311.persistence.Login.*;
import com.eecs3311.persistence.Register.*;
import com.eecs3311.persistence.Review.*;
import com.eecs3311.persistence.Wishlist.IWishlist;
import com.eecs3311.persistence.Wishlist.WishlistDB;
import com.eecs3311.persistence.Wishlist.WishlistStub;
import com.eecs3311.util.log.console.ConsoleLogs;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


// Single Pattern
public class Database extends AbstractDatabase {

    private static ILogin login;
    private static IRegister register;
    private static IBook book;
    private static IFavBooks favBooks;
    private static IReview review;
    private static IFollower follower;
    private static IGoals goal;
    private static IWishlist wishlist;
    private static Database database;
    // isUsingStub To use stub = True | To use real db = False
    private static boolean isUsingStubDB = false;

    private Database(){
        if (!isUsingStubDB) {
            configDBScript();
            login = new LoginDB();
            register = new RegisterDB();
            book = new BookDB();
            favBooks = new FavBooksDB();
            review = new ReviewDB();
            follower = new FollowerDB();
            goal = new GoalDB();
            wishlist = new WishlistDB();
        } else {
            login = LoginStub.getInstance();
            register = RegisterStub.getInstance();
            book = BookStub.getInstance();
            review = ReviewStub.getInstance();
            goal = GoalStub.getInstance();
        }
    }

    public static void setIsUsingStubDB(boolean val) {
        isUsingStubDB = val;
        database = new Database();
    }

    /**
     * Run the initial database setup script
     *
     * Will configure necessary tables and login if needed
     */
    private void configDBScript() {
        try { // Checking if database exists
            String sql = "SELECT * FROM Book WHERE ISBN13 = 9789000307975";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // If it does exist, then do not run script
                System.out.println(ConsoleLogs.DATABASE("Database exists."));
            } else {
                try { //If no data exists, then run db script
                    InputStream getDBScript = this.getClass().getClassLoader().getResourceAsStream("data/samepageschema.sql");
                    ScriptRunner sr = new ScriptRunner(getConnection());
                    assert getDBScript != null;
                    Reader readDBScript = new InputStreamReader(getDBScript);
                    sr.runScript(readDBScript);
                    System.out.println( ConsoleLogs.SUCCESSFUL("SUCCESSFULLY CONFIGURED THE DATABASE SETUP SCRIPT..."));
                } catch (Exception e) {
                    System.out.println(ConsoleLogs.ERROR("--- !ERROR! COULD NOT CONFIGURE DATABASE SETUP SCRIPT ---"));
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the class instance. Uses dependency injection to switch between stub and real-time database
     */
    public static Database getInstance() {
        if (database == null)
            database = new Database();
        return database;
    }

    public static ILogin getLoginInstance() {
        database = Database.getInstance();
        return login;
    }

    public static IRegister getRegisterInstance() {
        database = Database.getInstance();
        return register;
    }

    public static IBook getBookInstance() {
        database = Database.getInstance();
        return book;
    }

    public static IFavBooks getFavBooksInstance() {
        database = Database.getInstance();
        return favBooks;
    }

    public static IReview getReviewInstance() {
        database = Database.getInstance();
        return review;
    }

    public static IFollower getFollowerInstance() {
        database = Database.getInstance();
        return follower;
    }

    public static IGoals getGoalInstance() {
        database = Database.getInstance();
        return goal;
    }

    public static IWishlist getWishlistInstance() {
        database = Database.getInstance();
        return wishlist;
    }
}
