export ZOOKEEPER_VERSION="3.6.2"
export ZOOKEEPER_HOME=/opt/zookeeper-$ZOOKEEPER_VERSION

cd $ZOOKEEPER_HOME

# Zookeeper Environment Configuration ########
cp conf/zoo_sample.cfg conf/zoo.cfg

# set dataDir =====================
FIND="^dataDir=\/tmp\/zookeeper$"
REPLACE="dataDir=\/var\/zookeeper\/data"
sed -i "s/${FIND}/${REPLACE}/" conf/zoo.cfg

# Start Zookeeper ############################
exec bin/zkServer.sh start-foreground

# Start Zookeeper in background -- will immediately shutdown container when ran as daemon
# exec bin/zkServer.sh start

# Get Status of Zookeeper Instance
# exec bin/zkServer.sh status

# Interface with Zookeeper CLI Example
# exec bin/zkCli.sh -server localhost:2181
