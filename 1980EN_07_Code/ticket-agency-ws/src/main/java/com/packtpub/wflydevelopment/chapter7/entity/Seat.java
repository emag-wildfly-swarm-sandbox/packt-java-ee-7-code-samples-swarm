package com.packtpub.wflydevelopment.chapter7.entity;

/**
 * @author Yoshimasa Tanabe
 */
public class Seat {

  private final int id;
  private final String name;
  private final int price;
  private final boolean booked;

  public Seat(int id, String name, int price) {
    this(id, name, price, false);
  }

  private Seat(int id, String name, int price, boolean booked) {
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
