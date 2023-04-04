package com.eecs3311.persistence.Wishlist;

import com.eecs3311.model.User.User;
import com.eecs3311.model.Wishlist.IWishlistModel;
import com.eecs3311.model.Wishlist.WishlistModel;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.util.log.console.ConsoleLogs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class WishlistDB extends AbstractDatabase implements IWishlist {
    public WishlistDB() {
        super();
    }

    @Override
    public void submitBook(String username, String bookTitle, String author, String additionalNotes) {
        String query = "INSERT INTO Wishlist (Username, BookTitle, Author, AdditionalNotes) VALUES " +
                "('" + username + "', '" + bookTitle + "', '" + author + "', '" + additionalNotes + "')";
        try {
            Statement temp = getConnection().createStatement();
            temp.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<IWishlistModel> getBooksSubmitted() {
        String query = "SELECT BookTitle, Author, AdditionalNotes FROM Wishlist WHERE Username = '" + User.getInstance().getUsername() + "'";

        ArrayList<IWishlistModel> wishlist = new ArrayList<>();

        try {
            Statement temp = getConnection().createStatement();
            ResultSet rs = temp.executeQuery(query);

            while (rs.next()) {
                String title = rs.getString("BookTitle");
                String author = rs.getString("Author");
                String additionalNotes = rs.getString("AdditionalNotes");

                IWishlistModel thisWishlistBook = new WishlistModel();
                thisWishlistBook.setBookTitle(title);
                thisWishlistBook.setAuthor(author);
                thisWishlistBook.setAdditionalNotes(additionalNotes);

                wishlist.add(thisWishlistBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(ConsoleLogs.DATABASE(wishlist.get(0).toString()));

        return wishlist;
    }
}
