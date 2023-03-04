package com.eecs3311.persistence.Register;

public class RegisterStub implements IRegister{
    @Override
    public String registerUser(String username, String email, String password) {
        System.out.println("This is the Register Stub DB");
        return null;
    }
}
