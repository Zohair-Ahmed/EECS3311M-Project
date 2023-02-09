package com.eecs3311.view.Book;

import com.eecs3311.model.Book.BookDatabase;
import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.presenter.Book.IBookPresenter;

import javax.swing.*;

public class LatestBookView extends JFrame implements IBookView {
    private JTextArea textField;
    private IBookPresenter bookPresenter = new BookPresenter();

    private BookDatabase bookDatabase;
    public LatestBookView() {
        textField = new JTextArea();
        textField.setEditable(false);
        bookDatabase = new BookDatabase();
        int size = bookDatabase.getLatestReleases().size();
        String bookResult = "Latest Releases:\nTitle                  Author          ISBN               Genre\n";
        for (int i = 0; i < size; i++)
            bookResult += bookDatabase.getLatestReleases().get(i).toString()+"\n\n\n";
        textField.setText(bookResult);
        JScrollPane scroll = new JScrollPane(textField);

        scroll.setBounds(20, 150, 455, 249);
        getContentPane().add(scroll);
        setLocationRelativeTo ( null );
    }

    //Notes: Use in Landing Frame when refactoring
    public JTextArea getLatest(){
        int size = bookDatabase.getLatestReleases().size();
        String bookResult = "Latest Releases:\nTitle                  Author          ISBN               Genre\n";
        for (int i = 0; i < size; i++)
            bookResult += bookDatabase.getLatestReleases().get(i).toString()+"\n\n\n";
        textField.setText(bookResult);
        return textField;
    }

    public static void main(String [] args){
        new LatestBookView();
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
     * Returns a GUI component relating to the model
     *
     * @return
     */
    @Override
    public String getView() {
        // Notes: Include updatedViewFromModel function to ensure the view is up-to-date
        // Notes: Change return type as needed
        return bookPresenter.getUpdatedViewFromModel();
    }
}
