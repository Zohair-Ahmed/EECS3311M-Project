package com.eecs3311.UnitTesting.persistence.Login;

import com.eecs3311.persistence.Database;
import com.eecs3311.persistence.Login.ILogin;
import com.eecs3311.persistence.Login.LoginStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDBTest {
    LoginStub loginStub = LoginStub.getInstance();
    ILogin login = Database.getLoginInstance();
    @Test
    void isLoginValid() {
        //check stub login with correct email, wrong password
        boolean result = loginStub.isLoginValid("test1@mail.com", "wrongpass");
        assertFalse(result);

        //check stub login with correct credentials
        result = loginStub.isLoginValid("test1@mail.com", "pass1");
        assertTrue(result);

        //check if user is logging in with credentials that haven't been registered
        result = loginStub.isLoginValid("test5@mail.com", "pass1");
        assertFalse(result);

        //check db login with correct email, wrong password
        result = login.isLoginValid("test1@mail.com", "wrongpass");
        assertFalse(result);
    }
}