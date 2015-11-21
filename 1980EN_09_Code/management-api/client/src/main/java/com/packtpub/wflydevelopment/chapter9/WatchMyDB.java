package com.packtpub.wflydevelopment.chapter9;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Yoshimasa Tanabe
 */
public class WatchMyDB {

  public static void main(String[] args) throws IOException {
    final ModelControllerClient client =
      ModelControllerClient.Factory.create(InetAddress.getByName("localhost"), 9990);

    final ModelNode operation = new ModelNode();
    operation.get("operation").set("read-resource");
    operation.get("include-runtime").set(true);

    final ModelNode address = operation.get("address");
    address.add("subsystem", "datasources");
    address.add("data-source", "ExampleDS");
    address.add("statistics", "pool");
    operation.get("recursive").set(true);
    operation.get("operations").set(true);

    final ModelNode returnVal = client.execute(operation);

    final ModelNode resultNode = returnVal.get("result");
    final ModelNode activeCount = resultNode.get("ActiveCount");

    System.out.println(activeCount);

    client.close();
  }

}
