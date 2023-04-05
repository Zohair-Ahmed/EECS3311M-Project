package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.persistence.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FavBooksDB extends AbstractDatabase implements IFavBooks {

    private ArrayList<IBookModel> favBooks;
    private String title;
    private String description;
    private String ISBN;
    private String author;
    private String genre;
    private String img;

    /**
     * Provide access and perform operations on Favorite Books DB
     */
    public FavBooksDB() {
        super();
    }

    @Override
    public void addBook(IBookModel book) {
        try {
            Statement temp = getConnection().createStatement();
            temp.executeUpdate("insert into Favorites (BookID, Username, FavID) values ('" + book.getISBN() + "', '" + UserModel.getInstance().getUsername() + "', " + UserModel.getInstance().getUserID() + ")");
        }

        catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    public ArrayList<IBookModel> getFavBooks(){
        getDBdata();
        return favBooks;
    }

    @Override
    public ArrayList<String> getUsersFavBooks(String username) {
        ArrayList<String> favourites = new ArrayList<>();
        try {
            if (getConnection() != null) {
                String query = "SELECT Book.Title " +
                        "FROM Favorites " +
                        "JOIN Book ON Favorites.BookID = Book.ISBN13 " +
                        "WHERE Favorites.Username = '" + username + "'";
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    title = rs.getString("Title");
                    favourites.add(title);
                }
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favourites;
    }

    public void getDBdata() {
        favBooks = new ArrayList<>();
        try {
            if (getConnection() != null) {
                Set<String> bookISBN = new HashSet<>();
                String query = "SELECT Book.* " + "FROM Favorites " + "INNER JOIN Book ON Favorites.BookID = Book.ISBN13 " + "WHERE Favorites.FavID = " + UserModel.getInstance().getUserID();
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    ISBN = rs.getString("ISBN13");
                    bookISBN.add(ISBN);
                }
                addToList(bookISBN);
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addToList(Set<String> info) {
        ArrayList<IBookModel> allBooks = Database.getBookInstance().getLatestReleases();
        allBooks.parallelStream().forEach(book -> {
            if (info.contains(book.getISBN())) {
                book.setFavorite(true);
                this.favBooks.add(book);
            }
        });
    }

    public void removeFromFavorites(IBookModel book) {
        try {
            if (getConnection() != null) {
                book.setFavorite(false);
                String sql = "DELETE FROM Favorites WHERE BookID='" + book.getISBN() + "' AND FavID=" + User.getInstance().getUserID() + "";
                Statement st = getConnection().createStatement();
                st.executeUpdate(sql);
                st.close();
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
