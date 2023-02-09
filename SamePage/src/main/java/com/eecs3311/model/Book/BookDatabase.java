package com.eecs3311.model.Book;

import com.eecs3311.model.enums.Genre;

import java.util.ArrayList;

public class BookDatabase {

    private ArrayList<IBookModel> bookModels = new ArrayList<>();
    public ArrayList<IBookModel> getLatestReleases(){
        // dummy data for latest release on Landing Frame page
        // title, author, isbn, genre
        // array of strings
        bookModels.add(new BookModel("Harry Potter 1",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY));
        bookModels.add(new BookModel("Harry Potter 2",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY));
        bookModels.add(new BookModel("Harry Potter 3",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY));
        bookModels.add(new BookModel("Harry Potter 4",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY));
        bookModels.add(new BookModel("Harry Potter 5",
                "J.K Rowling",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage",
                null,
                1408855895,
                Genre.FANTASY));

        return bookModels;
    }
}
