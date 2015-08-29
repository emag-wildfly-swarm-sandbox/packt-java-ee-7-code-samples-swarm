package com.packtpub.wflydevelopment.chapter4.util;

import org.jboss.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * @author Yoshimasa Tanabe
 */
@Interceptor
@Logged
public class LoggingInterceptor implements Serializable {

  @AroundInvoke
  public Object log(InvocationContext context) throws Exception {
    final Logger logger = Logger.getLogger(context.getTarget().getClass());
    logger.infov("Excuted method {0}", context.getMethod().toString());
    return context.proceed();
  }

}
