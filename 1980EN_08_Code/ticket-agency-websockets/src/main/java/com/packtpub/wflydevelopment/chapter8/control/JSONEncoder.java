package com.packtpub.wflydevelopment.chapter8.control;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author Yoshimasa Tanabe
 */
public class JSONEncoder implements Encoder.Text<Object> {

  private Gson gson;

  @Override
  public void init(EndpointConfig config) {
    System.out.println("#### JSONEncoder#init");
    gson = new Gson();
  }

  @Override
  public void destroy() {
    System.out.println("#### JSONEncoder#destroy");
  }

  @Override
  public String encode(Object object) throws EncodeException {
    System.out.println("#### JSONEncoder#encode");
    return gson.toJson(object);
  }

}
