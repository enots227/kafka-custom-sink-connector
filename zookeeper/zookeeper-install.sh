zookeeper_version="3.6.2"
zookeeper_home=/opt/zookeeper-$zookeeper_version

# Install Apache Zookeeper
tar zxvf /tmp/apache-zookeeper-$zookeeper_version-bin.tar.gz -C /opt
mv /opt/apache-zookeeper-$zookeeper_version-bin $zookeeper_home

mkdir /var/zookeeper
mkdir /var/zookeeper/data