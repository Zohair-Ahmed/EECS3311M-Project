package com.eecs3311.view.components;

import java.util.*;

import com.eecs3311.model.Book.IBookModel;

// Method used for connected the search bar and results panels in the landing page
public class ResultsMediator {
    private ResultsPanel lbv;
    private SearchBar sbf;

    // Updates results view through search bar results - if found
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
