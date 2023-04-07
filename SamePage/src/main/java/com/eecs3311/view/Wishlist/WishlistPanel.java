package com.eecs3311.view.Wishlist;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.model.Wishlist.IWishlistModel;
import com.eecs3311.model.Wishlist.WishlistModel;
import com.eecs3311.presenter.Wishlist.IWishlistPreseter;
import com.eecs3311.presenter.Wishlist.WishlistPresenter;
import com.eecs3311.util.log.console.ConsoleLogs;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.RequestedWishlistPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WishlistPanel implements IWishlistPanelView, IPanelView, ActionListener {
    private static final JPanel containerPanel = new JPanel();
    private static JFrame wishlistFrame;

    // labels
    private JLabel lblConfirmation = new JLabel("");

    // text fields
    private JTextField tfBookTitle;
    private JTextField tfAuthor;
    private JTextArea tfAdditionalNotes;

    // buttons
    private final JButton addBookBtn = new JButton("Add book!");

    private static IWishlistPreseter iwp = new WishlistPresenter();
    private static IWishlistModel iwm = new WishlistModel();
    private static RequestedWishlistPanel rwp = new RequestedWishlistPanel();

    // layout
    private static final SpringLayout sl_containerPanel = new SpringLayout();

    // single instance
    private static WishlistPanel instance = null;

    public WishlistPanel() {
        initComponents();
        iwp.setModel(iwm);
        iwm.setPresenter(iwp);
        iwp.setView(this);
        iwp.setView(rwp);
        containerPanel.setLayout(sl_containerPanel);
    }

    /**
     * There can only be one instance of this wishlist panel
     *
     * @return the Wishlist Panel
     */
    public static WishlistPanel getInstance() {
        if (instance == null)
            instance = new WishlistPanel();
        return instance;
    }

    /**
     * Returns true if there is an active instance of this panel
     *
     * @return true if there is an active instance of this panel
     */
    public static boolean isActive() {
        return instance != null;
    }

    /**
     * Method to be used to initialize UI logic
     */
    public void initComponents() {
        initHeader();
        initBookTitleTextField();
        initAuthorTextField();
        initAdditionalNotesTextField();
        initMessageLabel();
        initAddBookButton();
    }

    /**
     * Init header style
     */
    private void initHeader() {
        // text labels
        JLabel lblHeader = new JLabel("Add to Wishlist");
        containerPanel.add(lblHeader);
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeader, 0,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        lblHeader.setHorizontalAlignment(JLabel.CENTER);
        lblHeader.setFont(new Font("Futura", Font.BOLD, 23));
    }

    /**
     * Init book title text field style and functionality
     */
    private void initBookTitleTextField() {
        // book title label
        JLabel lblBookTitle = new JLabel("Title of Book: ");
        sl_containerPanel.putConstraint(SpringLayout.NORTH, lblBookTitle, 75, SpringLayout.NORTH, containerPanel);
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblBookTitle, -50,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        containerPanel.add(lblBookTitle);

        // book title textfield
        tfBookTitle = new JTextField();
        tfBookTitle.setPreferredSize(new Dimension(150, tfBookTitle.getPreferredSize().height));
        sl_containerPanel.putConstraint(SpringLayout.NORTH, tfBookTitle, 70, SpringLayout.NORTH, containerPanel);
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, tfBookTitle, 75,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        containerPanel.add(tfBookTitle);
    }

    /**
     * Init author textfield style and functionality
     */
    private void initAuthorTextField() {
        // author label
        JLabel lblAuthor = new JLabel("Author: ");
        sl_containerPanel.putConstraint(SpringLayout.NORTH, lblAuthor, 105, SpringLayout.NORTH, containerPanel);
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblAuthor, -55,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        containerPanel.add(lblAuthor);

        // author textfield
        tfAuthor = new JTextField();
        tfAuthor.setPreferredSize(new Dimension(150, tfAuthor.getPreferredSize().height));
        sl_containerPanel.putConstraint(SpringLayout.NORTH, tfAuthor, 100, SpringLayout.NORTH, containerPanel);
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, tfAuthor, 50,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        containerPanel.add(tfAuthor);
    }

    /**
     * Init additional notes text field style and functionality
     */
    private void initAdditionalNotesTextField() {
        // additional notes label
        JLabel lblAdditionalNotes = new JLabel("Additional Notes: ");
        sl_containerPanel.putConstraint(SpringLayout.NORTH, lblAdditionalNotes, 135, SpringLayout.NORTH,
                containerPanel);
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblAdditionalNotes, -100,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        containerPanel.add(lblAdditionalNotes);

        // additional notes textfield
        tfAdditionalNotes = new JTextArea();
        tfAdditionalNotes.setPreferredSize(new Dimension(300, 120));
        sl_containerPanel.putConstraint(SpringLayout.NORTH, tfAdditionalNotes, 155, SpringLayout.NORTH,
                containerPanel);
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, tfAdditionalNotes, -10,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        containerPanel.add(tfAdditionalNotes);
    }

    /**
     * Init message status style and functionality
     */
    private void initMessageLabel() {
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblConfirmation, 0,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        sl_containerPanel.putConstraint(SpringLayout.SOUTH, lblConfirmation, -60, SpringLayout.SOUTH, containerPanel);
        containerPanel.add(lblConfirmation);
    }

    /**
     * Init Add book style and functionality
     */
    private void initAddBookButton() {
        sl_containerPanel.putConstraint(SpringLayout.HORIZONTAL_CENTER, addBookBtn, 0,
                SpringLayout.HORIZONTAL_CENTER, containerPanel);
        sl_containerPanel.putConstraint(SpringLayout.SOUTH, addBookBtn, -15, SpringLayout.SOUTH, containerPanel);
        addBookBtn.addActionListener(onAddButtonClicked());
        containerPanel.add(addBookBtn);
    }

    /**
     * Send the user input information to the presenter and update the message status
     *
     * @return ActionListener for Add Book button
     */
    private ActionListener onAddButtonClicked() {
        return e -> {
            lblConfirmation.setText(lblConfirmation.getText());
            lblConfirmation.revalidate();
            iwp.updateModelFromView(
                    UserModel.getInstance().getUsername(),
                    getBookTitle(),
                    getAuthor(),
                    getAdditionalNotes()
            );
        };
    }

    /**
     * Return the book title inputted by user
     *
     * @return the book title inputted by user
     */
    public String getBookTitle() {
        return this.tfBookTitle.getText();
    }

    /**
     * Return the author inputted by the user
     *
     * @return the author inputted by the user
     */
    public String getAuthor() {
        return this.tfAuthor.getText();
    }

    /**
     * Return the additional notes inputted by the user
     *
     * @return the additional notes inputted by the user
     */
    public String getAdditionalNotes() {
        return this.tfAdditionalNotes.getText();
    }

    /**
     * Return the parent container
     * @return
     */
    @Override
    public JPanel getParentContainer() {
        return null;
    }

    /**
     * Return the wishlist frame
     *
     * @return the wishlist frame
     */
    public JFrame getWishlistFrame() {
        return wishlistFrame;
    }

    /**
     * Set the parent containter
      * @param parent - parent view
     */
    @Override
    public void setParentContainer(JPanel parent) {}

    /**
     * Notifies User's of results of their actions
     *
     * @param status the status of registration update
     */
    @Override
    public void updateWishlistStatus(String status) {
        lblConfirmation.setText(status);
    }

    /**
     * Return the IWishlistPresenter
     *
     * @return the IWishlistPresenter
     */
    @Override
    public IWishlistPreseter getPresenter() {
        return iwp;
    }

    /**
     * Set the IWishlistPresenter
     *
     * @param iwp IWishlistPresenter to be set to
     */
    @Override
    public void setPresenter(IWishlistPreseter iwp) {
        WishlistPanel.iwp = iwp;
    }

    /**
     * Returns Wishlist Panel in the mainframe
     *
     * @return Wishlist Panel in the mainframe
     */
    @Override
    public JPanel getView() {
        return containerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    /**
     * Display the main wishlist frame
     */
    public static void displayWishlistFrame() {
        if (instance != null || wishlistFrame != null) {
            instance = new WishlistPanel();
            wishlistFrame = new JFrame("Adding book to your wishlist!");
            wishlistFrame.add(containerPanel);
            wishlistFrame.setSize(500, 400);
            wishlistFrame.setVisible(true);
            wishlistFrame.addWindowListener(determineIfWindowClosed());
        }
    }

    /**
     * Set the main frame Singleton instance to null when window is deactivated
     *
     * @return WindowListener for main JFrame
     */
    private static WindowAdapter determineIfWindowClosed() {
        return new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e){
                System.out.println(ConsoleLogs.ACTION("Closed wishlist frame..."));
                wishlistFrame.dispose();
                wishlistFrame = null;
                instance = null;
            }
        };
    }
}

