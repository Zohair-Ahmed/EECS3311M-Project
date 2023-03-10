package com.eecs3311.integrationtests;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.model.User.User;
import com.eecs3311.persistence.Book.IBook;
import com.eecs3311.persistence.Database;
import com.eecs3311.persistence.Review.IReview;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class SamePageIntegrationTest {
    IBook book = Database.getBookInstance();
    ArrayList<IBookModel> results = new ArrayList<>();
    ArrayList<IBookModel> bookModels = book.getLatestReleases();
    @Test
    public void getSearchResults() {
        String search = "Ha";
        for(IBookModel book : bookModels){
            if (book.getTitle().toLowerCase().contains(search.toLowerCase())){
                results.add(book);
            }
        }
        assertEquals(results.get(0).getTitle(),"Safe Haven");
        assertEquals(results.get(1).getTitle(), "In het hart");
        assertEquals(results.get(2).getTitle(),"Winter Chalet");
        //search not found
        search = "hfkjahdsf";
        ArrayList<IBookModel> emptyResults = new ArrayList<>();
        for(IBookModel book : bookModels){
            if (book.getTitle().toLowerCase().contains(search.toLowerCase())){
                emptyResults.add(book);
            }
        }
        assertTrue(emptyResults.isEmpty());
        assertEquals(bookModels.get(0).getPresenter().getModel().getTitle(), bookModels.get(0).getTitle());
    }

    //user adds reviews to book, compare
    @Test
    public void getUserReviews(){
        User.getInstance().setUsername("test1");
        IReview addedRev = Database.getReviewInstance();
        ArrayList<IReviewModel> previous = addedRev.getReviewData("9780552159722");
        addedRev.submitReview("A new reviews added to the db","4","9780552159722");
        ArrayList<IReviewModel> results = addedRev.getReviewData("9780552159722");
        assertEquals(results.size(),previous.size());
        assertEquals(previous.get(0).getPresenter().getModel().getReview(), previous.get(0).getReview());
    }

    //user adds book to favorites, compare
}