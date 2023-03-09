package com.eecs3311.persistence.Book;

import com.eecs3311.model.Book.BookModel;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.view.Book.BookView;
import com.eecs3311.view.Book.IBookView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Database stub - Parses JSON and saves data as list instead of using sql database
 */
public class BookStub implements IBook{
    private final ArrayList<IBookModel> bookList = new ArrayList<>();
    private static BookStub instance = null;
    private BookStub(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream bookMocksFile = this.getClass().getClassLoader().getResourceAsStream("data/bookMocks.json");
            JsonNode jsonNode = objectMapper.readTree(bookMocksFile);

            for (JsonNode node : jsonNode) {
                String ISBN = node.get("ISBN").asText();
                String title = node.get("title").asText();
                String author = node.get("author").asText();
                String description = node.get("summary").asText();
                String genre = node.get("genre").asText();
                String img = node.get("image").asText();
                bookList.add(new BookModel(title, author, description, ISBN, genre, img));
            }
            sort(bookList);
            addToList(bookList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static BookStub getInstance(){
        if(instance == null){
            instance = new BookStub();
        }
        return instance;
    }

    @Override
    public ArrayList<IBookModel> getLatestReleases(){
        System.out.println("STUB DATA");
        return bookList;
    }

    public void sort(ArrayList<IBookModel> list){
        list.sort(Comparator.comparing(IBookModel::getISBN));
    }

    @Override
    public void addToList(ArrayList<IBookModel> info) {
        for (IBookModel ibm : info) {
            // Set Model <-> Presenter <-> View connection to model
            IBookPresenter bp = new BookPresenter();
            IBookView bv = new BookView();
            bp.setModel(ibm);
            ibm.setPresenter(bp);
            bp.setView(bv);
            bv.setPresenter(bp);
        }
    }
}
