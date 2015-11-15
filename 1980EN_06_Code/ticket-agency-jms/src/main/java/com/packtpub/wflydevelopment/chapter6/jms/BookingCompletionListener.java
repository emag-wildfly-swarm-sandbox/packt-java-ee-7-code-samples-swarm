package com.packtpub.wflydevelopment.chapter6.jms;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.CompletionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.logging.Logger;

/**
 * @author Yoshimasa Tanabe
 */
@ApplicationScoped
public class BookingCompletionListener implements CompletionListener {

  @Inject
  private Logger logger;

  @Override
  public void onCompletion(Message message) {
    try {
      final String text = message.getBody(String.class);
      logger.info("Send was successful: " + text);
    } catch (JMSException e) {
      logger.severe("Problem with message format");
    }
  }

  @Override
  public void onException(Message message, Exception exception) {
    try {
      final String text = message.getBody(String.class);
      logger.info("Send failed...: " + text);
    } catch (JMSException e) {
      logger.severe("Problem with message format");
    }
  }

}
