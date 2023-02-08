package com.eecs3311.model;

import java.util.ArrayList;


public interface Product {
    //genre is enum
    String getTitle();
    String getDescription();
    ArrayList<Reviews> getReviews();

}
