package com.packtpub.wflydevelopment.chapter7.webservice;

import com.packtpub.wflydevelopment.chapter7.boundary.AccountDto;
import com.packtpub.wflydevelopment.chapter7.boundary.SeatDto;
import com.packtpub.wflydevelopment.chapter7.boundary.TicketWebService;

import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Yoshimasa Tanabe
 */
public class RestServiceTestApplication {

  private static final String APPLICATION_URL = "http://localhost:8080/rest/";

  private final WebTarget accountResource;
  private final WebTarget seatREsource;

  public RestServiceTestApplication() {
    final Client restClient = ClientBuilder.newClient();
    accountResource = restClient.target(APPLICATION_URL + "account");
    seatREsource = restClient.target(APPLICATION_URL + "seat");
  }

  public static void main(String[] args) throws MalformedURLException {
    new RestServiceTestApplication().runSample();
  }

  public void runSample() {
    printAccountStatusFromServer();

    System.out.println("=== Current status:");
    final Collection<SeatDto> seats = getSeatsFromServer();
    printSeats(seats);

    System.out.println("=== Booking:");
    bookSeats(seats);

    System.out.println("=== Status after booking:");
    final Collection<SeatDto> bookedSeats = getSeatsFromServer();
    printSeats(bookedSeats);

    printAccountStatusFromServer();
  }

  private void printAccountStatusFromServer() {
    final AccountDto account = accountResource.request().get(AccountDto.class);
    System.out.println(account);
  }

  private Collection<SeatDto> getSeatsFromServer() {
    return seatREsource.request().get(new GenericType<Collection<SeatDto>>(){});
  }

  private void printSeats(Collection<SeatDto> seats) {
    seats.forEach(System.out::println);
  }

  private void bookSeats(Collection<SeatDto> seats) {
    for (SeatDto seat : seats) {
      try {
        final String idOfSeat = Integer.toString(seat.getId());
        seatREsource.path(idOfSeat).request().post(Entity.json(""), String.class);
        System.out.println(seat + " booked");
      } catch (WebApplicationException e) {
        final Response response = e.getResponse();
        Response.StatusType statusInfo = response.getStatusInfo();
        System.out.println(seat + " not booked (" + statusInfo.getReasonPhrase() + "): " +
          response.readEntity(JsonObject.class).getString("entity"));
      }
    }
  }

}
