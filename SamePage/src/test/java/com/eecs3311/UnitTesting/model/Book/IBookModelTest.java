package com.eecs3311.UnitTesting.model.Book;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.persistence.Book.BookStub;
import com.eecs3311.persistence.Database;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IBookModelTest {
    private static final ArrayList<IBookModel> bookStub = BookStub.getInstance().getLatestReleases();
    ArrayList<IBookModel> book = Database.getBookInstance().getLatestReleases();
    @Test
    void getTitle() {
        for(int i=0; i<book.size(); i++){
            assertEquals(book.get(i).getTitle(),bookStub.get(i).getTitle(),"Error in index "+i);
        }
    }

    @Test
    void getAuthor() {
        for(int i=0; i<book.size(); i++){
            assertEquals(book.get(i).getAuthor(),bookStub.get(i).getAuthor(),"Error in index "+i);
        }
    }

    @Test
    void getISBN() {
        for(int i=0; i<book.size(); i++){
            assertEquals(book.get(i).getISBN(),bookStub.get(i).getISBN(),"Error in index "+i);
        }
    }

    @Test
    void getGenre() {
        for(int i=0; i<book.size(); i++){
            assertEquals(book.get(i).getGenre(),bookStub.get(i).getGenre(),"Error in index "+i);
        }
    }

    @Test
    void getPresenter() {
        for(int i=0; i<book.size(); i++){
            assertEquals(book.get(i).getPresenter().getUpdatedViewFromModel().getTitle(),
                    bookStub.get(i).getTitle(),"Error in index "+i);
        }
    }

    @Test
    void getDescription() {
        for(int i=0; i<book.size(); i++){
            assertEquals(book.get(i).getDescription(),bookStub.get(i).getDescription(),"Error in index "+i);
        }
    }

    @Test
    void getImg() {
        for(int i=0; i<book.size(); i++){
            assertEquals(book.get(i).getImg(),bookStub.get(i).getImg(),"Error in index "+i);
        }
    }

    @Test
    void getAverageReview() {
        for(int i=0; i<book.size(); i++){
            assertEquals(book.get(i).getAverageReview(),bookStub.get(i).getAverageReview(),"Error in index "+i);
        }
    }
}