package com.packtpub.wflydevelopment.chapter10.util;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * @author Yoshimasa Tanabe
 */
public class FacesContextProducer {

  @Produces
  @RequestScoped
  public FacesContext produceFacesContext() {
    return FacesContext.getCurrentInstance();
  }

}
