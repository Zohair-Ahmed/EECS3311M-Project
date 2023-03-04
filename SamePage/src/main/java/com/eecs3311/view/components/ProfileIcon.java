package com.eecs3311.view.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ProfileIcon extends JPanel {

    private String initials;

    public ProfileIcon(String initials) {
        this.initials = initials.toUpperCase();
        setPreferredSize(new Dimension(200, 200));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        int textWidth = g.getFontMetrics().stringWidth(initials);
        int textHeight = g.getFontMetrics().getHeight();
        g.drawString(initials, (getWidth()-textWidth)/2, (getHeight()+textHeight)/2);
    }
}
