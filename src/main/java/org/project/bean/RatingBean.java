package org.project.bean;

public class RatingBean {
    private int ratingId;
    private int ratingValue;
    private String ratingMessage;
    private int freelancerId;

    public RatingBean(int ratingValue, String ratingMessage, int freelancerId) {
        this.ratingValue = ratingValue;
        this.ratingMessage = ratingMessage;
        this.freelancerId = freelancerId;
    }

    public RatingBean(int ratingId, int ratingValue, String ratingMessage) {
        this.ratingId = ratingId;
        this.ratingValue = ratingValue;
        this.ratingMessage = ratingMessage;
    }

    public RatingBean(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getRatingMessage() {
        return ratingMessage;
    }

    public void setRatingMessage(String ratingMessage) {
        this.ratingMessage = ratingMessage;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }
}
