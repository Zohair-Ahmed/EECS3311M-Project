package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.BookModel;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Review.ReviewModel;
import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.view.Book.BookView;
import com.eecs3311.view.Book.IBookView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * BookDB - Book Database class to get all the available latest book releases
 * for users in the SamePage app.
 * Makes sql connection and uses samepageschema to store/retrieve book data.
 */
public class BookDB implements IBook {
    private String title;
    private String description;
    private ArrayList<ReviewModel> reviews;
    private String ISBN;
    private String author;
    private String genre;
    private String img;
    private String url = "jdbc:mysql://127.0.0.1:3306/samepageschema";
    private String user = "root";
    private String password = "1234";
    private Connection conn;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private File file = new File("/Users/nick/Desktop/EECS3311-Workspace/EECS3311M-Project/SamePage/src/main/java/com/eecs3311/persistence/Book/bookMocks.json");
    private ArrayList<IBookModel> bookList = new ArrayList<>();

    /**
     * BookDB constructor, makes initial connection to database
     * with JDBC driver and database URL.
     */
    public BookDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connection is successful");
                // If the database is empty, call prepopulateData
                if (!dataExists()){
                    prepopulateData();
                }
                getDBdata();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if book data has already been pre-populated and exists in database
     */
    @Override
    public boolean dataExists() throws SQLException {
        // select from book where the ISBN matches the first entry
        String sql = "SELECT * FROM Book WHERE ISBN13 = 9789000307975";
        PreparedStatement stmt = conn.prepareStatement(sql);
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
            JsonNode jsonNode = objectMapper.readTree(file);
            if (conn != null) {
                System.out.println("Connection is successful");
                String query = " insert into book (Title, Author, Description, ISBN13, Img, Genre)"
                        + " values (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
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

    /**
     * Returns the bookList with all latest books in DB
     */
    public ArrayList<IBookModel> getLatestReleases(){
        return bookList;
    }

    /**
     * Get the existing book data from database and pass it to Book Model
     */
    public void getDBdata() {
        try {
            if (conn != null) {
                ArrayList<IBookModel> info = new ArrayList<>();
                System.out.println("Connection is successful");
                String query = "SELECT * FROM book";
                Statement st = conn.createStatement();
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
                    info.add(new BookModel(title, author, description, null, ISBN, genre, img));
                }
                addToList(info);
                conn.close();
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToList(ArrayList<IBookModel> info) {
        for (IBookModel ibm : info) {
            // Set Model <-> Presenter <-> View connection to model
            IBookPresenter bp = new BookPresenter();
            IBookView bv = new BookView();
            bp.setModel(ibm);
            ibm.setPresenter(bp);
            bp.setView(bv);
            bv.setPresenter(bp);
            this.bookList.add(ibm);
        }
    }
}
