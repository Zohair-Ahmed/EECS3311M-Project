package com.eecs3311.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.eecs3311.view.layout.Main;
import com.eecs3311.view.layout.LoginPanel;
import com.eecs3311.view.layout.RegisterPanel;

// Create the GUI MenubarFrame
public class Menubar extends JMenuBar {

	Color bgColor=new Color(0, 128, 255);
	public void setColor(Color color) {
		bgColor=color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(bgColor);
		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

}