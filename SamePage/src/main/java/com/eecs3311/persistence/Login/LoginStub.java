package com.eecs3311.persistence.Login;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.model.User.UserStub;

import java.util.ArrayList;

public class LoginStub implements ILogin{

    private final ArrayList<UserStub> users;
    UserStub userStub = UserStub.getInstance();
    private static LoginStub instance = null;

    private LoginStub(){
        users = userStub.userList();
    }
    public static LoginStub getInstance(){
        if(instance == null){
            instance = new LoginStub();
        }
        return instance;
    }

    @Override
    public boolean isLoginValid(String email, String password) {
        int i =0;
        while(i < users.size()){
            if(users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)){
                UserModel.getInstance().setEmail(email);
                UserModel.getInstance().setUsername(users.get(i).getUsername());
                UserModel.getInstance().setPassword(password);
                return true;
            }
            i++;
        }
        System.out.println("This is the Login Stub DB");
        return false;
    }
}
