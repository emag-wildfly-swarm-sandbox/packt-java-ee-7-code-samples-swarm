package com.packtpub.wflydevelopment.chapter10.controller;

import com.packtpub.wflydevelopment.chapter10.boundary.TheatreBox;
import com.packtpub.wflydevelopment.chapter10.entity.Seat;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * @author Yoshimasa Tanabe
 */
@Model
public class TheatreInfo {

  @Inject
  private TheatreBox box;

  private Collection<Seat> seats;

  @PostConstruct
  public void retrievalAllSeatsOrderedByName() {
    seats = box.getSeats();
  }

  @Produces
  @Named()
  public Collection<Seat> getSeats() {
    return seats;
  }

  public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Seat member) {
    retrievalAllSeatsOrderedByName();
  }

}
