package com.eecs3311;

import com.eecs3311.model.Book.BookModel;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.view.Book.BookView;
import com.eecs3311.view.Book.IBookView;

public class App {

    public static void main(String[] args) {
        new App();
    }

    public App() {

        IBookModel bm = new BookModel();
        IBookPresenter bp = new BookPresenter();
        // p -> m
        bp.setModel(bm);

        // p <- m
        bm.setPresenter(bp);

        IBookView bv = new BookView();

        // p -> v
        bp.setView(bv);

        // p <- v
        bv.setPresenter(bp);

        // Initial view on load:
        bv.getView();

        // Update title
        bm.setTitle("b");
        bv.getView();

        // Update title through view
        bp.updateModelFromView("c");
        bv.getView();

    }

}