package com.eecs3311.view.layout;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.eecs3311.view.components.MenubarFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JPasswordField;

public class RegisterFrame extends JFrame {

	private JPanel tfFirstName;
	private JTextField tfUsername;
	private JTextField tfEmail;
	private JPasswordField tfPassword;
	private JPasswordField tfConfirmPass;
	private JCheckBox cbTerms;
	private JLabel lblConfirmation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
		setResizable(false);
	    /*************** Menubar ******************/
	    MenubarFrame menubar = new MenubarFrame();
	    setJMenuBar(menubar.getJMenuBar());
	    
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 486);
		tfFirstName = new JPanel();
		tfFirstName.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(tfFirstName);
		tfFirstName.setLayout(null);
		
		// Register label
		JLabel lblRegisterHere = new JLabel("Register Here");
		lblRegisterHere.setFont(new Font("Segoe print", Font.BOLD, 25));
		lblRegisterHere.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegisterHere.setBounds(140, 6, 203, 56);
		tfFirstName.add(lblRegisterHere);
		
		// Username label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(62, 89, 80, 16);
		tfFirstName.add(lblUsername);
		
		// * username label
		JLabel lblUserCheck = new JLabel("");
		lblUserCheck.setForeground(new Color(255, 26, 10));
		lblUserCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblUserCheck.setBounds(391, 115, 33, 16);
		tfFirstName.add(lblUserCheck);
		
		// Username text field
		tfUsername = new JTextField();
		tfUsername.setBounds(62, 110, 331, 26);
		tfFirstName.add(tfUsername);
		tfUsername.setColumns(10);
		
		// Email label
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(62, 159, 80, 16);
		tfFirstName.add(lblEmail);
		
		// * email label
		JLabel lblEmailCheck = new JLabel("");
		lblEmailCheck.setForeground(new Color(255, 26, 10));
		lblEmailCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblEmailCheck.setBounds(391, 181, 33, 16);
		tfFirstName.add(lblEmailCheck);
		
		// Email text field
		tfEmail = new JTextField();
		tfEmail.setBounds(62, 176, 331, 26);
		tfFirstName.add(tfEmail);
		tfEmail.setColumns(10);
		
		// Password label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(62, 225, 80, 16);
		tfFirstName.add(lblPassword);
		
		// * password label
		JLabel lblPassCheck = new JLabel("");
		lblPassCheck.setForeground(new Color(255, 25, 9));
		lblPassCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPassCheck.setBounds(391, 247, 33, 16);
		tfFirstName.add(lblPassCheck);
		
		// Password text field
		tfPassword = new JPasswordField();
		tfPassword.setBounds(62, 242, 331, 26);
		tfFirstName.add(tfPassword);
		tfPassword.setColumns(10);
		
		// Confirm Password label
		JLabel lblConfirmPass = new JLabel("Confirm Password:");
		lblConfirmPass.setBounds(62, 290, 127, 16);
		tfFirstName.add(lblConfirmPass);
		
		// * confirm password label
		JLabel lblConfCheck = new JLabel("");
		lblConfCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfCheck.setForeground(new Color(255, 25, 9));
		lblConfCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblConfCheck.setBounds(391, 313, 33, 16);
		tfFirstName.add(lblConfCheck);
		
		// Confirm password text field
		tfConfirmPass = new JPasswordField();
		tfConfirmPass.setBounds(62, 308, 331, 26);
		tfFirstName.add(tfConfirmPass);
		tfConfirmPass.setColumns(10);
		
		// T&C Checkbox
		cbTerms = new JCheckBox("I have read the terms and conditions");
		cbTerms.setBounds(83, 346, 273, 23);
		tfFirstName.add(cbTerms);
		
		// * t&c label
		JLabel lblTermsCheck = new JLabel("");
		lblTermsCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblTermsCheck.setForeground(new Color(255, 25, 9));
		lblTermsCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTermsCheck.setBounds(348, 350, 33, 16);
		tfFirstName.add(lblTermsCheck);
		
		// Message label
		lblConfirmation = new JLabel("");
		lblConfirmation.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 11));
		lblConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmation.setBounds(62, 414, 324, 16);
		tfFirstName.add(lblConfirmation);
		
		JButton btnRegister = new JButton("Register");
		// Logic for checking all required fields are valid upon clicking register
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Username field check
				if (tfUsername.getText().equals("")) {
					lblUserCheck.setText("*");
				} else {
					lblUserCheck.setText("");
				}
				
				// Email field check
				if (tfEmail.getText().equals("")) {
					lblEmailCheck.setText("*");
				} else {
					lblEmailCheck.setText("");
				}
				
				// Password field check
				if (tfPassword.getText().equals("")) {
					lblPassCheck.setText("*");
				} else {
					lblPassCheck.setText("");
				}
				
				// Confirm Password field check
				if (tfConfirmPass.getText().equals("")) {
					lblConfCheck.setText("*");
				} else {
					lblConfCheck.setText("");
				}
				
				// Accepted T&C's check
				if (!cbTerms.isSelected()) {
					lblTermsCheck.setText("*");
				} else {
					lblTermsCheck.setText("");
				}
				
				//Valid credentials method
				if (!tfUsername.getText().equals("") && 
					!tfEmail.getText().equals("") && 
					!tfPassword.getText().equals("") && 
					!tfConfirmPass.getText().equals("") && 
					cbTerms.isSelected()) 
				{
					lblUserCheck.setText("");
					lblEmailCheck.setText("");
					lblPassCheck.setText("");
					lblConfCheck.setText("");
					lblConfirmation.setText("Confirmation Sent!");
				} else {
					lblConfirmation.setText("Please enter all valid credentials!");
				}
			}
		});
		btnRegister.setBounds(166, 373, 117, 29);
		tfFirstName.add(btnRegister);
		
	}
}
