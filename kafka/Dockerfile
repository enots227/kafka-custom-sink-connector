FROM java

COPY ./kafka_2.13-2.7.0.tgz /tmp/kafka_2.13-2.7.0.tgz
COPY ./kafka-install.sh /tmp/kafka-install.sh
COPY ./kafka-start.sh /kafka-start.sh

RUN /tmp/kafka-install.sh

ENTRYPOINT /kafka-start.sh