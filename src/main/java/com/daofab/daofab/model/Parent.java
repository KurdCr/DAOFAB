package com.daofab.daofab.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Parent {

  private Long id;

  private String sender;
  private String receiver;
  private BigDecimal totalAmount;
  private List<Child> children;

  public Parent(String sender, String receiver, BigDecimal totalAmount) {
    this.sender = sender;
    this.receiver = receiver;
    this.totalAmount = totalAmount;
    children = new ArrayList<Child>();
  }

  public Parent() {
    children = new ArrayList<Child>();
  }

  public Parent(
    Long id,
    String sender,
    String receiver,
    BigDecimal totalAmount
  ) {
    this.id = id;
    this.sender = sender;
    this.receiver = receiver;
    this.totalAmount = totalAmount;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public BigDecimal getTotalPaidAmount() {
    return totalAmount;
  }

  public void setTotalPaidAmount(BigDecimal totalPaidAmount) {
    this.totalAmount = totalPaidAmount;
  }

  public void addChild(Child child) {
    children.add(child);
  }

  public List<Child> getChildren() {
    return children;
  }
  // Constructors, getters, and setters
}
