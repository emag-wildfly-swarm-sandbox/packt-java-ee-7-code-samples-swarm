package com.packtpub.wflydevelopment.chapter8.boundary;

import com.packtpub.wflydevelopment.chapter8.control.JSONEncoder;
import com.packtpub.wflydevelopment.chapter8.controller.SessionRegistry;
import com.packtpub.wflydevelopment.chapter8.entity.Seat;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.sound.midi.Soundbank;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Yoshimasa Tanabe
 */
@ApplicationScoped
@ServerEndpoint(value = "/tickets", encoders = {JSONEncoder.class})
public class TicketEndpoint {

  @Inject
  private SessionRegistry sessionRegistry;

  @OnOpen
  public void open(Session session, EndpointConfig config) {
    sessionRegistry.add(session);
  }

  @OnClose
  public void close(Session session, CloseReason reason) {
    sessionRegistry.remove(session);
  }

  public void send(@Observes Seat seat) {
    sessionRegistry.getAll().forEach(session -> {
      session.getAsyncRemote().sendObject(seat);
    });
  }

}
