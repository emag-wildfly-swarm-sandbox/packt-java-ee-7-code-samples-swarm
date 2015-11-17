package com.packtpub.wflydevelopment.chapter8.boundary;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author Yoshimasa Tanabe
 */
@WebService(
  targetNamespace = "http://www.packtpub.com/",
  serviceName = "CalculatePowerWebService"
)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CalculatePowerWebService {

  @WebMethod
  @WebResult(name = "result")
  public double calculatePower(@WebParam(name = "base") double base,
                               @WebParam(name = "exponent") double exponent) {
    return Math.pow(base, exponent);
  }

}
