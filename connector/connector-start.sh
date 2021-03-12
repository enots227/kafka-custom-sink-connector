#!/bin/bash
export SCALA_VERSION="2.13"
export KAFKA_VERSION="2.7.0"
export KAFKA_HOME=/opt/kafka_$SCALA_VERSION-$KAFKA_VERSION 

# Start Kafka Connector
cd $KAFKA_HOME
exec bin/connect-distributed.sh config/connector.properties
