package com.packtpub.wflydevelopment.chapter10;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.management.ManagementFraction;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * @author Yoshimasa Tanabe
 */
public class App {

  public static void main(String[] args) throws Exception {
    Container container = new Container();

    container.fraction(ManagementFraction.createDefaultFraction()
      .securityRealm("ApplicationRealm", (realm) -> {
        realm.inMemoryAuthentication((authn) -> {
          authn.add("demouser", "password", true);
        });
        realm.inMemoryAuthorization((authz) -> {
          authz.add("demouser", "Manager");
        });
      }));

    WARArchive deployment = ShrinkWrap.create(WARArchive.class);
    deployment.addPackages(true, App.class.getPackage());

    deployment.addAsWebResource(
      new ClassLoaderAsset("index.xhtml", App.class.getClassLoader()), "index.xhtml");
    deployment.addAsWebResource(
      new ClassLoaderAsset("resources/style.css", App.class.getClassLoader()), "resources/style.css");

    deployment.addAsWebInfResource(
      new ClassLoaderAsset("WEB-INF/web.xml", App.class.getClassLoader()), "web.xml");
    deployment.addAsWebInfResource(
      new ClassLoaderAsset("WEB-INF/templates/default.xhtml", App.class.getClassLoader()), "templates/default.xhtml");
    deployment.addAsWebInfResource(
      new ClassLoaderAsset("WEB-INF/beans.xml", App.class.getClassLoader()), "beans.xml");

    deployment.addAllDependencies();

    container.start().deploy(deployment);
  }

}
