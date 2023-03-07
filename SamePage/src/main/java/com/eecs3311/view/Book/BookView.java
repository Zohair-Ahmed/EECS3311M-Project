package com.eecs3311.view.Book;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.view.components.ReviewsPanel;

public class BookView implements IBookView {

    private IBookPresenter bookPresenter;
    private JFrame bookFrame;
    private DisplayBookInformation book;
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

    @Override
    public JPanel getAlternateView(){

        return new JPanel();
    }

    /**
     * Returns a GUI component relating to the model. Include updatedViewFromModel
     * function to ensure the view is up-to-date and change return type as needed
     *
     * @return JPanel - Component that has views related to BookModel
     */
    @Override
    public JPanel getView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel titleLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getTitle());
        JLabel authorLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getAuthor());
        JLabel avgReviews = new JLabel(getPresenter().getUpdatedViewFromModel().getAverageReview()+" ☆");
        JButton favouriteBtn = new JButton("Favourite");
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
        titleLbl.setFont(new Font("Futura", Font.BOLD, 12));
        authorLbl.setFont(new Font("Futura", Font.BOLD | Font.ITALIC, 10));
        avgReviews.setFont(new Font("Futura", Font.ITALIC, 14));
        avgReviews.setForeground(new Color(255, 191, 0));
        favouriteBtn.setFont(new Font("Euphemia UCAS", Font.BOLD, 14));
        favouriteBtn.setBackground(new Color(29, 152, 252));
        favouriteBtn.setForeground(new Color(255, 255, 255));
        favouriteBtn.setOpaque(true);
        favouriteBtn.setBorderPainted(false);
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
        mainPanel.addMouseListener(onBookClicked());
        mainPanel.revalidate();
        return mainPanel;
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
    private void displaySelectedBook(){
        try {
            book = DisplayBookInformation.getInstance(getPresenter());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        bookFrame = new JFrame("Book Reviews");
        bookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookFrame.add(book.getView());
        bookFrame.setSize(1150, 550);
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