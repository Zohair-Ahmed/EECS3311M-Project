package com.eecs3311.UnitTesting.model.Review;

import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.model.User.UserStub;
import com.eecs3311.persistence.Review.ReviewStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IReviewModelTest {

    ArrayList<IReviewModel> reviewFromStubUsers = new ArrayList<>();
    ArrayList<IReviewModel> reviewFromReviewStub = ReviewStub.getInstance().getAllReviews();

    @BeforeEach
    void setUpData() {
        System.out.println("Setting up data ...");
        for (UserStub u : UserStub.getInstance().userList()) {
            for (IReviewModel irm : u.getUserReviews()) {
                reviewFromStubUsers.add(irm);
            }
        }
        assertEquals(reviewFromStubUsers.size(), reviewFromReviewStub.size(), "Missing reviews");
    }

    @Test
    void getUsername() {
        for (IReviewModel irm : reviewFromReviewStub) {
            for (IReviewModel userReview : reviewFromStubUsers) {
                if (userReview.getISBN().equals(irm.getISBN()) && userReview.getUsername().equals(irm.getUsername())){
                    assertEquals(irm, userReview, "Usernames do not match");
                }
            }
        }
    }

    @Test
    void getRating() {
        for (IReviewModel irm : reviewFromReviewStub) {
            for (IReviewModel userReview : reviewFromStubUsers) {
                if (userReview.getISBN().equals(irm.getISBN()) && userReview.getUsername().equals(irm.getUsername()))
                    assertEquals(userReview.getRating(), irm.getRating(), "Ratings do not match");
            }
        }

    }

    @Test
    void getReview() {
        for (IReviewModel irm : reviewFromStubUsers) {
            assertTrue(reviewFromStubUsers.contains(irm));
        }
    }

    @Test
    void getDate() {
        for (IReviewModel irm : reviewFromReviewStub) {
            for (IReviewModel userReview : reviewFromStubUsers) {
                if (userReview.getISBN().equals(irm.getISBN()) && userReview.getUsername().equals(irm.getUsername()))
                    assertEquals(userReview.getISBN(), irm.getISBN(), "Ratings do not match");
            }
        }
    }

    @Test void getISBN() {
        for (IReviewModel irm : reviewFromReviewStub) {
            for (IReviewModel userReview : reviewFromStubUsers) {
                if (userReview.getISBN().equals(irm.getISBN()) && userReview.getUsername().equals(irm.getUsername()))
                    assertEquals(userReview.getISBN(), irm.getISBN(), "Ratings do not match");
            }
        }
    }

}