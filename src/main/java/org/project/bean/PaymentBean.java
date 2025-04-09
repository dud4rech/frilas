package org.project.bean;

public class PaymentBean {
    private int paymentId;
    private int paymentValue;
    private String paymentDate;
    private int paymentStatus;
    private int freelancerId;
    private int hirerid;

    public PaymentBean(int paymentValue, String paymentDate, int paymentStatus, int freelancerId, int hirerid) {
        this.paymentValue = paymentValue;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.freelancerId = freelancerId;
        this.hirerid = hirerid;
    }

    public PaymentBean(int paymentId, int paymentValue, String paymentDate, int paymentStatus, int freelancerId, int hirerid) {
        this.paymentId = paymentId;
        this.paymentValue = paymentValue;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.freelancerId = freelancerId;
        this.hirerid = hirerid;
    }

    public PaymentBean(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(int paymentValue) {
        this.paymentValue = paymentValue;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public int getHirerid() {
        return hirerid;
    }

    public void setHirerid(int hirerid) {
        this.hirerid = hirerid;
    }
}
