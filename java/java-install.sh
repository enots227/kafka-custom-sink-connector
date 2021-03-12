#!/bin/bash

# Prerequisites ##########
ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Install Java ###########
apt-get update
apt-get install openjdk-11-jdk -y

# Install Apache Kafka
tar zxvf /tmp/apache-maven-bin.tar.gz -C /opt