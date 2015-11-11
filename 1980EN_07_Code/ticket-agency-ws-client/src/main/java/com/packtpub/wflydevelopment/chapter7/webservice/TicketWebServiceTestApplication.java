package com.packtpub.wflydevelopment.chapter7.webservice;

import com.packtpub.wflydevelopment.chapter7.boundary.SeatDto;
import com.packtpub.wflydevelopment.chapter7.boundary.TicketWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Yoshimasa Tanabe
 */
public class TicketWebServiceTestApplication {

  private static final Logger LOGGER = Logger.getLogger(TicketWebServiceTestApplication.class.getName());

  public static void main(String[] args) throws MalformedURLException {
    final int seatId = 1;

    LOGGER.info("TEST SOAP WS Service");

    final URL wsdlURL = new URL("http://localhost:8080/TicketWebService?wsdl");
    final QName SERVICE_NAME = new QName("http://www.packtpub.com/", "TicketWebService");
    final Service service = Service.create(wsdlURL, SERVICE_NAME);
    final TicketWebService infoService = service.getPort(TicketWebService.class);

    LOGGER.info("Got the Service: " + infoService);

    try {
      infoService.bookSeat(seatId);
      LOGGER.info("Ticket Booked with JAX-WS Service");
    } catch (Exception e) {
      LOGGER.warning(e.getMessage());
    }

    final List<SeatDto> seats = infoService.getSeats();

    dumpSeats(seats);
  }

  private static void dumpSeats(List<SeatDto> seats) {
    LOGGER.info("=== Available Ticket List ===");
    seats.stream().forEach(seat -> LOGGER.info(seat.toString()));
  }

}
