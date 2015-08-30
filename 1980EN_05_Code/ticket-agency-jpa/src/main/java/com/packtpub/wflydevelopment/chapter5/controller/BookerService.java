package com.packtpub.wflydevelopment.chapter5.controller;

import com.packtpub.wflydevelopment.chapter5.control.TicketService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Yoshimasa Tanabe
 */
@Named
@ViewScoped
public class BookerService implements Serializable {

  @Inject
  private Logger logger;

  @Inject
  private TicketService ticketService;

  @Inject
  private FacesContext facesContext;

  private int money;

  @PostConstruct
  public void createCustomer() {
    this.money = 100;
  }

  public void bookSeat(long seatId, int price) {
    logger.info("Booking seat: " + seatId);

    if (price > money) {
      final FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not enough Money!", "Registration failed");
      facesContext.addMessage(null, m);
      return;
    }

    ticketService.bookSeat(seatId);

    final FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
    facesContext.addMessage(null, m);
    logger.info("Seat booked.");

    money = money - price;
  }

  public int getMoney() {
    return money;
  }

}
