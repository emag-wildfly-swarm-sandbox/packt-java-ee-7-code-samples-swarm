# ticket-agency-jms

## Setup

Change `/path/to` in your environment.

### PostgreSQL

``` sh
$ docker run --name ticketsystem \
  -e POSTGRES_USER=ticketsystem -e POSTGRES_PASSWORD=ticketsystem \
  -v /path/to/pgdata/data:/var/lib/postgresql/data \
  -p 5432:5432 \
  -d postgres
```

If you want to check PostgreSQL by psql, do the following command.

``` sh
$ docker run --link ticketsystem:db \
  --rm -it postgres \
  sh -c 'exec psql -h "$DB_PORT_5432_TCP_ADDR" -p "$DB_PORT_5432_TCP_PORT" -U ticketsystem'
```

## Run App

``` sh
$ mvn clean package && java -jar ./target/ticket-agency-jms-swarm.jar
```