// package com.eecs3311.view.layout;

// import java.awt.*;

// import javax.swing.*;

// import com.eecs3311.view.Book.LatestBookView;
// import com.eecs3311.view.components.MenubarFrame;

// public class LandingFrame extends JFrame {
//   private JTextField txtSearch;

//   /**
//    * Creates GUI frame
//    */
//   public LandingFrame() {
//     /*************** Menubar ******************/
//     MenubarFrame menubar = new MenubarFrame();
//     setJMenuBar(menubar.getJMenuBar());

//     // Make screen full-screen
//     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//     setSize((int) (0.75 * screenSize.width), (int) (0.75 * screenSize.height));

//     /*************** Landing Page Setup ******************/

//     JPanel mainPanel = new JPanel();
//     mainPanel.setBackground(new Color(254, 255, 255));
//     // LatestBookView bookView = new LatestBookView();
//     mainPanel.setSize(500, 500);
//     getContentPane().add(mainPanel);
//     mainPanel.setLayout(null);

//     JPanel releasePanel = new JPanel();
//     releasePanel.setBounds(96, 257, 906, 331);
//     releasePanel.setLayout(new BorderLayout());
//     // releasePanel.add(bookView.getView());
//     mainPanel.add(releasePanel);

//     JLabel lblLatestReleases = new JLabel("Latest Releases:");
//     lblLatestReleases.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
//     lblLatestReleases.setBounds(96, 217, 219, 28);
//     mainPanel.add(lblLatestReleases);

//     JLabel lblTitle = new JLabel("Same Page Books");
//     lblTitle.setFont(new Font("Segoe print", Font.BOLD, 30));
//     lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
//     lblTitle.setBounds(366, 25, 333, 77);
//     mainPanel.add(lblTitle);

//     txtSearch = new JTextField();
//     txtSearch.setBackground(new Color(181, 183, 183));
//     txtSearch.setText("  Search....");
//     txtSearch.setBounds(209, 134, 644, 35);
//     mainPanel.add(txtSearch);
//     txtSearch.setColumns(10);

//     setTitle("Same Page");
//     setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//     setResizable(false);
//     setVisible(true);
//   }
// }

package com.eecs3311.view.layout;

import java.awt.*;

import javax.swing.*;

import com.eecs3311.view.Book.LatestBookView;
import com.eecs3311.view.components.MenubarFrame;

public class LandingFrame extends JFrame {

  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  private final int HEIGHT = (int) (0.75 * screenSize.height);
  private final int WIDTH = (int) (0.75 * screenSize.width);

  public LandingFrame() {

    /*************** Menubar ******************/
    MenubarFrame menubar = new MenubarFrame();
    setJMenuBar(menubar.getJMenuBar());

    LandingTest landingTest = new LandingTest();

    add(landingTest.getView());
    setSize(WIDTH, HEIGHT);
    setTitle("Same Page");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

}
