package com.packtpub.wflydevelopment.chapter7.boundary;

import javax.jws.WebService;
import java.util.List;

/**
 * @author Yoshimasa Tanabe
 */
@WebService
public interface TicketWebService {

  List<SeatDto> getSeats();
  void bookSeat(int seatId);

}
