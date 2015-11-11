package com.packtpub.wflydevelopment.chapter5;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * @author Yoshimasa Tanabe
 */
public class App {

  public static void main(String[] args) throws Exception {

    Container container = new Container();

    container.fraction(new DatasourcesFraction()
      .jdbcDriver("org.postgresql", (d) -> {
        d.xaDatasourceClass("org.postgresql.xa.PGXADataSource");
        d.driverModuleName("org.postgresql");
      })
      .dataSource("wildflydevelopment", (ds) -> {
        ds.driverName("org.postgresql");
        ds.connectionUrl("jdbc:postgresql://localhost:5432/ticketsystem");
        ds.userName("ticketsystem");
        ds.password("ticketsystem");
      })
    );

    container.start();

    WARArchive deployment = ShrinkWrap.create(WARArchive.class);

    deployment.addPackages(true, App.class.getPackage());

    deployment.addAsWebResource(
      new ClassLoaderAsset("index.xhtml", App.class.getClassLoader()), "index.xhtml");
    deployment.addAsWebResource(
      new ClassLoaderAsset("views/setup.xhtml", App.class.getClassLoader()), "views/setup.xhtml");
    deployment.addAsWebResource(
      new ClassLoaderAsset("views/book.xhtml", App.class.getClassLoader()), "views/book.xhtml");

    deployment.addAsWebInfResource(
      new ClassLoaderAsset("WEB-INF/web.xml", App.class.getClassLoader()), "web.xml");
    deployment.addAsWebInfResource(
      new ClassLoaderAsset("WEB-INF/beans.xml", App.class.getClassLoader()), "beans.xml");
    deployment.addAsWebInfResource(
      new ClassLoaderAsset("WEB-INF/templates/default.xhtml", App.class.getClassLoader()), "templates/default.xhtml");
    deployment.addAsWebInfResource(
      new ClassLoaderAsset("META-INF/persistence.xml", App.class.getClassLoader()), "classes/META-INF/persistence.xml");

    deployment.addAllDependencies();

    container.deploy(deployment);

  }

}
