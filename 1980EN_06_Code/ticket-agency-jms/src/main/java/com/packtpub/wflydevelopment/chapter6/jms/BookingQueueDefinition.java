package com.packtpub.wflydevelopment.chapter6.jms;

import javax.jms.JMSDestinationDefinition;

/**
 * @author Yoshimasa Tanabe
 */
// FIXME WildFly Swarm don't read @JMSDestinationDefinition
//@JMSDestinationDefinition(
//  name = BookingQueueDefinition.BOOKING_QUEUE,
//  interfaceName = "javax.jms.Queue"
//)
public class BookingQueueDefinition {

//  public static final String BOOKING_QUEUE = "java:global/jms/queue/bookingQueue";
  public static final String BOOKING_QUEUE = "/jms/queue/bookingQueue";

}
