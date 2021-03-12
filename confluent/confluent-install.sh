jdbc_connect_version="10.0.1"
confluent_platform_version="6.1.0"
scala_version="2.13"
kafka_version="2.7.0"
jdbc_conect_home=/opt/confluentinc-kafka-connect-jdbc-$jdbc_connect_version
kafka_home=/opt/kafka_$scala_version-$kafka_version

# Install curl and unzip
apt-get install unzip -y
apt-get install curl -y

# Install Confluent Platform
unzip /tmp/confluent-community-$confluent_platform_version.zip -d /opt

# Install JDBC Connector
unzip /tmp/confluentinc-kafka-connect-jdbc-$jdbc_connect_version.zip -d /opt
