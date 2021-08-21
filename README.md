# Url shortener using Kafka Streams and CQRS

URL shortening is used to create shorter aliases for long URLs. We call these shortened aliases "short links". Users are redirected to the original URL when they hit these short links. Short links save a lot of space when displayed, printed messaged or tweeted. Additionally, users are less likely to misstype shorter URLs.

## Prerequisites

To run this project you need a kafka cluster running, you'll find in this project a docker-compose file that contains zookeeper and 3 brokers (3 brokers are enough to keep the cluster up and running).

## Architecture

![Alt text](./archi.png?raw=true "Architecture")

Url shortener implementation using only Apache Kafka (simple producer and Kafka Streams library). 

## CQRS pattern

CQRS stands for Command and Query Responsibility Segregation, a pattern that separates read and update operations for a data store. Implementing CQRS in your application can maximize its performance, scalability, and security. The flexibility created by migrating to CQRS allows a system to better evolve over time and prevents update commands from causing merge conflicts at the domain level.

## Interactive queries

Kafka Streams natively provides all of the required functionality for interactively querying the state of the application. \
A Kafka Streams application typically runs on multiple instances. The state that is locally available on any given instance is only a subset of the application’s entire state. Querying the local stores on an instance will only return data locally available on that particular instance. \

### Sharding

Kafka Streams materializes one state store per stream partition. This means the application will potentially manage many underlying state stores. The API enables to query all of the underlying stores without having to know which partition the data is in.

## Author

- **Zakaria Maaraki** - _Initial work_ - [zakariamaaraki](https://github.com/zakariamaaraki)
