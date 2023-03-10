package com.eecs3311.integrationtests;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.persistence.Book.IBook;
import com.eecs3311.persistence.Database;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BookIntegrationTest {
    IBook book = Database.getBookInstance();
    @Test
    public void getSearchResults() {
        ArrayList<IBookModel> bookModels = book.getLatestReleases();
        ArrayList<IBookModel> results = new ArrayList<>();
        String search = "Ha";
        for(IBookModel book : bookModels){
            if (book.getTitle().toLowerCase().contains(search.toLowerCase())){
                results.add(book);
            }
        }
        assertEquals(results.get(0).getTitle(),"Safe Haven");
        assertEquals(results.get(1).getTitle(), "In het hart");
        assertEquals(results.get(2).getTitle(),"Winter Chalet");
    }

    //user adds reviews to book, compare

    //user adds book to favorites, compare
}