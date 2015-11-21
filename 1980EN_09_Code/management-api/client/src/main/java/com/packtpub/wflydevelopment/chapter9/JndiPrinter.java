package com.packtpub.wflydevelopment.chapter9;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Yoshimasa Tanabe
 */
public class JndiPrinter {

  public static void main(String[] args) throws IOException {

    final ModelControllerClient client =
      ModelControllerClient.Factory.create(InetAddress.getByName("localhost"), 9990);

    final ModelNode operation = new ModelNode();
    operation.get("operation").set("jndi-view");

    final ModelNode address = operation.get("address");
    address.add("subsystem", "naming");

    operation.get("recursive").set(true);
    operation.get("operations").set(true);

    final ModelNode returnVal = client.execute(operation);

    System.out.println(returnVal.get("result"));

    client.close();
  }

}
