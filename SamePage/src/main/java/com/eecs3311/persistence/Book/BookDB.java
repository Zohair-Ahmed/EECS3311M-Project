package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.BookModel;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Reviews;
import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.view.Book.BookView;
import com.eecs3311.view.Book.IBookView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookDB implements IBook {
    private String title;
    private String description;
    private ArrayList<Reviews> reviews;
    private String ISBN;
    private String author;
    private String genre;
    private String pubDate;
    private String url = "jdbc:mysql://127.0.0.1:3306/samepageschema";
    private String user = "root";
    private String password = "root1234";
    private Date publicationDate;
    private Connection conn;
    private ObjectMapper objectMapper = new ObjectMapper();
    private File file = new File("/Users/nargisghiasi/IdeaProjects/EECS3311M-Project/SamePage/src/main/java/com/eecs3311/persistence/Book/bookMocks.json");
    //this path was unique to mine^
    private ArrayList<IBookModel> bookList = new ArrayList<>();

    public BookDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connection is successful");
                if (!dataExists()){
                    prepopulateData();
                }
                getDBdata();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean dataExists() throws SQLException {
        String sql = "SELECT * FROM Book WHERE ISBN13 = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "9789000307975"); // replace 1 with the ID of the data you want to check for
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            System.out.println("Data exists in table.");
            return true;
        } else {
            System.out.println("Data does not exist in table.");
            return false;
        }
    }

    public void prepopulateData(){
        Calendar calendar1 = Calendar.getInstance();
        try {
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = format.parse(pubDate);
            publicationDate = new Date(date.getTime());
        }
        catch(Exception e) {
            publicationDate = new Date(calendar1.getTime().getTime());
        }
        // make sure entry is not null
        try {

            JsonNode jsonNode = objectMapper.readTree(file);
            if (conn != null) {
                System.out.println("Connection is successful");
                // regardless of event type, the event table must be filled first as its the parent of these 3 event types
                String query = " insert into book (Title, Author, Description, ISBN13, PublicationDate, Genre)"
                        + " values (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                for (JsonNode node : jsonNode) {
                    ISBN = node.get("ISBN").asText();
                    title = node.get("title").asText();
                    author = node.get("author").asText();
                    description = node.get("summary").asText();
                    genre = node.get("genre").asText();
                    preparedStmt.setString(1, title);
                    preparedStmt.setString(2, author);
                    preparedStmt.setString(3, description);
                    preparedStmt.setString(4, ISBN);
                    preparedStmt.setString(5, publicationDate.toString());
                    preparedStmt.setString(6, genre);
                    preparedStmt.executeUpdate();
                }
                //conn.close();
            }
            else {
                System.out.println("Failed to connect");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<IBookModel> getLatestReleases(){
        return bookList;
    }
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
                    //Date publicationDate = rs.getDate("PubDate");
                    genre = rs.getString("Genre");
                    info.add(new BookModel(title, author, description, null, ISBN, genre));
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
