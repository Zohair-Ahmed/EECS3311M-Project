package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Reviews;
import com.eecs3311.model.User;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.persistence.Database;

import java.sql.*;
import java.util.ArrayList;

public class FavBooksDB extends AbstractDatabase implements IFavBooks {

    private ArrayList<IBookModel> favBooks;
    private String title;
    private String description;
    private ArrayList<Reviews> reviews;
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

            temp.executeUpdate("insert into Favorites (BookID, Username, FavID) values ('" + book.getISBN() + "', '" + User.getInstance().getUsername() + "', " + User.getInstance().getUserID() + ")");
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the bookList with all latest books in DB
     */
    public ArrayList<IBookModel> getFavBooks(){
        return favBooks;
    }

    public void getDBdata() {
        favBooks = new ArrayList<IBookModel>();
        try {
            if (getConnection() != null) {
//                ArrayList<IBookModel> info = new ArrayList<>();
                ArrayList<String> bookISBN = new ArrayList<String>();
                System.out.println("Connection is successful");
                String query = "SELECT Book.* " +
                        "FROM Favorites " +
                        "INNER JOIN Book ON Favorites.BookID = Book.ISBN13 " +
                        "WHERE Favorites.FavID = " + User.getInstance().getUserID();
                Statement st = getConnection().createStatement();
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);
                // iterate through the java resultset
//                while (rs.next()) {
//                    title = rs.getString("Title");
//                    author = rs.getString("Author");
//                    description = rs.getString("Description");
//                    ISBN = rs.getString("ISBN13");
//                    genre = rs.getString("Genre");
//                    img = rs.getString("Img");
//                    info.add(new BookModel(title, author, description, null, ISBN, genre, img));
//                }
//                addToList(info);

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

//    public void addToList(ArrayList<IBookModel> info) {
//        for (IBookModel ibm : info) {
//            // Set Model <-> Presenter <-> View connection to model
//            IBookPresenter bp = new BookPresenter();
//            IBookView bv = new BookView();
//            bp.setModel(ibm);
//            ibm.setPresenter(bp);
//            bp.setView(bv);
//            bv.setPresenter(bp);
//            this.favBooks.add(ibm);
//        }
//    }

    public void addToList(ArrayList<String> info) {
        ArrayList<IBookModel> allBooks = Database.getBookInstance().getLatestReleases();

        for (String isbn : info) {
            for (IBookModel book : allBooks) {
                if (book.getISBN().equals(isbn)) {
                    this.favBooks.add(book);
                }
            }
        }
    }

    public void removeFromFavorites(IBookModel book) {
        try {
            if (getConnection() != null) {
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
