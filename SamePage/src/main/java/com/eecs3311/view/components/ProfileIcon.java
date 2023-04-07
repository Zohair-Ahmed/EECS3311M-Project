package com.eecs3311.view.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class ProfileIcon extends JPanel {

    private String initials;

    public ProfileIcon(String initials) {
        this.initials = initials.toUpperCase();
        setPreferredSize(new Dimension(120, 120));
//        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random r = new Random();
        float hue = r.nextFloat();
        float saturation = r.nextFloat() * 0.5f + 0.5f;
        float brightness = r.nextFloat() * 0.5f + 0.5f;
        Color randColor = Color.getHSBColor(hue, saturation, brightness);
        g.setColor(randColor);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        int textWidth = g.getFontMetrics().stringWidth(initials);
        int textHeight = g.getFontMetrics().getHeight();
        g.drawString(initials, (getWidth()-textWidth)/2, (getHeight()+textHeight)/2);
    }
}
