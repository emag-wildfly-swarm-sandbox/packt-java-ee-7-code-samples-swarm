package com.packtpub.wflydevelopment.chapter7.boundary;

import com.packtpub.wflydevelopment.chapter7.controller.TheatreBooker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Yoshimasa Tanabe
 */
@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountResource {

  @Inject
  private TheatreBooker theatreBooker;

  @GET
  public AccountDto getAccount() {
    return AccountDto.fromAccount(theatreBooker.getCurrentAccount());
  }

  @POST
  public Response renew() {
    theatreBooker.createCustomer();
    return Response
      .ok(AccountDto.fromAccount(theatreBooker.getCurrentAccount()))
      .build();
  }

}
