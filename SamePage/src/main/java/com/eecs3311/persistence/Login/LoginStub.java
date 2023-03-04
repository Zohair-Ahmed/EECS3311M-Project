package com.eecs3311.persistence.Login;

public class LoginStub implements ILogin{

    @Override
    public boolean isLoginValid(String email, String password) {
        System.out.println("This is the Login Stub DB");
        return false;
    }
}
