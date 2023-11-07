package com.venk.org.tutorials.model;

/**
 * @author venkateshkaradbhajne
 */

public class BillingInvoicePayment {

  private User user;

  private Course course;

  private int totalAmount;

  private String paymentConfirmationId;


  public BillingInvoicePayment() {
  }


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public int getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getPaymentConfirmationId() {
    return paymentConfirmationId;
  }

  public void setPaymentConfirmationId(String paymentConfirmationId) {
    this.paymentConfirmationId = paymentConfirmationId;
  }
}
