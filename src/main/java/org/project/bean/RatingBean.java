package org.project.bean;

public class RatingBean {
    private int ratingId;
    private int ratingValue;
    private String ratingDescription;
    private int freelancerId;
    private int hirerId;

    public RatingBean(int ratingValue, String ratingDescription, int freelancerId, int hirerId) {
        this.ratingValue = ratingValue;
        this.ratingDescription = ratingDescription;
        this.freelancerId = freelancerId;
        this.hirerId = hirerId;
    }

    public RatingBean(int ratingId, int ratingValue, String ratingDescription) {
        this.ratingId = ratingId;
        this.ratingValue = ratingValue;
        this.ratingDescription = ratingDescription;
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

    public String getRatingDescription() {
        return ratingDescription;
    }

    public void setRatingDescription(String ratingDescription) {
        this.ratingDescription = ratingDescription;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public int getHirerId() {
        return hirerId;
    }

    public void setHirerId(int hirerId) {
        this.hirerId = hirerId;
    }
}
