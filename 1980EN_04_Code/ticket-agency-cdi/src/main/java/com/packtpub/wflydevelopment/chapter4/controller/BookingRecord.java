package com.packtpub.wflydevelopment.chapter4.controller;

import com.packtpub.wflydevelopment.chapter4.entity.Seat;
import com.packtpub.wflydevelopment.chapter4.util.NamedView;

import javax.enterprise.event.Observes;
import java.io.Serializable;

/**
 * @author Yoshimasa Tanabe
 */
@NamedView
public class BookingRecord implements Serializable {

  private int bookedCount = 0;

  public int getBookedCount() {
    return bookedCount;
  }

  public void bookEvent(@Observes Seat bookedSeat) {
    bookedCount++;
  }

}
