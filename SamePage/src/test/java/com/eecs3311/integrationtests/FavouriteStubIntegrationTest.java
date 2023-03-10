package com.eecs3311.integrationtests;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.User.UserStub;
import com.eecs3311.persistence.Book.BookStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class FavouriteStubIntegrationTest {

    ArrayList<IBookModel> bookStub = BookStub.getInstance().getLatestReleases();
    UserStub user1 = UserStub.getInstance().userList().get(0);
    ArrayList<IBookModel> favouriteList = new ArrayList<>();

    @BeforeEach
    public void setData(){
        for (int i=0; i<4; i++) {
            user1.addBookToFavourite(bookStub.get(i));
            favouriteList.add(bookStub.get(i));
        }
    }

    @Test
    public void removeBookFromFavourite() {
        // Remove 1 book from favourites
        IBookModel removedBook = bookStub.get(1);
        user1.getFavourites().removeIf(ibm -> ibm.getTitle().equals(removedBook.getTitle()));
        favouriteList.removeIf(ibm -> ibm.getTitle().equals(removedBook.getTitle()));
        assertEquals(user1.getFavourites().size(), 3);

        // Check if book is not in favourites
        for (IBookModel ibm : user1.getFavourites()) {
            assertNotEquals(removedBook.getTitle(), ibm.getTitle());
        }

        for (int i=0; i<user1.getFavourites().size(); i++) {
            IBookModel userFav = user1.getFavourites().get(i);
            IBookModel stubFav = favouriteList.get(i);
            // Checks for if UI shows the same content
            assertEquals(userFav.getPresenter().getModel().getTitle(), stubFav.getPresenter().getModel().getTitle());
        }
    }

    @Test
    public void addExistingBookToFavourites(){
        // Add a new book to favourites - if exists, do not add
        IBookModel newbook = bookStub.get(0);
        user1.addBookToFavourite(newbook);
        assertTrue(user1.getFavourites().size() == 4);

        for (int i=0; i<user1.getFavourites().size(); i++) {
            IBookModel userFav = user1.getFavourites().get(i);
            IBookModel stubFav = favouriteList.get(i);
            // Checks for if UI shows the same content
            assertEquals(userFav.getPresenter().getModel().getTitle(), stubFav.getPresenter().getModel().getTitle());
        }
    }

    @Test
    public void addNewBookToFavourite() {
        favouriteList.add(bookStub.get(5));
        // Add a new book to favourites - if it does not already exists
        IBookModel newbook = bookStub.get(5);
        user1.addBookToFavourite(newbook);
        assertTrue(user1.getFavourites().size() == 5);

        for (int i=0; i<user1.getFavourites().size(); i++) {
            IBookModel userFav = user1.getFavourites().get(i);
            IBookModel stubFav = favouriteList.get(i);
            // Checks for if UI shows the same content
            assertEquals(userFav.getPresenter().getModel().getTitle(), stubFav.getPresenter().getModel().getTitle());
        }
    }

    @AfterEach
    public void after(){
        user1.setFavourites(new ArrayList<>());
    }
}
