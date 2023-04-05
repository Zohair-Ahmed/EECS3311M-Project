package com.eecs3311.UnitTesting.persistence.Register;

import com.eecs3311.model.User.UserStub;
import com.eecs3311.persistence.Database;
import com.eecs3311.persistence.Register.IRegister;
import com.eecs3311.persistence.Register.RegisterStub;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class RegisterDBTest {
    RegisterStub registerStub = RegisterStub.getInstance();
    IRegister register = Database.getRegisterInstance();
    ArrayList<UserStub> users = UserStub.getInstance().userList();
    @Test
    void registerUser() {
        //check if user name is already registered in stub
        String result = registerStub.registerUser("test1", "test1@mail.com", "pass1");
        assertEquals(result, "A SamePage account with this username already exists");

        //check if email is already registered in stub
        result = registerStub.registerUser("differentusername", "test1@mail.com", "pass1");
        assertEquals(result, "A SamePage account with this email already exists");

        //Register new user in stub
        result = registerStub.registerUser("different", "different@mail.com", "pass1");
        assertEquals(result, "Successfully registered!");
        assertEquals(users.get(3).getEmail(), "different@mail.com");

        //Register new user in db
        int rand = Math.abs(new Random().nextInt());
        result = register.registerUser("testnewuser"+rand, "testnewuser"+rand+"@mail.com", "pass1");
        assertEquals(result, "Successfully registered!");

        //check if user name is already registered in db
        result = register.registerUser("testnewuser"+rand, "usertrieswithnewemail@mail.com", "pass1");
        assertEquals(result, "A SamePage account with this username already exists");
    }

    @Test
    void getUserList(){
        registerStub.registerUser("test1", "test1@mail.com", "pass1");
        assertEquals(registerStub.getUserList().get(0), "test1");
    }

    @Test
    void getLatestRegisteredID(){
        int num = Math.abs(new Random().nextInt());
        int prevID = registerStub.getLatestRegisterUserID();
        registerStub.registerUser("SamePageTester"+num, "SamePageTester"+num+"@mail.com", "1");
        assertEquals(prevID + 1, registerStub.getLatestRegisterUserID());
    }
}