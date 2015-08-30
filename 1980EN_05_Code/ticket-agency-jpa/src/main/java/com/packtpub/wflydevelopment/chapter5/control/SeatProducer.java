package com.packtpub.wflydevelopment.chapter5.control;

import com.packtpub.wflydevelopment.chapter5.entity.Seat;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author Yoshimasa Tanabe
 */
@RequestScoped
public class SeatProducer {

  @Inject
  private SeatDao seatDao;

  private List<Seat> seats;

  @PostConstruct
  public void retrieveAllSeats() {
    seats = seatDao.findAll();
  }

  @Produces
  @Named
  public List<Seat> getSeats() {
    return seats;
  }

  public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Seat member) {
    retrieveAllSeats();
  }

}
