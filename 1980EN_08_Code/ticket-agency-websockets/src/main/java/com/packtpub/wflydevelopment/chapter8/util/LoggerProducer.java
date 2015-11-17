package com.packtpub.wflydevelopment.chapter8.util;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * @author Yoshimasa Tanabe
 */
@Dependent
public class LoggerProducer {

  @Produces
  public Logger produceLogger(InjectionPoint injectionPoint) {
    return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
  }

}
