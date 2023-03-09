package com.eecs3311.view.Login;

import javax.swing.*;

import com.eecs3311.presenter.Login.ILoginPresenter;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.layout.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPanel implements ILoginPanelView, IPanelView, ActionListener {

	// Creating components that will be used for the Login page
	private JPanel panel = new JPanel();
	private JTextField emailField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel email = new JLabel("Email: ");
	private JLabel password = new JLabel("Password: ");
	private JButton loginSubmit = new JButton("Login");
	private JLabel lblHeader = new JLabel("Login to SamePage");
	private JLabel loginStatus = new JLabel("status pending");
	private SpringLayout layout = new SpringLayout();
	private Main main;
	private Timer timer;


	private ILoginPresenter loginPresenter;

	// Constructor for an instance of the Login page
	public LoginPanel() {
		initComponents();

		timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Switch to the landing frame panel
				main.getCard().show(main.getContainer(), "Landing");
				main.addProfilePanel();
			}
		});
		timer.setRepeats(false); // Only perform the action once
		timer.stop(); // Stop the timer initially
	}

	@Override
	public void initComponents() {
		setSizeOfComponents();

		loginSubmit.addActionListener(this);
		layout.putConstraint(SpringLayout.NORTH, lblHeader, 26, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, lblHeader, 0, SpringLayout.EAST, emailField);
		panel.setLayout(layout);

		// Adding components to the JPanel
		panel.add(lblHeader);
		panel.add(email);
		panel.add(emailField);
		panel.add(password);
		panel.add(passwordField);
		panel.add(loginSubmit);
		panel.add(loginStatus);
		initSpringLayout();
	}

	// Resizing and setting the design of the components within the panel
	private void setSizeOfComponents() {
		lblHeader.setHorizontalAlignment(JLabel.CENTER);
		lblHeader.setFont(new Font("Futura", Font.BOLD, 23));

		password.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

		emailField.setPreferredSize(new Dimension(150, emailField.getPreferredSize().height));
		passwordField.setPreferredSize(new Dimension(150, passwordField.getPreferredSize().height));
	}

	// Setting the sizing and format of the panel
	private void initSpringLayout() {
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeader, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, email, 100, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, email, -50, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, emailField, 100, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, emailField, 50, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, password, 130, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, password, -55, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, passwordField, 130, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordField, 105, SpringLayout.HORIZONTAL_CENTER,
				password);
		layout.putConstraint(SpringLayout.NORTH, loginSubmit, 180, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginSubmit, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginStatus, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.SOUTH, loginStatus, -100, SpringLayout.SOUTH, panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginSubmit) {
			this.loginPresenter.updateModelFromView(getEmail(), getPassword());
		}
	}

	public String getEmail() {
		return emailField.getText();
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	public void updateLoginStatus(String status) {
		loginStatus.setText(status);

		if (checkLogin()) {
			// Start the timer if the login is successful
			timer.start();
		}
	}

	@Override
	public JPanel getView() {
		return panel;
	}

	@Override
	public JPanel getParentContainer() {
		return null;
	}

	@Override
	public void setParentContainer(JPanel parent) {
	}

	@Override
	public ILoginPresenter getPresenter() {
		return this.loginPresenter;
	}

	@Override
	public void setPresenter(ILoginPresenter ilp) {
		this.loginPresenter = ilp;
	}

	public Main getMainInit() {
		return this.main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public boolean checkLogin() {
		if (loginStatus.getText().contains("Successfully Logged in")) {
			return true;
		}

		else {
			return false;
		}
	}



}