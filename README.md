# Url shortener using Kafka Streams and CQRS

URL shortening is used to create shorter aliases for long URLs. We call these shortened aliases "short links". Users are redirected to the original URL when they hit these short links. Short links save a lot of space when displayed, printed messaged or tweeted. Additionally, users are less likely to misstype shorter URLs.

## Prerequisites

To run this project you need a kafka cluster running, you'll find a docker-compose file in this project, that contains zookeeper and 3 brokers (3 brokers are enough to keep the cluster up and running).

## Architecture

![Alt text](./archi.png?raw=true "Architecture")

Url shortener implementation using only Apache Kafka (simple producer and Kafka Streams library)

## Author

- **Zakaria Maaraki** - _Initial work_ - [zakariamaaraki](https://github.com/zakariamaaraki)
