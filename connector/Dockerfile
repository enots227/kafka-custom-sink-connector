FROM confluent

COPY ./connector.properties /opt/kafka_2.13-2.7.0/config/connector.properties
COPY ./connector-start.sh /connector-start.sh

ENTRYPOINT /connector-start.sh