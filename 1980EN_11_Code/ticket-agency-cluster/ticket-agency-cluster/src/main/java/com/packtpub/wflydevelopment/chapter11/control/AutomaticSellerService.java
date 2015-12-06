package com.packtpub.wflydevelopment.chapter11.control;

import com.packtpub.wflydevelopment.chapter11.entity.Seat;
import com.packtpub.wflydevelopment.chapter11.exception.NoSuchSeatException;
import com.packtpub.wflydevelopment.chapter11.exception.SeatBookedException;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Yoshimasa Tanabe
 */
@Stateless
public class AutomaticSellerService {

  private static final Logger LOGGER = Logger.getLogger(AutomaticSellerService.class);

  @EJB
  private TheatreBox box;

  @Resource
  private TimerService timerService;

  @Schedule(hour = "*", minute = "*/1", persistent = false)
  public void automaticCustomer() throws NoSuchSeatException {
    final Optional<Seat> seatOptional = findFreeSeat();

    if (! seatOptional.isPresent()) {
      cancelTimers();
      LOGGER.info("Scheduler gone!");
      return;
    }

    final Seat seat = seatOptional.get();

    try {
      box.buyTicket(seat.getId());
    } catch (SeatBookedException e) {
      LOGGER.info(e.getMessage());
    }

    LOGGER.info("Somebody just booked seat number " + seat.getId());
  }

  private Optional<Seat> findFreeSeat() {
    final Collection<Seat> list = box.getSeats();
    return list.stream().
      filter(seat -> !seat.isBooked())
      .findFirst();
  }

  private void cancelTimers() {
    timerService.getTimers().forEach(Timer::cancel);
  }

}
