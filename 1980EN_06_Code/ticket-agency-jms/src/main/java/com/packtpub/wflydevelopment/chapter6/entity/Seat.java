package com.packtpub.wflydevelopment.chapter6.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Yoshimasa Tanabe
 */
@Entity
@Table(name = "seats")
public class Seat implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean booked;

  @ManyToOne
  @JoinColumn(name = "seat_id")
  private SeatType seatType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isBooked() {
    return booked;
  }

  public void setBooked(boolean booked) {
    this.booked = booked;
  }

  public SeatType getSeatType() {
    return seatType;
  }

  public void setSeatType(SeatType seatType) {
    this.seatType = seatType;
  }

  @Override
  public String toString() {
    return "Seat{" +
      "id=" + id +
      ", booked=" + booked +
      ", seatType=" + seatType +
      '}';
  }

}
