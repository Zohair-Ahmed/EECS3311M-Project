package com.eecs3311.view.components;

import java.util.*;

import com.eecs3311.model.Book.IBookModel;

public class ResultsMediator {
    private ResultsPanel lbv;
    private SearchBar sbf;

    public void updateBookView(ArrayList<IBookModel> results) {
        if (results != null)
            this.lbv.updateBookView(results);
    }

    public ResultsPanel getLbv() {
        return lbv;
    }

    public void setLbv(ResultsPanel lbv) {
        this.lbv = lbv;
    }

    public SearchBar getSbf() {
        return sbf;
    }

    public void setSbf(SearchBar sbf) {
        this.sbf = sbf;
    }

}
