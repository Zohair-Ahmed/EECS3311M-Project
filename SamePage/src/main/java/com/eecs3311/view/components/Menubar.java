package com.eecs3311.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

// Create the GUI MenubarFrame
public class Menubar extends JMenuBar {

	Color bgColor = new Color(0, 128, 255); // Set the background colour of the menu bar

	public void setColor(Color color) {
		bgColor = color;
	}

	@Override
	protected void paintComponent(Graphics g) { // Utilize awt to fill the JMenubar component with the appropriate colour
		super.paintComponent(g);
		;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(bgColor);
		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

}