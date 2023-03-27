package com.eecs3311.persistence.Register;

import com.eecs3311.model.User.UserStub;

import java.util.ArrayList;

public class RegisterStub implements IRegister{
    //list of usernames and email
    private final ArrayList<UserStub> users;
    UserStub userStub = UserStub.getInstance();
    private static RegisterStub instance = null;

    private RegisterStub(){
        users = userStub.userList();
    }
    public static RegisterStub getInstance(){
        if(instance == null){
            instance = new RegisterStub();
        }
        return instance;
    }

    @Override
    public String registerUser(String username, String email, String password) {
        String result = "";
        boolean matchingCredentials = false;
        int i = 0;
        while(i < users.size() && !matchingCredentials){
            if(users.get(i).getUsername().equals(username)){
                result = "A SamePage account with this username already exists";
                matchingCredentials = true;
            }
            else if (users.get(i).getEmail().equals(email)) {
                result = "A SamePage account with this email already exists";
                matchingCredentials = true;
            }
            i++;
        }
        if (!matchingCredentials) {
            userStub.addNewUser(email, username, password);
            //users.add(new UserStub(email,username,password));
            result = "Successfully registered!";
        }
        System.out.println("This is the Register Stub DB");
        return result;
    }

    @Override
    public ArrayList<String> getUserList() {
        ArrayList<String> usernames = new ArrayList<>();
        for(UserStub user : users){
            usernames.add(user.getUsername());
        }
        return usernames;
    }
}
