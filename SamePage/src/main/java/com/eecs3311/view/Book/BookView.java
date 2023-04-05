package com.eecs3311.view.Book;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.model.enums.State;
import com.eecs3311.presenter.Book.IBookPresenter;

public class BookView implements IBookView {

    private IBookPresenter bookPresenter;
    private JFrame bookFrame;
    private DisplayBookInformation book;

    private JPanel mainPanel;
    private JButton favouriteBtn;

    public BookView() {
    }

    @Override
    public IBookPresenter getPresenter() {
        return bookPresenter;
    }

    @Override
    public void setPresenter(IBookPresenter bp) {
        this.bookPresenter = bp;
    }

    /**
     * Initilaizes the favourites button for the book view; changes button text depending on
     * whether the book is in favourites list of logged-in user
     * @param mainPanel
     * @param favouriteBtn
     */
    public void initFavouriteBtn(JPanel mainPanel, JButton favouriteBtn) {
        favouriteBtn.addActionListener(e -> {
            if (UserModel.getInstance().getLoginState() == State.GUEST) {
                JOptionPane.showMessageDialog(mainPanel, "Only members signed into SamePage can add books to favourites");
            } else {
                if (getPresenter().checkModelFavBooks()) {
                    getPresenter().removeFavBook();
                    favouriteBtn.setText("Favourite");
                    favouriteBtn.setBackground(initFavouriteBtnColour(favouriteBtn));
                    UserModel.getInstance().getMainInit().addProfilePanel();
                    if (UserModel.getInstance().getMainInit().checkCurrentCard().equals("Profile")) {
                        UserModel.getInstance().getMainInit().getCard().show(UserModel.getInstance().getMainInit().getContainer(), "Profile");
                    }
                } else {
                    getPresenter().updateModelFavBooks();
                    favouriteBtn.setText("Remove");
                    favouriteBtn.setBackground(initFavouriteBtnColour(favouriteBtn));
                    UserModel.getInstance().getMainInit().addProfilePanel();
                }
            }
            User.getInstance().getMainInit().getLandingPanel().updateLanding(this);
        });
    }

    public void changeFavouriteBtnText(boolean addRemoveButton){
        favouriteBtn.setText(addRemoveButton ? "Remove" : "Favourite");
        favouriteBtn.repaint();
        favouriteBtn.revalidate();
    }

    // Initializing whether the favourite button is blue or red
    public Color initFavouriteBtnColour(JButton favouriteBtn) {
        if (favouriteBtn.getText().equals("Favourite"))
            return new Color(29, 152, 252);
        else return new Color (255, 26, 18);
    }

    @Override
    public JPanel getView() {
        if (mainPanel != null)
            return mainPanel;
        else {
            JPanel mainPanel = new JPanel();
            mainPanel.setName(bookPresenter.getModel().getTitle());
            mainPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            JLabel titleLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getTitle());
            JLabel authorLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getAuthor());
            JLabel avgReviews = new JLabel(String.format("%.1f",getPresenter().getUpdatedViewFromModel().getAverageReview())+" ☆");
            favouriteBtn = new JButton(getPresenter().checkModelFavBooks() == true ? "Remove" : "Favourite");

            initFavouriteBtn(mainPanel, favouriteBtn);
            favouriteBtn.setBackground(initFavouriteBtnColour(favouriteBtn));

            initBookImage(mainPanel, c);
            initFonts(titleLbl, authorLbl, avgReviews, favouriteBtn);
            initLayout(mainPanel, titleLbl, authorLbl, avgReviews, favouriteBtn, c);

            mainPanel.addMouseListener(onBookClicked());
            mainPanel.revalidate();
            return mainPanel;
        }
    }

    /**
     * Initilaizes the layout of the components of the book view
     * @param mainPanel
     * @param titleLbl
     * @param authorLbl
     * @param avgReviews
     * @param favouriteBtn
     * @param c
     */
    private void initLayout(JPanel mainPanel, JLabel titleLbl, JLabel authorLbl, JLabel avgReviews, JButton favouriteBtn, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(titleLbl, c);
        c.gridy = 2;
        mainPanel.add(authorLbl, c);
        c.gridy = 3;
        mainPanel.add(avgReviews, c);
        c.gridy = 4;
        c.insets = new Insets(5, 0, 50, 15);
        mainPanel.add(favouriteBtn, c);
    }


    /**
     * Initializes the fonts for the labels and buttons
     * @param titleLbl
     * @param authorLbl
     * @param avgReviews
     * @param favouriteBtn
     */
    private void initFonts(JLabel titleLbl, JLabel authorLbl, JLabel avgReviews, JButton favouriteBtn) {
        titleLbl.setFont(new Font("Futura", Font.BOLD, 12));
        authorLbl.setFont(new Font("Futura", Font.BOLD | Font.ITALIC, 10));
        avgReviews.setFont(new Font("Futura", Font.ITALIC, 14));
        avgReviews.setForeground(new Color(255, 191, 0));
        favouriteBtn.setFont(new Font("Euphemia UCAS", Font.BOLD, 14));
        favouriteBtn.setForeground(new Color(255, 255, 255));
        favouriteBtn.setOpaque(true);
        favouriteBtn.setBorderPainted(false);
    }

    /**
     * Initializes the book image for the book view
     * @param mainPanel book view panel
     * @param c layout constraint
     */
    private void initBookImage(JPanel mainPanel, GridBagConstraints c){
        try {
            URL url = new URL(bookPresenter.getUpdatedViewFromModel().getImg());
            BufferedImage img = ImageIO.read(url);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(120, 180, Image.SCALE_SMOOTH));
            JLabel picLabel = new JLabel(imageIcon);
            mainPanel.add(picLabel, c);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * MouseListener for which book the user has selected.
     */
    private MouseListener onBookClicked() {
        return new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                System.out.printf("Clicked %s\n",getPresenter().getUpdatedViewFromModel().getTitle());
                if (bookFrame == null) {
                    // If not, create a new frame and show it
                    displaySelectedBook();
                } else if(book!=null && !book.getTitleB().equals(getPresenter().getUpdatedViewFromModel().getTitle())){
                    //so book frame is already there, now check if the user is clicking a new book result
                    // then dispose the popup and open a new display book view
                    bookFrame.dispose();
                    displaySelectedBook();
                }
                else{
                    // user has clicked the same book popup and thus bring the open book frame to the front
                    bookFrame.setVisible(true);
                    bookFrame.toFront();
                }
            }
        };
    }

    /**
     * Opens a bigger display for the Book View on a mouse click
     */
    private void displaySelectedBook(){
        try {
            book = DisplayBookInformation.getInstance(getPresenter());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        bookFrame = new JFrame("Book Reviews");
        bookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookFrame.add(book.getView());
        bookFrame.setSize(1250, 550);
        bookFrame.setVisible(true);
        bookFrame.setLocationRelativeTo(null);
        addWindowListener();
    }

    private void addWindowListener() {
        WindowListener wl = new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e){
                bookFrame.setVisible(false);
            }
        };
        bookFrame.addWindowListener(wl);
    }
}