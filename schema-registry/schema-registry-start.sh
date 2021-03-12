confluent_platform_version="6.1.0"
confluent_home=/opt/confluent-$confluent_platform_version

# Schema Registry Environment Configuration ########
cd $confluent_home/etc/schema-registry

# set listener ===========
SCHEMA_LISTENERS=$(echo $SCHEMA_LISTENERS | sed "s/\//\\\\\//g")

FIND="^listeners=.*$"
REPLACE="listeners=${SCHEMA_LISTENERS}"
sed -i "s/${FIND}/${REPLACE}/" schema-registry.properties

# set brokers ============
# ensures / -> \/ (forward slashes escape the sed find and replace)
SCHEMA_BROKERS=$(echo $SCHEMA_BROKERS | sed "s/\//\\\\\//g")

FIND="^kafkastore.bootstrap.servers=.*$"
REPLACE="kafkastore.bootstrap.servers=${SCHEMA_BROKERS}"
sed -i "s/${FIND}/${REPLACE}/" schema-registry.properties

# stark schema registry ##############################
cd $confluent_home
bin/schema-registry-start etc/schema-registry/schema-registry.properties