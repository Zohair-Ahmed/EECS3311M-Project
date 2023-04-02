package com.eecs3311.IntegrationTesting;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.persistence.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class FavouritesBooksIntegrationTest {

    ArrayList<IBookModel> bookStub = new ArrayList<>();
    ArrayList<IBookModel> favouriteList = new ArrayList<>();

    @BeforeEach
    public void setData(){

        Database.setIsUsingStubDB(false);
        bookStub = Database.getBookInstance().getLatestReleases();
        Database.getRegisterInstance().registerUser("SamePageTester", "SamePageTester@gmail.com", "1");

        Database.getLoginInstance().isLoginValid("SamePageTester@gmail.com", "1");

        Database.getFavBooksInstance().addBook(bookStub.get(0));
        System.out.println(Database.getFavBooksInstance().getFavBooks().size());
    }

    @Test
    public void removeBookFromFavourite() {
        // Remove 1 book from favourites
        IBookModel removedBook = bookStub.get(0);

        Database.getFavBooksInstance().removeFromFavorites(removedBook);
        ArrayList<IBookModel> favs = Database.getFavBooksInstance().getFavBooks();
        assertEquals(favs.size(), 0);

        // Check if book is not in favourites
        for (IBookModel ibm : favs) {
            assertNotEquals(removedBook.getTitle(), ibm.getTitle());
        }

        for (int i=0; i<favs.size(); i++) {
            IBookModel userFav = favs.get(i);
            IBookModel stubFav = bookStub.get(i);
            // Checks for if UI shows the same content
            assertEquals(userFav.getPresenter().getModel().getTitle(), stubFav.getPresenter().getModel().getTitle());
        }
    }

    @Test
    public void addExistingBookToFavourites(){
        // Add a new book to favourites - if exists, do not add
        IBookModel newbook = bookStub.get(0);
        Database.getFavBooksInstance().addBook(newbook);
        ArrayList<IBookModel> favs = Database.getFavBooksInstance().getFavBooks();
        assertTrue(favs.size() == 1);

        for (int i=0; i<favs.size(); i++) {
            IBookModel userFav = favs.get(i);
            IBookModel stubFav = bookStub.get(i);
            // Checks for if UI shows the same content
            assertEquals(userFav.getPresenter().getModel().getTitle(), stubFav.getPresenter().getModel().getTitle());
        }
    }

    @Test
    public void addNewBookToFavourite() {
        favouriteList.add(bookStub.get(1));
        // Add a new book to favourites - if it does not already exists
        IBookModel newBook = bookStub.get(1);
        Database.getFavBooksInstance().addBook(newBook);

        ArrayList<IBookModel> favs = Database.getFavBooksInstance().getFavBooks();
        assertEquals(2, favs.size());

        for (int i=0; i<favs.size(); i++) {
            assertTrue(bookStub.contains(favs.get(i)));
        }


    }

    @AfterEach
    public void after(){
        for (int i=0; i<4; i++){
            Database.getFavBooksInstance().removeFromFavorites(bookStub.get(i));
        }
    }
}
