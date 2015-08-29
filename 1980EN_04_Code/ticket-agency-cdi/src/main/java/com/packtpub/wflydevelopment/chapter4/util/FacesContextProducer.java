package com.packtpub.wflydevelopment.chapter4.util;

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
