package com.eecs3311.view.layout;

import com.eecs3311.view.IPanelView;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class RegisterPanel implements IPanelView {

	private JPanel containerPanel = new JPanel();

	// Text labels
	private JLabel lblUserCheck;
	private JLabel lblEmailCheck;
	private JLabel lblPassCheck;
	private JLabel lblConfCheck;
	private JLabel lblTermsCheck;
	private JLabel lblConfirmation;

	// Input fields
	private JTextField tfUsername;
	private JTextField tfEmail;
	private JPasswordField tfPassword;
	private JPasswordField tfConfirmPass;
	private JCheckBox cbTerms;

	// Layout variables
	private SpringLayout sl_containerPanel = new SpringLayout();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the GUI frame. Plan to Refactor in Itr2
	 */
	public RegisterPanel() {
		containerPanel.setSize(500, 500);
		containerPanel.setLayout(sl_containerPanel);
		initComponents();
	}

	@Override
	public void initComponents() {
		// Initializing text labels
		initRegisterLabel();
		initUsernameLabel();
		initUserCheck();
		initEmailLabel();
		initEmailCheck();
		initPasswordLabel();
		initPasswordCheck();
		initConfirmPasswordLabel();
		initConfirmPasswordCheck();
		initTermsAndConditionsLabel();
		initMessageLabel();

		// Initializing input fields
		initUsernameTextField();
		initEmailTextField();
		initPasswordTextField();
		initConfirmPasswordField();
		initTermsAndConditionsCheckbox();
		initRegisterButton();
	}

	private void initRegisterButton() {
		JButton btnRegister = new JButton("Register");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, btnRegister, 373, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, btnRegister, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, btnRegister, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		// Logic for checking all required fields have valid input upon clicking
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckFields(tfUsername, lblUserCheck);
				CheckFields(tfEmail, lblEmailCheck);
				CheckFields(tfPassword, lblPassCheck);
				CheckFields(tfConfirmPass, lblConfCheck);
				if (!cbTerms.isSelected())
					lblTermsCheck.setText("*");
				else
					lblTermsCheck.setText("");
				ValidateFields();
			}
		});
		containerPanel.add(btnRegister);
	}

	// Register label
	private void initRegisterLabel() {
		JLabel lblRegisterHere = new JLabel("Register Here");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblRegisterHere, 6, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblRegisterHere, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.SOUTH, lblRegisterHere, 62, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblRegisterHere, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		lblRegisterHere.setFont(new Font("Segoe print", Font.BOLD, 25));
		lblRegisterHere.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegisterHere.setHorizontalAlignment(SwingConstants.CENTER);
		containerPanel.add(lblRegisterHere);
	}

	// Username label
	private void initUsernameLabel() {
		JLabel lblUsername = new JLabel("Username:");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblUsername, 89, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblUsername, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblUsername, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		containerPanel.add(lblUsername);
	}

	// Initializing user check label (* icon)
	private void initUserCheck() {
		lblUserCheck = new JLabel("");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblUserCheck, 115, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblUserCheck, (int) (screenSize.width / 2.04),
				SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.SOUTH, lblUserCheck, 131, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblUserCheck, (int) (screenSize.width / 1.9),
				SpringLayout.WEST,
				containerPanel);
		lblUserCheck.setForeground(new Color(255, 26, 10));
		lblUserCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		containerPanel.add(lblUserCheck);
	}

	// Initializing email label
	private void initEmailLabel() {
		JLabel lblEmail = new JLabel("Email:");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblEmail, 159, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblEmail, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblEmail, (screenSize.width / 2),
				SpringLayout.WEST, containerPanel);
		containerPanel.add(lblEmail);
	}

	// Initializing email check label (* icon)
	private void initEmailCheck() {
		lblEmailCheck = new JLabel("");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblEmailCheck, 181, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblEmailCheck, (int) (screenSize.width / 2.04),
				SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.SOUTH, lblEmailCheck, 197, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblEmailCheck, (int) (screenSize.width / 1.9),
				SpringLayout.WEST,
				containerPanel);
		lblEmailCheck.setForeground(new Color(255, 26, 10));
		lblEmailCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		containerPanel.add(lblEmailCheck);
	}

	// Initializing password label
	private void initPasswordLabel() {
		JLabel lblPassword = new JLabel("Password:");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblPassword, 225, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblPassword, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblPassword, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		containerPanel.add(lblPassword);
	}

	// Initializing username text field
	private void initUsernameTextField() {
		tfUsername = new JTextField();
		sl_containerPanel.putConstraint(SpringLayout.NORTH, tfUsername, 110, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, tfUsername, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, tfUsername, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		containerPanel.add(tfUsername);
		tfUsername.setColumns(10);
	}

	// Initializing status message label
	private void initMessageLabel() {
		lblConfirmation = new JLabel("");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblConfirmation, 414, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblConfirmation, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.SOUTH, lblConfirmation, 430, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblConfirmation, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		lblConfirmation.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 11));
		lblConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		containerPanel.add(lblConfirmation);
	}

	private void initTermsAndConditionsLabel() {
		lblTermsCheck = new JLabel("");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblTermsCheck, 350, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblTermsCheck, (int) (screenSize.width / 2.7),
				SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.SOUTH, lblTermsCheck, 366, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblTermsCheck, (int) (screenSize.width / 2),
				SpringLayout.WEST,
				containerPanel);
		lblTermsCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblTermsCheck.setForeground(new Color(255, 25, 9));
		lblTermsCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		containerPanel.add(lblTermsCheck);
	}

	private void initTermsAndConditionsCheckbox() {
		cbTerms = new JCheckBox("I have read the terms and conditions");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, cbTerms, 346, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, cbTerms, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, cbTerms, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		containerPanel.add(cbTerms);
	}

	private void initConfirmPasswordField() {
		tfConfirmPass = new JPasswordField();
		sl_containerPanel.putConstraint(SpringLayout.NORTH, tfConfirmPass, 308, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, tfConfirmPass, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, tfConfirmPass, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		containerPanel.add(tfConfirmPass);
		tfConfirmPass.setColumns(10);
	}

	private void initConfirmPasswordCheck() {
		lblConfCheck = new JLabel("");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblConfCheck, 313, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblConfCheck, (int) (screenSize.width / 2.04),
				SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.SOUTH, lblConfCheck, 329, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblConfCheck, (int) (screenSize.width / 1.9),
				SpringLayout.WEST,
				containerPanel);
		lblConfCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfCheck.setForeground(new Color(255, 25, 9));
		lblConfCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		containerPanel.add(lblConfCheck);
	}

	private void initConfirmPasswordLabel() {
		JLabel lblConfirmPass = new JLabel("Confirm Password:");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblConfirmPass, 290, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblConfirmPass, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblConfirmPass, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		containerPanel.add(lblConfirmPass);
	}

	private void initPasswordTextField() {
		tfPassword = new JPasswordField();
		sl_containerPanel.putConstraint(SpringLayout.NORTH, tfPassword, 242, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, tfPassword, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, tfPassword, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		containerPanel.add(tfPassword);
		tfPassword.setColumns(10);
	}

	private void initPasswordCheck() {
		lblPassCheck = new JLabel("");
		sl_containerPanel.putConstraint(SpringLayout.NORTH, lblPassCheck, 247, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, lblPassCheck, (int) (screenSize.width / 2.04),
				SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.SOUTH, lblPassCheck, 263, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, lblPassCheck, (int) (screenSize.width / 1.9),
				SpringLayout.WEST,
				containerPanel);
		lblPassCheck.setForeground(new Color(255, 25, 9));
		lblPassCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		containerPanel.add(lblPassCheck);
	}

	private void initEmailTextField() {
		tfEmail = new JTextField();
		sl_containerPanel.putConstraint(SpringLayout.NORTH, tfEmail, 176, SpringLayout.NORTH, containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.WEST, tfEmail, (screenSize.width / 4), SpringLayout.WEST,
				containerPanel);
		sl_containerPanel.putConstraint(SpringLayout.EAST, tfEmail, (screenSize.width / 2), SpringLayout.WEST,
				containerPanel);
		containerPanel.add(tfEmail);
		tfEmail.setColumns(10);
	}

	// Reads individual fields and displays error icon if they don't have input
	private void CheckFields(JTextField input, JLabel label) {
		if (input.getText().equals("")) {
			label.setText("*");
		} else {
			label.setText("");
		}
	}

	// Reads that all fields have some input and sends confirmation message,
	// otherwise gives error
	private void ValidateFields() {
		if (!tfUsername.getText().equals("") && !tfEmail.getText().equals("")
				&& !tfPassword.getPassword().equals("") && !tfConfirmPass.getPassword().equals("")
				&& cbTerms.isSelected()) {
			lblUserCheck.setText("");
			lblEmailCheck.setText("");
			lblPassCheck.setText("");
			lblConfCheck.setText("");
			lblConfirmation.setText("Confirmation Sent!");
		} else {
			lblConfirmation.setText("Please enter all valid credentials!");
		}
	}

	@Override
	public JPanel getView() {
		return this.containerPanel;
	}

	// Implement for future release
	@Override
	public JPanel getParentContainer() {
		return null;
	}

	// Implement for future release
	@Override
	public void setParentContainer(JPanel parent) {
	}
}