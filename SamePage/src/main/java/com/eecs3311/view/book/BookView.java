package com.eecs3311.view.Book;

import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.presenter.Book.IBookPresenter;

import javax.swing.*;
import java.util.ArrayList;

public class BookView extends JFrame implements IBookView {
    private JTextArea textField;
    private IBookPresenter bookPresenter = new BookPresenter();

    public BookView() {
        textField = new JTextArea();
        textField.setEditable(false);

        String bookResult = "Latest Releases:\nTitle                  Author          ISBN               Genre\n";
        for (int i = 0; i < bookPresenter.getUpdatedViewFromModel().size(); ++i)
            bookResult += bookPresenter.getUpdatedViewFromModel().get(i)+"\n\n\n";
        textField.setText(bookResult);
        JScrollPane scroll = new JScrollPane(textField);

        scroll.setBounds(20, 150, 455, 249);
        getContentPane().add(scroll);
        setLocationRelativeTo ( null );

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
    public ArrayList<String> getView() {
        // ToDo: Include updatedViewFromModel function to ensure the view is up-to-date
        // ToDo: Change return type as needed
        return bookPresenter.getUpdatedViewFromModel();
    }
}
