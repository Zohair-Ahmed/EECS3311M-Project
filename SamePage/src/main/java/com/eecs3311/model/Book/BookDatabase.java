package com.eecs3311.model.Book;

import com.eecs3311.model.enums.Genre;
import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.view.Book.BookView;
import com.eecs3311.view.Book.IBookView;

import java.util.ArrayList;

public class BookDatabase {

        private ArrayList<IBookModel> bookModels = new ArrayList<>();

        public BookDatabase() {
                bookModels = generateLatestReleases();
        }

        public ArrayList<IBookModel> getLatestReleases() {
                return this.bookModels;
        }

        private ArrayList<IBookModel> generateLatestReleases() {
                // dummy data for latest release on Landing Frame page
                // title, author, isbn, genre
                // array of strings
                IBookModel info[] = { new BookModel("Harry Potter 1",
                                "J.K Rowling",
                                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                null,
                                1408855895,
                                Genre.FANTASY),
                                new BookModel("Harry Potter 2",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 3",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 4",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 5",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 6",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 7",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 8",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 9",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 10",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 11",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 12",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY),
                                new BookModel("Harry Potter 13",
                                                "J.K Rowling",
                                                "The first novel in the Harry Potter series and Rowling's debut novel, "
                                                                +
                                                                "it follows Harry Potter, a young wizard who discovers his magical heritage",
                                                null,
                                                1408855895,
                                                Genre.FANTASY) };

                for (IBookModel ibm : info) {
                        IBookPresenter bp = new BookPresenter();
                        IBookView bv = new BookView();
                        bp.setModel(ibm);
                        ibm.setPresenter(bp);
                        bp.setView(bv);
                        bv.setPresenter(bp);
                        bookModels.add(ibm);
                }

                return bookModels;
        }
}
