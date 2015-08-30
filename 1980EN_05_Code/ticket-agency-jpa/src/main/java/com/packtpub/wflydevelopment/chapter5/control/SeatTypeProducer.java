package com.packtpub.wflydevelopment.chapter5.control;

import com.packtpub.wflydevelopment.chapter5.entity.SeatType;

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
public class SeatTypeProducer {

  @Inject
  private SeatTypeDao seatTypeDao;

  private List<SeatType> seatTypes;

  @PostConstruct
  public void retrieveAllSeatTypes() {
    seatTypes = seatTypeDao.findAll();
  }

  @Produces
  @Named
  public List<SeatType> getSeatTypes() {
    return seatTypes;
  }

  public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SeatType member) {
    retrieveAllSeatTypes();
  }

}
