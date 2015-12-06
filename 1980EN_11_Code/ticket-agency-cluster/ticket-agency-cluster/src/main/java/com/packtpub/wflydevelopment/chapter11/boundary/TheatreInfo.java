package com.packtpub.wflydevelopment.chapter11.boundary;

import com.packtpub.wflydevelopment.chapter11.control.TheatreBox;
import com.packtpub.wflydevelopment.chapter11.entity.Seat;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Yoshimasa Tanabe
 */
@Stateless
@Remote(TheatreInfoRemote.class)
public class TheatreInfo implements TheatreInfoRemote {

  private static final Logger LOGGER = Logger.getLogger(TheatreInfo.class);

  @EJB
  private TheatreBox box;

  @Override
  public String printSeatList() {
    LOGGER.info("Printing");

    return box.getSeats().stream()
      .map(Seat::toString)
      .collect(Collectors.joining(System.lineSeparator()));
  }

}
