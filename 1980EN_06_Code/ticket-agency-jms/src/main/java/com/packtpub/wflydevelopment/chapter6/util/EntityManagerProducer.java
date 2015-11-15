package com.packtpub.wflydevelopment.chapter6.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Yoshimasa Tanabe
 */
public class EntityManagerProducer {

  @Produces
  @PersistenceContext
  private EntityManager em;

}
