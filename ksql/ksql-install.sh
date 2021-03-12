confluent_platform_version="6.1.0"
confluent_home=/opt/confluent-$confluent_platform_version

# KSQL Configuration ########
cd $confluent_home/etc/ksqldb
echo "\n\n#------ Added Configurations -------" >> ksql-server.properties

echo "ksql.connect.url=http://localhost:8083" >> ksql-server.properties
