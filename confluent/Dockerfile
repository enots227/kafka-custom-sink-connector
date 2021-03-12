FROM kafka

COPY ./confluentinc-kafka-connect-jdbc-10.0.1.zip /tmp/confluentinc-kafka-connect-jdbc-10.0.1.zip
COPY ./confluent-community-6.1.0.zip /tmp/confluent-community-6.1.0.zip
COPY ./confluent-install.sh /tmp/confluent-install.sh

RUN /tmp/confluent-install.sh
