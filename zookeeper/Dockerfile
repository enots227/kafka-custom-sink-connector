FROM java

COPY ./apache-zookeeper-3.6.2-bin.tar.gz /tmp/apache-zookeeper-3.6.2-bin.tar.gz
COPY ./zookeeper-install.sh /tmp/zookeeper-install.sh
COPY ./zookeeper-start.sh ./zookeeper-start.sh

RUN /tmp/zookeeper-install.sh

ENTRYPOINT /zookeeper-start.sh