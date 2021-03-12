export SCALA_VERSION="2.13"
export KAFKA_VERSION="2.7.0"
export KAFKA_HOME=/opt/kafka_$SCALA_VERSION-$KAFKA_VERSION 

# Kafka Environment Configuration ########
cd $KAFKA_HOME/config

# set zookeeper.connect ===========
FIND="^zookeeper.connect=.*$"
REPLACE="zookeeper.connect=${KAFKA_ZOOKEEPER_CONNECT}"
sed -i "s/${FIND}/${REPLACE}/" server.properties

# set broker.id ===================
FIND="^broker.id=[[:digit:]]*$"
REPLACE="broker.id=${KAFKA_BROKER_ID}"
sed -i "s/${FIND}/${REPLACE}/" server.properties

# set advertised.listeners ========
# ensures / -> \/ (forward slashes escape the sed find and replace)
KAFKA_ADVERTISED_LISTENERS=$(echo $KAFKA_ADVERTISED_LISTENERS | sed "s/\//\\\\\//g")

FIND="^#\{0,1\}advertised.listeners=.*$"
REPLACE="advertised.listeners=${KAFKA_ADVERTISED_LISTENERS}"
sed -i "s/${FIND}/${REPLACE}/" server.properties

# Start Kafka ############################
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties
