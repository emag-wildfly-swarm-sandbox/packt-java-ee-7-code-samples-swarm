package com.packtpub.wflydevelopment.chapter11.entity;

import java.io.Serializable;

/**
 * @author Yoshimasa Tanabe
 */
public class Seat implements Serializable {

  private int id;
  private String name;
  private int price;
  private boolean booked;

  public Seat() {}

  public Seat(int id, String name, int price) {
    this(id, name, price, false);
  }

  public Seat(int id, String name, int price, boolean booked) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.booked = booked;
  }

  public Seat getBookedSeat() {
    return new Seat(getId(), getName(), getPrice(), true);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public boolean isBooked() {
    return booked;
  }

  @Override
  public String toString() {
    return "Seat{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", price=" + price +
      ", booked=" + booked +
      '}';
  }

}
