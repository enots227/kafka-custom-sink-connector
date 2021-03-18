#!/bin/bash
export SCALA_VERSION="2.13"
export KAFKA_VERSION="2.7.0"
export KAFKA_HOME=/opt/kafka_$SCALA_VERSION-$KAFKA_VERSION 

# Install Custom Connector
cd /opt/multidb-connector
../apache-maven-3.6.3/bin/mvn install -DskipTests

# Start Kafka Connector
cd $KAFKA_HOME
exec bin/connect-distributed.sh config/connector.properties
