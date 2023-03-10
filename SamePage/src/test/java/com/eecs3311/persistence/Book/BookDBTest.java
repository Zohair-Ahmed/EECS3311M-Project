package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.persistence.Database;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookDBTest {
    private static final ArrayList<IBookModel> book = Database.getBookInstance().getLatestReleases();
    private final ArrayList<IBookModel> bookStub = BookStub.getInstance().getLatestReleases();

    @Test
    void dataExists() {
        assertFalse(book.isEmpty());
    }

    @Test
    void getLatestReleases() {
        assertFalse(book.isEmpty());
        assertEquals(book.get(10).getTitle(), bookStub.get(10).getTitle());
    }

}