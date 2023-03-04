package com.eecs3311.persistence.Login;

public interface ILogin {
    /**
     * Runs a query to validate the Login based on email and password
     *
     * @param email    user email
     * @param password user password
     * @return True - valid login
     */
    boolean isLoginValid(String email, String password);
}
