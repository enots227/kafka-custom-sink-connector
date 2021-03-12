FROM ubuntu:20.04

COPY ./java-install.sh /tmp/java-install.sh
COPY ./apache-maven-3.6.3-bin.tar.gz /tmp/apache-maven-bin.tar.gz

ENV TZ="America/Los_Angeles"
# Could probably use default-jre instead of jdk
ENV JAVA_HOME="/usr/lib/jvm/java-1.11.0-openjdk-amd64"
ENV CLASSPATH=".:/usr/lib/jvm/java-1.11.0-openjdk-amd64/lib"

RUN /tmp/java-install.sh
