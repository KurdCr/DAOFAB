package com.daofab.daofab.model;

import java.math.BigDecimal;

public class Child {

  private Long id;
  private Long parentId;
  private BigDecimal paidAmount;

  public Child() {}

  public Child(Long id, Long parentId, BigDecimal paidAmount) {
    this.id = id;
    this.parentId = parentId;
    this.paidAmount = paidAmount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public BigDecimal getPaidAmount() {
    return paidAmount;
  }

  public void setPaidAmount(BigDecimal paidAmount) {
    this.paidAmount = paidAmount;
  }
}
