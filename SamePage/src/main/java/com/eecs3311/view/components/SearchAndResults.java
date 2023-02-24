package com.eecs3311.view.components;

import java.util.*;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.view.Book.LatestBookView;

public class SearchAndResults {
    private LatestBookView lbv;
    private SearchBarFrame sbf;

    public void updateBookView(ArrayList<IBookModel> results) {
        if (results != null)
            this.lbv.updateBookView(results);
    }

    public LatestBookView getLbv() {
        return lbv;
    }

    public void setLbv(LatestBookView lbv) {
        this.lbv = lbv;
    }

    public SearchBarFrame getSbf() {
        return sbf;
    }

    public void setSbf(SearchBarFrame sbf) {
        this.sbf = sbf;
    }

}
