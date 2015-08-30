package com.packtpub.wflydevelopment.chapter5.control;

import com.packtpub.wflydevelopment.chapter5.entity.Seat;
import com.packtpub.wflydevelopment.chapter5.entity.SeatType;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Yoshimasa Tanabe
 */
@Stateless
public class TicketService {

  @Inject
  private Logger log;

  @Inject
  private Event<SeatType> seatTypeEventSrc;

  @Inject
  private Event<Seat> seatEventSrc;

  @Inject
  private SeatDao seatDao;

  @Inject
  private SeatTypeDao seatTypeDao;

  public void createSeatType(SeatType seatType) throws Exception {
    log.info("Registering " + seatType.getDescription());
    seatTypeDao.persist(seatType);
    seatTypeEventSrc.fire(seatType);
  }

  public void createTheatre(List<SeatType> seatTypes) {
    for (SeatType seatType : seatTypes) {
      for (int i = 0; i < seatType.getQuantity(); i++) {
        final Seat seat = new Seat();
        seat.setBooked(false);
        seat.setSeatType(seatType);
        seatDao.persist(seat);
      }
    }
  }

  public void bookSeat(long seatId) {
    final Seat seat = seatDao.find(seatId);
    seat.setBooked(true);
    seatDao.persist(seat);
    seatEventSrc.fire(seat);
  }

  public void doCleanUp() {
    seatDao.deleteAll();
    seatTypeDao.deleteAll();
  }
}
