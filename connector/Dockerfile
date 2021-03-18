FROM confluent

COPY ./connector.properties /opt/kafka_2.13-2.7.0/config/connector.properties
COPY ./connector-start.sh /connector-start.sh
COPY ./multiDB_compile.sh ./c.sh
COPY ./postgresql-42.2.19.jar /opt/kafka_2.13-2.7.0/libs/postgresql-42.2.19.jar

ENTRYPOINT /connector-start.sh