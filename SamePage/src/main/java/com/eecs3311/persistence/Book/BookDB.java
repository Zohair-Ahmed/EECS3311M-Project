package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.BookModel;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.view.Book.BookView;
import com.eecs3311.view.Book.IBookView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * BookDB - Book Database class to get all the available latest book releases
 * for users in the SamePage app.
 * Makes sql connection and uses samepageschema to store/retrieve book data.
 */
public class BookDB extends AbstractDatabase implements IBook {
    private String title;
    private String description;
    private String ISBN;
    private String author;
    private String genre;
    private String img;
    private int bookIndex;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final InputStream bookMocksFile = this.getClass().getClassLoader().getResourceAsStream("data/bookMocks.json");
    private final ArrayList<IBookModel> bookList = new ArrayList<>();

    /**
     * BookDB constructor, makes initial connection to database
     * with JDBC driver and database URL.
     */
    public BookDB() {
        super();
        try {
            if (!dataExists()) {
                prepopulateData();
            }
            getDBdata();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if book data has already been pre-populated and exists in database
     */
    public boolean dataExists() throws SQLException {
        // select from book where the ISBN matches the first entry
        String sql = "SELECT * FROM Book WHERE ISBN13 = 9789000307975";
        PreparedStatement stmt = getConnection().prepareStatement(sql);

        // if the first entry ISBN exists, prepopulateData has already
        // been called and data exists. Else return false.
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            System.out.println("Data exists in table.");
            return true;
        } else {
            System.out.println("Data does not exist in table.");
            return false;
        }
    }

    /**
     * Parse JSON to prepopulate data in book database.
     */
    public void prepopulateData(){
        // make sure entry is not null
        try {
            JsonNode jsonNode = objectMapper.readTree(bookMocksFile);
            if (getConnection() != null) {
                String query = " INSERT INTO BOOK (Title, Author, Description, ISBN13, Img, Genre)"
                        + " values (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = getConnection().prepareStatement(query);
                for (JsonNode node : jsonNode) {
                    ISBN = node.get("ISBN").asText();
                    title = node.get("title").asText();
                    author = node.get("author").asText();
                    description = node.get("summary").asText();
                    genre = node.get("genre").asText();
                    img = node.get("image").asText();
                    preparedStmt.setString(1, title);
                    preparedStmt.setString(2, author);
                    preparedStmt.setString(3, description);
                    preparedStmt.setString(4, ISBN);
                    preparedStmt.setString(5, img);
                    preparedStmt.setString(6, genre);
                    preparedStmt.executeUpdate();
                }
            }
            else {
                System.out.println("Failed to connect");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<IBookModel> getLatestReleases(){
        return bookList;
    }

    /**
     * Get the existing book data from database and pass it to Book Model
     */
    public void getDBdata() {
        try {
            if (getConnection() != null) {
                ArrayList<IBookModel> info = new ArrayList<>();
                System.out.println("Connection is successful");
                String query = "SELECT * FROM Book ORDER BY BookID ASC";
                Statement st = getConnection().createStatement();
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);
                // iterate through the java resultset
                while (rs.next()) {
                    title = rs.getString("Title");
                    author = rs.getString("Author");
                    description = rs.getString("Description");
                    ISBN = rs.getString("ISBN13");
                    genre = rs.getString("Genre");
                    img = rs.getString("Img");
                    bookIndex = rs.getInt("BookID") - 1;

                    IBookModel temp = new BookModel(title, author, description,  ISBN, genre, img);
                    temp.setBookIndex(bookIndex);
                    info.add(temp);
                }
                addToList(info);
                getConnection().close();
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToList(ArrayList<IBookModel> info) {
        info.forEach(ibm -> {
            IBookPresenter bp = new BookPresenter();
            IBookView bv = new BookView();
            bp.setModel(ibm);
            ibm.setPresenter(bp);
            bp.setView(bv);
            bv.setPresenter(bp);
            this.bookList.add(ibm);
        });
    }
}
