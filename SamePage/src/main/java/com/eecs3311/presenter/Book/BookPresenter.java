package com.eecs3311.presenter.Book;

import com.eecs3311.model.Book.BookModel;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.enums.Genre;
import com.eecs3311.view.Book.IBookView;

import java.util.ArrayList;

public class BookPresenter implements IBookPresenter {

    // Each Presenter object should have 1 Model and 1 View
    private IBookModel bookModel;
    private IBookModel bookModel2;
    private IBookModel bookModel3;
    private IBookModel bookModel4;
    private IBookView bookView;

    @Override
    public IBookModel getModel() {
        return bookModel;
    }

    @Override
    public void setModel() {
        this.bookModel = new BookModel("Harry Potter 1",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY);
        this.bookModel2 = new BookModel("Harry Potter 2",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY);
        this.bookModel3 = new BookModel("Harry Potter 3",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY);
        this.bookModel4 = new BookModel("Harry Potter 4",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY);

    }

    @Override
    public IBookView getView() {
        return bookView;
    }

    @Override
    public void setView(IBookView bv) {
        this.bookView = bv;
    }

    /**
     * Presenter class gets details of what to change for the model.
     * 
     * @param title - Attribute to be updated/changed - can be multiple
     */
    @Override
    public void updateModelFromView(String title) {
        // There may be one method for every mutable attribute - TBD
        // Default: getModel().<setAttribute(param)> -- depending on what's exactly
        // being changed, it won't be enough
        getModel().setTitle(title);
    }

    /**
     * Sends updated information about the model to the view
     *
     * @return - implement return type as needed
     */
    @Override
    public ArrayList<String> getUpdatedViewFromModel() {
        setModel();
        ArrayList<String> out = new ArrayList<>();
        out.add(this.bookModel.getTitle() +"   "+ this.bookModel.getAuthor() + "   "+this.bookModel.getISBN()+ "   "+this.bookModel.getGenre());
        out.add(this.bookModel2.getTitle() +"   "+ this.bookModel2.getAuthor()+"   "+this.bookModel2.getISBN()+ "   "+this.bookModel.getGenre());
        out.add(this.bookModel3.getTitle() +"   "+ this.bookModel3.getAuthor()+"   "+this.bookModel3.getISBN()+ "   "+this.bookModel.getGenre());
        out.add(this.bookModel4.getTitle() +"   "+ this.bookModel4.getAuthor()+"   "+this.bookModel4.getISBN()+ "   "+this.bookModel.getGenre());
        out.add("Harry Potter 5" +"   "+ this.bookModel2.getAuthor()+"   "+this.bookModel2.getISBN()+ "   "+this.bookModel.getGenre());
        out.add("Harry Potter 6" +"   "+ this.bookModel2.getAuthor()+"   "+this.bookModel2.getISBN()+ "   "+this.bookModel.getGenre());
        out.add("Harry Potter 7" +"   "+ this.bookModel2.getAuthor()+"   "+this.bookModel2.getISBN()+ "   "+this.bookModel.getGenre());
        return out;
        // would we print out these values or return them? Wouldn't they be returned so
        // the View can read them?
        // I mean its fine for now, but just wondering how would the view get these
        // values for the future?

        // To be honest, I think its fine for itr 1 - since we are hardcoding the values
        // for itr 1 anyway, plus we don't have a view you can test on

        // B/c it's presenter, we should return the values -- only have void right now,
        // but will change to return
    }
}
