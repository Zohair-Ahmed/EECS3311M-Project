package com.eecs3311.view.Book;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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
        JLabel titleLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getTitle());
        JLabel authorLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getAuthor());
        JLabel genreLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getGenre());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(blackline);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 100;
        c.ipady = 25;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(titleLbl, c);
        c.gridy++;
        mainPanel.add(genreLbl, c);
        c.gridy++;
        mainPanel.add(authorLbl, c);
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
        bookFrame.setSize(1000, 400);
        bookFrame.setVisible(true);
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