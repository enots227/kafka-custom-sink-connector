FROM confluent

COPY ./ksql-start.sh /ksql-start.sh
COPY ./ksql-install.sh /tmp/ksql-install.sh

RUN /tmp/ksql-install.sh

ENTRYPOINT /ksql-start.sh