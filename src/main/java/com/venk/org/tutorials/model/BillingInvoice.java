package com.venk.org.tutorials.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author venkateshkaradbhajne
 */
@Entity
@Table(name = "BillingInvoice")
public class BillingInvoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private User user;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "courseId", referencedColumnName = "id")
  private Course course;

  @Column(name = "totalAmount")
  private int totalAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusEnum status;

  @Column(name = "paymentConfirmationId")
  private String paymentConfirmationId;

  public enum StatusEnum {
    pending, paid
  }

  public BillingInvoice() {
  }

  public long getId() {
    return id;
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

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public String getPaymentConfirmationId() {
    return paymentConfirmationId;
  }

  public void setPaymentConfirmationId(String paymentConfirmationId) {
    this.paymentConfirmationId = paymentConfirmationId;
  }
}
