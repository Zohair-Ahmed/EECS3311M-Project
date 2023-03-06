package com.eecs3311.view.Review;

import com.eecs3311.presenter.Review.IReviewPresenter;

import javax.swing.*;

public class ReviewPanelView implements IReviewPanelView{

    private IReviewPresenter irp;
    private JPanel root;

    @Override
    public IReviewPresenter getPresenter() {
        return irp;
    }

    @Override
    public void setPresenter(IReviewPresenter irp) {
        this.irp = irp;
    }

    @Override
    public JPanel getView() {
        root = new JPanel();
        String text = getPresenter().getModel().getUsername()+" - "+getPresenter().getModel().getRating() + " - "+getPresenter().getModel().getReview();
        root.add(new JLabel(text));

        return root;
    }
}
