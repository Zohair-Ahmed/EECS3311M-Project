package com.eecs3311.model.Register;

import com.eecs3311.presenter.Register.IRegisterPresenter;
import com.eecs3311.persistence.Register.RegisterDB;


public class RegisterModel implements IRegisterModel {
    private IRegisterPresenter RegisterPresenter;
    private RegisterDB registerDB;
    private String username;
    private String email;
    private String password;

    @Override
    public IRegisterPresenter getPresenter() {
        return RegisterPresenter;
    }

    @Override
    public void setPresenter(IRegisterPresenter irp) {
        this.RegisterPresenter = irp;
    }

    @Override
    public void updateModelFromView(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

        validateRegister();
    }

    private void validateRegister() {
        // Write Query
        // Send query to db
        // Get results from db
        // Compare results
        // Based on results, update view

        String queryResult = registerDB.registerUser(this.username, this.email, this.password);

        this.getPresenter().updateViewFromModel(queryResult);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
	public RegisterDB getRegisterDB() {
		return this.registerDB;
	}

	@Override
	public void setRegisterDB(RegisterDB rdb) {
		this.registerDB = rdb;
	}

}