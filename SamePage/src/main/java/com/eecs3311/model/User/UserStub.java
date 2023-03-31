package com.eecs3311.model.User;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.model.enums.State;

import java.util.ArrayList;

public class UserStub {
    private String username;
    private String email;
    private String password;
    private int userID;
    private State loginState = State.GUEST;
    private ArrayList<UserStub> users = new ArrayList<>();
    private ArrayList<IReviewModel> userReviews = new ArrayList<>();
    private ArrayList<IBookModel> favourites = new ArrayList<>();
    private static UserStub instance = null;

    public UserStub(int userID, String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    private UserStub(){
        users.add(new UserStub(1,"test1@mail.com","test1", "pass1"));
        users.add(new UserStub(2,"test2@mail.com","test2", "pass2"));
        users.add(new UserStub(3,"test3@mail.com","test3", "pass3"));
    }

    public ArrayList<UserStub> userList(){
        return users;
    }

    public ArrayList<IReviewModel> getUserReviews(){
        return userReviews;
    }

    public ArrayList<IBookModel> getFavourites() {return favourites;}

    public static UserStub getInstance(){
        if(instance == null){
            instance = new UserStub();
        }
        return instance;
    }

    public void addNewUser(int userID, String email, String username, String password){
        users.add(new UserStub(userID, email, username,password));
    }

    public void addBookToFavourite(IBookModel ibm) {
        boolean found = false;
        for (IBookModel favIbm : favourites) {
            if (ibm.getISBN().equals(favIbm.getISBN())){
                found = true;
                break;
            }
        }

        if (!found)
            favourites.add(ibm);
    }

    public void setFavourites(ArrayList<IBookModel> favs){
        this.favourites = favs;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        if (loginState.equals(State.GUEST))
            return "Not Logged In";
        return "Current User = " + getEmail() + " - " + getPassword();
    }

}
