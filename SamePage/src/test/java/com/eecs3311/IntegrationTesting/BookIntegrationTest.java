package com.eecs3311.IntegrationTesting;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.Book.IBook;
import com.eecs3311.persistence.Database;
import com.eecs3311.persistence.Review.IReview;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BookIntegrationTest {
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
        assertEquals(results.get(0).getTitle(),"Harry Potter");
        assertEquals(results.get(1).getTitle(), "Safe Haven");
        assertEquals(results.get(2).getTitle(),"Winter Chalet");
        assertEquals(results.get(3).getTitle(), "In het hart");
        //search not found
        search = "hfkjahdsf";
        ArrayList<IBookModel> emptyResults = new ArrayList<>();
        for(IBookModel book : bookModels){
            if (book.getTitle().toLowerCase().contains(search.toLowerCase())){
                emptyResults.add(book);
            }
        }
        assertTrue(emptyResults.isEmpty());
        for (IBookModel book : bookModels){
            assertEquals(book.getPresenter().getModel().getTitle(), book.getTitle());
            assertEquals(book.getPresenter().getModel().getISBN(), book.getISBN());
            assertEquals(book.getPresenter().getUpdatedViewFromModel().getPresenter().getModel().getISBN(),book.getISBN());
        }

    }

    //user adds reviews to book, compare
    @Test
    public void getUserReviews(){
        UserModel.getInstance().setUsername("test1");
        IReview addedRev = Database.getReviewInstance();
        addedRev.submitReview("A new reviews added to the db","4","9780552159722");
        ArrayList<IReviewModel> results = addedRev.getReviewData("9780552159722");
        for (IReviewModel reviewModel : results){
            assertEquals(reviewModel.getPresenter().getModel().getReview(), reviewModel.getReview());
            assertEquals(reviewModel.getPresenter().getModel().getISBN(), reviewModel.getISBN());
        }
    }
}