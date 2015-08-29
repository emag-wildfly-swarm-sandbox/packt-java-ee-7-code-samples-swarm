package com.packtpub.wflydevelopment.chapter4.controller;

import com.packtpub.wflydevelopment.chapter4.boundary.TheatreBox;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 * @author Yoshimasa Tanabe
 */
@Model
public class Poller {

  @Inject
  private TheatreBox theatreBox;

  public boolean isPollingActive() {
    return areFreeSeatsAvailable();
  }

  private boolean areFreeSeatsAvailable() {
    return theatreBox.getSeats().stream()
      .anyMatch(seat -> !seat.isBooked());
  }

}
