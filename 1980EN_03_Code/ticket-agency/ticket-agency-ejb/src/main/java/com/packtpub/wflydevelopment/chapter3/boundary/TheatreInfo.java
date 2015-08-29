package com.packtpub.wflydevelopment.chapter3.boundary;

import com.packtpub.wflydevelopment.chapter3.control.TheatreBox;
import com.packtpub.wflydevelopment.chapter3.entity.Seat;

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

  @EJB
  private TheatreBox box;

  @Override
  public String printSeatList() {
    final Collection<Seat> seats = box.getSeats();

    return seats.stream()
      .map(Seat::toString)
      .collect(Collectors.joining(System.lineSeparator()));
  }

}
