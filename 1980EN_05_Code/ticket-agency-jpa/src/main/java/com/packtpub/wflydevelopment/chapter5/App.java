package com.packtpub.wflydevelopment.chapter5;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.Datasource;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.datasources.Driver;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * @author Yoshimasa Tanabe
 */
public class App {

  public static void main(String[] args) throws Exception {

    Container container = new Container();

    container.subsystem(new DatasourcesFraction()
        .driver(
          new Driver("org.postgresql")
            .xaDatasourceClassName("org.postgresql.xa.PGXADataSource")
            .module("org.postgresql"))
        .datasource(new Datasource("wildflydevelopment")
          .driver("org.postgresql")
          .connectionURL("jdbc:postgresql://localhost:5432/ticketsystem")
          .authentication("ticketsystem", "ticketsystem"))
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
