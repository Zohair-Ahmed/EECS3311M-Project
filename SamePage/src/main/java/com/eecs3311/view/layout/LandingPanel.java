package com.eecs3311.view.layout;

import javax.swing.*;
import java.awt.*;

import com.eecs3311.view.components.ResultsPanel;
import com.eecs3311.view.components.ResultsMediator;
import com.eecs3311.view.components.SearchBar;

public class LandingPanel {

    ResultsMediator mediator = new ResultsMediator();
    JPanel root = new JPanel();

    public LandingPanel() {
        root.setLayout(new GridBagLayout());
    	
        JPanel herobanner = new JPanel();
        herobanner.setLayout(new GridBagLayout());
      
        JLabel title = new JLabel("Same Page Books");
        title.setForeground(new Color(12, 51, 127));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Futura", Font.BOLD, 25));
        herobanner.add(title);
    	
	    SearchBar sbf = new SearchBar(mediator);
	    ResultsPanel lbv = new ResultsPanel(mediator);
	
	    mediator.setLbv(lbv);
	    mediator.setSbf(sbf);
	    
	    GridBagConstraints c = new GridBagConstraints();
	    
        c.ipadx = 0;
        c.ipady = 75;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        root.add(herobanner, c);
	    
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        
        root.add(sbf.getView(), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 0;
        c.ipady = 150;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        root.add(lbv.getView(), c);
    }

    public JPanel getView() {
        return this.root;
    }
}
