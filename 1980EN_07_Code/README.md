# ticket-agency-ws

## Run API Server

``` sh
$ cd ticket-agency-ws
$ mvn wildfly-swarm:run
```

## Run client

``` sh
$ cd ticket-agency-ws-client
$ mvn compile exec:java -Dexec.mainClass=com.packtpub.wflydevelopment.chapter7.webservice.TicketWebServiceTestApplication -Djava.util.logging.config.file=logging.properties
```
