package com.eecs3311.view.components;

import com.eecs3311.model.Follower.IFollowerModel;

import java.util.ArrayList;

public class FollowerMediator {

    private SearchFriends sbf;
    private UserResultsPanel urp;

    public void updateUserResultsPanelView(ArrayList<IFollowerModel> results) {
        if (results != null) {
            urp.updateFriendsView(results);
        }
    }

    public UserResultsPanel getURP() {return urp;}

    public void setURP(UserResultsPanel urp) {this.urp = urp;}

    public SearchFriends getSbf() {return sbf;}

    public void setSbf(SearchFriends sf) {this.sbf = sf;}
}
