package com.eecs3311.persistence.Register;

public interface IRegister {
    /**
     * Performs a retrieval query first in order to check credentials for possible matches in the database,
     * otherwise it will perform an insert update to add a new SamePage user to the database with the provided
     * username, email and password from the client input
     * Returns a string to model with status update of the register attempt that will be passed to the view (GUI)
     *
     * @param username username
     * @param email email
     * @param password password
     * @return Message to display onto Register Page
     */
    String registerUser(String username, String email, String password);
}
